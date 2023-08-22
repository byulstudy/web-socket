package com.byultudy.socketserver.business.lineup.service;

import com.byultudy.socketserver.business.lineup.dto.LineupRequestDto;
import com.byultudy.socketserver.business.lineup.dto.LineupResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.Set;
import java.time.ZoneId;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LineupServiceImpl implements LineupService, SchedulerProcessable {
    public static final String REDIS_Z_SET_KEY = "line_up";
    private final StringRedisTemplate redisTemplate;
    private final SimpMessagingTemplate template;

    @Override
    public LineupResponseDto lineup(final LineupRequestDto requestDto) {
        final String id = requestDto.id();
        final LocalDateTime now = LocalDateTime.now();
        final long score = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        this.addToZSet(id, (int) score);
        return new LineupResponseDto(id, now);
    }

    @Override
    public Optional<Long> getRank(final String id) {
        return Optional.ofNullable(redisTemplate.opsForZSet().rank(REDIS_Z_SET_KEY, id));
    }

    private void addToZSet(final String id, final int score) {
        redisTemplate.opsForZSet().add(REDIS_Z_SET_KEY, id, score);
    }

    @Override
    public void process() {
        this.processTime();
        Set<String> queue = redisTemplate.opsForZSet().range(REDIS_Z_SET_KEY, 0, 0);
        if (ObjectUtils.isEmpty(queue)) {
            return;
        }
        queue.forEach(id -> {
            log.info("processed id : {}", id);
            template.convertAndSend("/sub/process/" + id, id);
            redisTemplate.opsForZSet().remove(REDIS_Z_SET_KEY, id);
        });
    }

    private void processTime() {
        final int waitTime = new Random().nextInt(25, 75);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
