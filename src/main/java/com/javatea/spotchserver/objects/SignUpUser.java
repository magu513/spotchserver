package com.javatea.spotchserver.objects;

/**
 * SignUp時にクライアントから送信されたメッセージを取得するためのクラス
 */
public class SignUpUser extends User{
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
