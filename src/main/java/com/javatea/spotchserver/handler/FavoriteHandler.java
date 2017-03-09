package com.javatea.spotchserver.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javatea.spotchserver.db.DBConnector;
import com.javatea.spotchserver.db.FavoriteDao;
import com.javatea.spotchserver.objects.websocket.FavoriteMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.sql.SQLException;

/**
 * WebSocketでお気に入り登録に関する処理を実行するクラス
 */
@Component
public class FavoriteHandler extends TextWebSocketHandler{
	/** お気に入りをDBで扱うためのクラス */
	@Autowired
	FavoriteDao fd;
	/** DB接続管理クラス */
	@Autowired
	DBConnector connector;

	/**
	 * handlerTextMessageをオーバーライドし、
	 * お気に入りの処理をさせる様に特化したメソッド
	 * @param session セッション
	 * @param message クライアントから受信したメッセージ
	 * @throws Exception ロールバックに失敗した場合に発生する例外
	 */
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		FavoriteMessage fm = mapper.readValue(message.getPayload(),FavoriteMessage.class);
		try {
			if (fm.isStatus()) {
				fd.insert(fm);
			} else {
				fd.delete(fm);
			}
			connector.commit();
		} catch (SQLException e) {
			connector.rollback();
		}
	}
}
