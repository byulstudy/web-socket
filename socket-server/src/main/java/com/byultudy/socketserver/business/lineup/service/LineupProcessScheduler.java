package com.byultudy.socketserver.business.lineup.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import static com.byultudy.socketserver.business.lineup.service.LineupServiceImpl.REDIS_Z_SET_KEY;


@Component
@Slf4j
@RequiredArgsConstructor
public class LineupProcessScheduler {
    private final StringRedisTemplate redisTemplate;
    private final SchedulerProcessable processable;
    private final SimpMessagingTemplate template;

    @Scheduled(fixedDelay = 100)
    @Transactional
    public void processScheduler() {
        Long count = redisTemplate.opsForZSet().zCard(REDIS_Z_SET_KEY);
        log.info("count : {}", count);
        if (!ObjectUtils.isEmpty(count)) {
            template.convertAndSend("/sub/total", count);
            processable.process();
        }
    }


}
