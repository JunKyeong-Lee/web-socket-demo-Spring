package com.example.websocket240130;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
class ChatController {

    @MessageMapping("/chat/rooms/{roomId}/send")
    @SendTo("/topic/public/rooms/{roomId}")
    public ChatMessageRequest sendMessage(@DestinationVariable Long roomId, @Payload ChatMessageRequest chatMessage) {
        System.out.println("roomId: " + roomId);
        System.out.println("chatMessage " + chatMessage);
        return chatMessage;
    }
}
