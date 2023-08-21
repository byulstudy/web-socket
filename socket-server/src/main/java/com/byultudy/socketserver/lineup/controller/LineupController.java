package com.byultudy.socketserver.lineup.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/lineup")
@RestController
public class LineupController {

    private LineupController lineupController;

    @PostMapping
    public ResponseEntity<?> lineup() {

        return ResponseEntity.ok(null);
    }
}
