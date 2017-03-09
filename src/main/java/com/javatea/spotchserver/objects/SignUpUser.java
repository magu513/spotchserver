package com.javatea.spotchserver.objects;

/**
 * SignUp時にクライアントから送信されたメッセージを取得するためのクラス
 */
public class SignUpUser extends User{
	/** パスワード */
	private String password;

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
