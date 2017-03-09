package com.javatea.spotchserver.controller;

import com.javatea.spotchserver.db.PassDao;
import com.javatea.spotchserver.db.PreUserDao;
import com.javatea.spotchserver.objects.PreUser;
import com.javatea.spotchserver.objects.User;
import com.javatea.spotchserver.db.UserDao;
import com.javatea.spotchserver.opt.Hash;
import com.javatea.spotchserver.opt.MailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.time.LocalDateTime;

/**
 * User関連の通信を行うクラス
 */
@Controller
public class UserController {
	/*
	TODO HTTPSでの通信
	 */
	@Autowired
	private UserDao ud;
	@Autowired
	private PreUserDao pud;
	@Autowired
	private PassDao pd;
	@Autowired
	private MailSend ms;

	/**
	 * 仮登録処理
	 * @param username ユーザー名
	 * @param email メールアドレス
	 * @param birthday 誕生日
	 * @param password パスワード
	 * @throws SQLException DBの処理に失敗した場合に発生する例外
	 */
	@RequestMapping(value = "/signon",method = RequestMethod.POST)
	public void signon(@RequestParam String username,
					   @RequestParam String email,
					   @RequestParam String birthday,
					   @RequestParam String password) throws SQLException {

		/*TODO DBに登録し損なった場合の処理を実装する*/
		User u = new User(username,email,birthday);
		u = ud.insert(u);
		LocalDateTime now = LocalDateTime.now();
		PreUser pu = new PreUser(u.getUserId(),now);
		pud.insert(pu);

		String salt = Hash.getHashStr(String.valueOf(u));
		String hashPass = Hash.getPassHash(password, salt);
		pd.insert(u.getUserId(),hashPass,salt);

		ms.send(email,pu.getToken());
		/*TODO 成否を送信したい*/
	}

	/**
	 * 本登録処理
	 * @param token 本登録用トークン
	 * @return セッション情報を返す
	 */
	@RequestMapping("/signon/{token}")
	public String singon(@PathVariable String token) {
		long userId = pud.find(token);
		User u = ud.find(userId);
		if (u != null) {
			u.setStatus((short) 1);
			ud.update(u);
			pud.delete(userId);

		} else {
			//error処理
		}
		return "";
	}

//	@RequestMapping("/signin")
//	public void signin(@RequestParam String email,
//					   @RequestParam String password) {
//		User u = ud.find(email);
//	}
}
