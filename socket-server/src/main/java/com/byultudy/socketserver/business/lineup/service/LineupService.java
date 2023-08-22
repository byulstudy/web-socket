package com.byultudy.socketserver.business.lineup.service;

import com.byultudy.socketserver.business.lineup.dto.LineupRequestDto;
import com.byultudy.socketserver.business.lineup.dto.LineupResponseDto;

import java.util.Optional;

public interface LineupService {
    LineupResponseDto lineup(LineupRequestDto requestDto);

    Optional<Long> getRank(String id);
}
