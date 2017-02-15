package com.javatea.spotchserver.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.javatea.spotchserver.objects.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class CreateHandler extends TextWebSocketHandler{
	@Autowired
	private ArticleController ac;

	/**
	 * ハンドリングしたテキストメッセージをグローバルキャストします。
	 * @param session セッション
	 * @param message メッセージ
	 */
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println(message.getPayload());
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		Article a = mapper.readValue(message.getPayload(),Article.class);
		System.out.println(a);
		ac.create(a.getUserId(),a.getContent(),a.getLatitude(),a.getLongitude());
	}
}
