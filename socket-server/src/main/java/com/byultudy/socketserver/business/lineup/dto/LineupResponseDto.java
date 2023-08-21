package com.byultudy.socketserver.business.lineup.dto;

import java.time.LocalDateTime;

public record LineupResponseDto(
        String id,
        LocalDateTime createdAt
) {

}
