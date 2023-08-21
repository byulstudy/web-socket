package com.byultudy.socketserver.business.lineup.service;

import com.byultudy.socketserver.business.lineup.dto.LineupRequestDto;
import com.byultudy.socketserver.business.lineup.dto.LineupResponseDto;

public interface LineupService {
    LineupResponseDto lineup(LineupRequestDto requestDto);
}
