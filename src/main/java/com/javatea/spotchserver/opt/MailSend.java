package com.javatea.spotchserver.opt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * メール送信に関するクラス
 */
@Component
public class MailSend {
	@Autowired
	private MailSender ms;

	/**
	 * メールを送信する
	 * @param address
	 * @param link
	 */
	@Async
	public void send(String address,String link) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom("spotch_service@kbckj.net");
		msg.setTo(address);
		msg.setSubject("登録用アドレス");
		msg.setText("http://kbckj.net/singon/"+link);
		ms.send(msg);
	}
}
