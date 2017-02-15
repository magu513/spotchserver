package com.javatea.spotchserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.javatea.spotchserver.db.PassDao;
import com.javatea.spotchserver.db.UserDao;
import com.javatea.spotchserver.objects.SignUpUser;
import com.javatea.spotchserver.objects.User;
import com.javatea.spotchserver.opt.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class SignUpHandler extends TextWebSocketHandler {
	@Autowired
	private UserDao ud;
	@Autowired
	private PassDao pd;
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		User user = mapper.readValue(message.getPayload(),SignUpUser.class);

		String json = "{";
		try {
			user = ud.insert(user);
			SignUpUser signUpUser = (SignUpUser) user;
			String salt = Hash.getHashStr(user.getUserId() + LocalDateTime.now().toString());
			String passHash = Hash.getPassHash(signUpUser.getPassword(), salt);
			pd.insert(user.getUserId(), passHash, salt);

			json += "\"result\":" + true;
		} catch (SQLException e) {
			json += "\"result\":" + false;
		}

		json += ",\"userId\":" + user.getUserId();
		json += "}";

		session.sendMessage(new TextMessage(json.getBytes()));
	}
}
