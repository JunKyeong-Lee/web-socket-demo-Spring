package com.example.websocket;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * WebSocket을 실제로 클라이언트 쪽에 전달하기 위한 컨트롤러
 */
@Controller
public class WebSocketController {

    /**
     * 클라이언트에서 메신저 채팅방으로 메시지를 보낼 때 호출하는 메서드이다.<br>
     * MessageMapping 부분은 클라이언트에서 서버로 보내는 부분의 주소<br>
     * SendTo는 구독된 주소로 클라이언트가 받을 장소로 서버가 보낸다고 생각하면 된다.
     *
     * @param roomId  메시지가 속한 채팅방의 식별자<br>
     *                @DestinationVariable 메시지 매핑 메서드에서 동적인 값을 받기 위함.
     * @param message 전송된 메신저 메시지 객체
     *                @Payload 메시지의 본문이 매핑되도록 지정하기 위함. JSON으로 반환해도 알아서 받기 위함.
     * @return 서버에서 클라이언트로 브로드캐스트되는 메신저 메시지 객체
     */
    @MessageMapping("/messenger/rooms/{roomId}/send")
    @SendTo("/topic/messenger/rooms/{roomId}")
    public MessengerMessageRequest sendMessage(@DestinationVariable Long roomId, @Payload MessengerMessageRequest message) {
        System.out.println("roomId: " + roomId);
        System.out.println("MessengerMessage: " + message);
        System.out.println(message.getFrom());
        System.out.println(message.getText());
        return message;
    }

//    // 추가적인 주소가 필요할때는 아래처럼 작성하면 된다.
//    @MessageMapping("/calendar/events/{eventId}/send")
//    @SendTo("/topic/calendar/events/{eventId}")
//    public CalendarEventAlarmRequest sendAlarm(@DestinationVariable Long eventId, @Payload CalendarEventAlarmRequest alarm) {
//        System.out.println("eventId: " + eventId);
//        System.out.println("CalendarEventAlarm: " + alarm);
//        System.out.println(message.getFrom());
//        System.out.println(message.getText());
//        return alarm;
//    }
}
