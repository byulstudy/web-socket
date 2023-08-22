package com.byultudy.socketserver.lineup.service;

import com.byultudy.socketserver.user.UserDto;
import com.byultudy.socketserver.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LineupService {

    private final RedisTemplate<String, String> redisTemplate;
    private final String LINEUP_KEY = "lineup";

    public void lineup(final UserDto user) {
        final long time = user.atToEpochMilli();
        String userId = UUID.randomUUID().toString();
        redisTemplate.opsForZSet().add(LINEUP_KEY, userId, time);
    }
}
