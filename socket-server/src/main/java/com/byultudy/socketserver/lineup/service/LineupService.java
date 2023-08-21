package com.byultudy.socketserver.lineup.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LineupService {

    private final RedisTemplate<String, String> redisTemplate;


}
