package com.javatea.spotchserver.objects.websocket;

/**
 * ログイン時に使用するメッセージを保持するクラス
 */
public class SignInMessage {
	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;

	/**
	 * メールアドレスを取得する
	 * @return メールアドレス
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * メールアドレスを設定する
	 * @param email メールアドレス
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * パスワードを取得する
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * パスワードを設定する
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}