package com.byultudy.socketserver.business.lineup.controller;

import com.byultudy.socketserver.business.lineup.dto.LineupRequestDto;
import com.byultudy.socketserver.business.lineup.dto.LineupResponseDto;
import com.byultudy.socketserver.business.lineup.service.LineupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/lineup")
@RestController
@RequiredArgsConstructor
@Slf4j
public class LineupController {

    public final LineupService lineupService;
    private final SimpMessagingTemplate template;

    @PostMapping
    public ResponseEntity<LineupResponseDto> lineup(@RequestBody LineupRequestDto requestDto) {
        return ResponseEntity.ok(lineupService.lineup(requestDto));
    }


    @MessageMapping("/rank")
    public void getMyRank(@Payload String id) {
        lineupService
                .getRank(id)
                .ifPresentOrElse(
                        rank -> {
                            log.info("id : {}, rank: {}", id, rank);
                            template.convertAndSend("/sub/rank/" + id, rank);
                        },
                        () -> template.convertAndSend("/sub/rank/" + id, 0)
                );
    }
}
