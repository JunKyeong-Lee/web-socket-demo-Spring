package com.example.websocket;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MessengerMessageRequest {
    private String from;
    private String text;
}