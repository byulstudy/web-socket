package com.byultudy.socketserver.business.lineup.controller;

import com.byultudy.socketserver.business.lineup.service.LineupService;
import com.byultudy.socketserver.business.user.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/lineup")
@RestController
public class LineupController {

    private final LineupService lineupService;

    @PostMapping
    public ResponseEntity<?> lineup(@RequestBody UserRequestDto user) {
        lineupService.lineup(user);
        return ResponseEntity.ok(null);
    }
}
