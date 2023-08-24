package com.byultudy.socketserver.business.lineup.service;

import com.byultudy.socketserver.business.lineup.repository.LineupRepository;
import com.byultudy.socketserver.business.user.UserRequestDto;
import com.byultudy.socketserver.business.user.UserResponseDto;
import com.byultudy.socketserver.utils.ThreadUtils;
import com.byultudy.socketserver.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class LineupService {

    private final LineupRepository lineupRepository;
    private final long RANGE = 100L;
    public UserResponseDto lineup(final UserRequestDto user) {
        final long time = user.atToEpochMilli();
        String userId = UUID.randomUUID().toString();
        lineupRepository.add(userId, time);
        return UserResponseDto.from(time, userId);
    }

    public void enter()  {
        Set<ZSetOperations.TypedTuple<String>> pop = lineupRepository.pop(RANGE);
        for (ZSetOperations.TypedTuple<String> stringTypedTuple : pop) {
            String session = stringTypedTuple.getValue();
            ThreadUtils.sleep(TimeUtils.randomMillSecond(250 , 750));
            log.info("{} 님 처리 완료" , session);
        }
    }
}
