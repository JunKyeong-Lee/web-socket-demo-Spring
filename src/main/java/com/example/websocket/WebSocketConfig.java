package com.example.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocket을 구성하는 클래스이다.
 * 실시간 양방향 통신을 위해 Stomp프로토콜을 사용한다.<br>
 * EnableWebSocketMessageBroker 어노테이션은 WebSocket 기능을 활성한다.
 *
 * @implNote WebSocketMessageBrokerConfigurer는 WebSocket을 구성하는 데 사용하는 메서드를 제공하는 인터페이스
 * @see org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    /**
     * WebSocket 엔드포인트를 등록한다.<br>
     * 이 때 엔드포인트로 사용하는 url은 말 그대로, 서로 상호 동의하는 과정인 handshake를 위한 것 이다.<br>
     * 웹소켓의 엔드포인트의 경우 전역적으로 하나만 있어도 되기 때문에 여러 엔드포인트를 쓰는거는 특별한 상황인 경우 뿐이다.<br>
     * 즉, 처음 백엔드 서버를 실행할때 한번만 적용되면 되니까 프론트엔드 측면에서도 최상단에서 적용하면 된다.
     *
     * @param stompEndpointRegistry Stomp 엔드포인트 레지스트리
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        /*
         * socket 의 엔드 포인트 url을 설정하고
         * cors의 허용범위는 모든 구역에서 가능하도록 설정한다.
         * */
        stompEndpointRegistry
                .addEndpoint("/websocket") // socket 연결 url
                .setAllowedOriginPatterns("*"); // CORS 허용 범위
    }

    /**
     * 메시지 브로커를 구성합니다.<br>
     * 메시지 브로커는 클라이언트 간에 메시지를 전달하고 관리하는 중간 매게체를 말하는데, 이 중 STOMP를 사용합니다.<br>
     *
     * @param messageBrokerRegistry 메시지 프로커 레지스트리
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry messageBrokerRegistry) {
        /*
         * 브로커는 url 중간다리 역할 뿐만 아니라. 브로드 캐스팅의 역할도 있어서 필수적이다.
         * 왜냐하면 이 메시지 브로커라는것 자체에서 전달 보장 메커니즘이 존재하기 때문에
         * 개발자가 무리해서 개발할 필요가 없기 때문이다. (이게 없으면 직접 전달기능을 작성해야 한다.)
         *
         * 심지어 버퍼링 기능도 있어서 중간에 의도적으로 송신 속도를 느리게 해주는 역할도 있다.
         * 단점도 분명 있지만 현재는 확실하게 쓸만한 해결점이다.
         *
         * https://binux.tistory.com/74
         * */
        messageBrokerRegistry.enableSimpleBroker("/topic");
        /*
         * 클라이언트에서 서버로 보낼때 목적지가 있을텐데 그 주소의 prefix를 지정한다.
         * 자세한건 MessageController 부분에서 확인할 수 있다.
         * */
        messageBrokerRegistry.setApplicationDestinationPrefixes("/app"); // prefix 정의
    }
}