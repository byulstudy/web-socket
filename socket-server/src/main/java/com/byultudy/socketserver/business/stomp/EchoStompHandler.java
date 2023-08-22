package com.byultudy.socketserver.business.stomp;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EchoStompHandler {

    private final SimpMessageSendingOperations simpMessageSendingOperations;

    @MessageMapping("/echo")     // 발송자에게서 받음 /pub/echo
    @SendTo("/sub/echo") //구독자에게 발행
    public String handleEcho(byte[] payload) {
        if (payload == null || payload.length == 0) {
            return "...";
        }
        return new String(payload) + "~!!!";
    }

}
