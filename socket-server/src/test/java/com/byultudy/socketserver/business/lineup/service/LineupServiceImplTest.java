package com.byultudy.socketserver.business.lineup.service;

import com.byultudy.socketserver.business.lineup.dto.LineupRequestDto;
import com.byultudy.socketserver.business.lineup.dto.LineupResponseDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDateTime;

import static com.byultudy.socketserver.business.lineup.service.LineupServiceImpl.REDIS_Z_SET_KEY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

class LineupServiceImplTest {
    public static final LocalDateTime TARGET_DATE_TIME = LocalDateTime.of(2023, 8, 23, 0, 0, 0);
    private StringRedisTemplate redisTemplate;
    private MockedStatic<LocalDateTime> localDateTimeMockedStatic;
    private LineupService lineupService;

    @BeforeEach
    void setUp() {
        redisTemplate = new StringRedisTemplate();
        final RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        LettuceConnectionFactory connection = new LettuceConnectionFactory(config);
        connection.afterPropertiesSet();
        redisTemplate.setConnectionFactory(connection);
        redisTemplate.afterPropertiesSet();

        this.localDateTimeMockedStatic = mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS);
        localDateTimeMockedStatic.when(LocalDateTime::now)
                .thenReturn(TARGET_DATE_TIME);

        this.lineupService = new LineupServiceImpl(redisTemplate);
    }


    @AfterEach
    void tearDown() {
        redisTemplate.delete(REDIS_Z_SET_KEY);
        localDateTimeMockedStatic.close();
    }

    @Test
    @DisplayName("lineup 메서드 - 성공")
    void lineup() {
        String id = "test";
        LineupResponseDto res = lineupService.lineup(new LineupRequestDto(id));

        assertThat(res.id()).isEqualTo(id);
        assertThat(res.createdAt()).isEqualTo(TARGET_DATE_TIME);
        assertThat(redisTemplate.opsForZSet().zCard(REDIS_Z_SET_KEY)).isEqualTo(1L);
    }
}