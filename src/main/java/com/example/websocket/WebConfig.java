package com.example.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring 설정을 담기 위한 클래스
 * CORS (Cross-Origin Resource Sharing)을 구성하기 위한 설정 클래스
 */
@Configuration
public class WebConfig {
    /**
     * WebMvcConfigurer 인터페이스 반환하는 메서드
     * @return WebMvcConfigurer 익명 클래스로 WebMvcConfigurer의 메서드 구현
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * CORS 구성을 정의. 모든 경로에 대해 모든 오리진을 허옹한다는 뜻
             * @param corsRegistry CORS 설정을 구성하는데 사용되는클래스. 이를 이용해서 쉽게 처리할 수 있다.
             */
            @Override
            public void addCorsMappings(CorsRegistry corsRegistry) {
                corsRegistry.addMapping("/**").allowedOriginPatterns("*");
            }
        };
    }
}