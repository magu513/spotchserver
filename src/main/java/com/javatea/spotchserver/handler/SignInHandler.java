package com.javatea.spotchserver.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.javatea.spotchserver.db.PassDao;
import com.javatea.spotchserver.db.UserDao;
import com.javatea.spotchserver.objects.Password;
import com.javatea.spotchserver.objects.User;
import com.javatea.spotchserver.objects.websocket.SignInMessage;
import com.javatea.spotchserver.opt.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SignInHandler extends TextWebSocketHandler {
	@Autowired
	private UserDao ud;
	@Autowired
	private PassDao pd;

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());

		SignInMessage m = mapper.readValue(message.getPayload(),SignInMessage.class);
		User user = ud.findWhereMail(m.getEmail());
		Password password = pd.find(user.getUserId());
		String pass = Hash.getPassHash(m.getPassword(),password.getSalt());

		if ( pass.equals(password.getPassword()) ) {
			String json = mapper.writeValueAsString(user);
			session.sendMessage(new TextMessage(json));
		} else {
			session.sendMessage(new TextMessage("{result:false}"));
		}
	}
}
