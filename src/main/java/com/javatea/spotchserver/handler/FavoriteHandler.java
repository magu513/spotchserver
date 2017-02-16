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

@Component
public class FavoriteHandler extends TextWebSocketHandler{
	@Autowired
	FavoriteDao fd;
	@Autowired
	DBConnector connector;
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
