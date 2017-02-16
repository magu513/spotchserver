package com.javatea.spotchserver.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatea.spotchserver.controller.ArticleController;
import com.javatea.spotchserver.objects.Article;
import com.javatea.spotchserver.objects.websocket.FindMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 投稿検索に関する通信の処理を実装したクラス
 */
@Component
public class FindHandler extends TextWebSocketHandler{
	private Map<String,WebSocketSession> sessionPool = new ConcurrentHashMap<>();

	@Autowired
	private ArticleController ac;
	/**
	 * 接続が確立したセッションをプールします。
	 * @param session セッション
	 * @throws Exception 例外が発生した場合
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		this.sessionPool.put(session.getId(), session);
	}

	/**
	 * 切断された接続をプールから削除します。
	 * @param session セッション
	 * @param status ステータス
	 * @throws Exception 例外が発生した場合
	 */
	@Override
	public void afterConnectionClosed(
			WebSocketSession session,
			CloseStatus status) throws Exception {
		this.sessionPool.remove(session.getId());
	}

	/**
	 * 要求された情報をDBから取得し、返却する。
	 * @param session セッション
	 * @param message メッセージ
	 */
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println(message.getPayload());
		ObjectMapper mapper = new ObjectMapper();
		FindMessage fm = mapper.readValue(message.getPayload(),FindMessage.class);
		List<Article> articles = ac.read(fm.getLatitude(),fm.getLongitude(),fm.getRange());
		articles.sort((o1, o2) -> (int) (o1.getUserId() - o2.getUserId()));

		String json = mapper.writeValueAsString(articles);
		System.out.println(json);
		session.sendMessage(new TextMessage(json));
	}
}
