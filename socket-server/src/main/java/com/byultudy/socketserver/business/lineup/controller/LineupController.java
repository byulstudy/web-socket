package com.byultudy.socketserver.business.lineup.controller;

import com.byultudy.socketserver.business.lineup.dto.LineupRequestDto;
import com.byultudy.socketserver.business.lineup.dto.LineupResponseDto;
import com.byultudy.socketserver.business.lineup.service.LineupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/lineup")
@RestController
@RequiredArgsConstructor
public class LineupController {

    public final LineupService lineupService;

    @PostMapping
    public ResponseEntity<LineupResponseDto> lineup(@RequestBody LineupRequestDto requestDto) {
        return ResponseEntity.ok(lineupService.lineup(requestDto));
    }
}
