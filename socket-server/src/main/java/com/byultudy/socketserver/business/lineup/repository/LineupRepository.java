package com.byultudy.socketserver.business.lineup.repository;

import com.byultudy.socketserver.business.user.UserRequestDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class LineupRepository {

    private final RedisTemplate<String, String> redisTemplate;
    public final String LINEUP_KEY = "lineup";
    private ZSetOperations<String, String> zSet = redisTemplate.opsForZSet();

    public void add(String session, Long time) {
        zSet.add(LINEUP_KEY, session, time);
    }

    public Long getRank(String session) {
        return zSet.rank(LINEUP_KEY, session);
    }

    public Long getCount() {
        return zSet.zCard(LINEUP_KEY);
    }

    public void getRange(long range) {
        zSet.range(LINEUP_KEY , 0L , range );
    }

}
