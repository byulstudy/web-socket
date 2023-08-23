package com.byultudy.socketserver.business.lineup.service;

import com.byultudy.socketserver.business.lineup.repository.LineupRepository;
import com.byultudy.socketserver.business.user.UserRequestDto;
import com.byultudy.socketserver.business.user.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class LineupService {

    private final LineupRepository lineupRepository;


    public UserResponseDto lineup(final UserRequestDto user) {
        final long time = user.atToEpochMilli();
        String userId = UUID.randomUUID().toString();

        lineupRepository.add(userId, time);
        return UserResponseDto.from(time, userId);
    }

    public void enter() {

    }
}
