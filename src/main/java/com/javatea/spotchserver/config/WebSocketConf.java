package com.javatea.spotchserver.config;

import com.javatea.spotchserver.controller.FindHandler;
import com.javatea.spotchserver.controller.CreateHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;


@Configuration
@EnableWebSocket
public class WebSocketConf implements WebSocketConfigurer {
	@Autowired
	private FindHandler findHandler;
	@Autowired
	private CreateHandler createHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(findHandler,"/socket/articles/find").setAllowedOrigins("*");
		registry.addHandler(createHandler,"/socket/articles/create").setAllowedOrigins("*");
	}
}
