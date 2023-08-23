package com.byultudy.socketserver.business.lineup.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

import java.util.Set;

@RequiredArgsConstructor
@Repository
public class LineupRepository {

    private final RedisTemplate<String, String> redisTemplate;
    public final String LINEUP_KEY = "lineup";

    private ZSetOperations<String, String> zSet() {
        return redisTemplate.opsForZSet();
    }

    public void add(String session, Long time) {
        zSet().add(LINEUP_KEY, session, time);
    }

    public Long getRank(String session) {
        return zSet().rank(LINEUP_KEY, session);
    }

    public Long size() {
        return zSet().zCard(LINEUP_KEY);
    }

    public Set<ZSetOperations.TypedTuple<String>> pop(long range) {
        return zSet().popMin(LINEUP_KEY, range - 1);
    }

}
