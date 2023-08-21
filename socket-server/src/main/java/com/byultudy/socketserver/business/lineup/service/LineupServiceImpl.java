package com.byultudy.socketserver.business.lineup.service;

import com.byultudy.socketserver.business.lineup.dto.LineupRequestDto;
import com.byultudy.socketserver.business.lineup.dto.LineupResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
@Slf4j
public class LineupServiceImpl implements LineupService {
    public static final String REDIS_Z_SET_KEY = "line_up";
    private final StringRedisTemplate redisTemplate;
    @Override
    public LineupResponseDto lineup(final LineupRequestDto requestDto) {
        final String id = requestDto.id();
        final LocalDateTime now = LocalDateTime.now();
        final long score = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        this.addToZSet(id, (int) score);
        return new LineupResponseDto(id, now);
    }

    private void addToZSet(final String id, final int score) {
        redisTemplate.opsForZSet().add(REDIS_Z_SET_KEY, id, score);
    }
}
