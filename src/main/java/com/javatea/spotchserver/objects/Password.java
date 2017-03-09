package com.javatea.spotchserver.objects;

/**
 * パスワードに関するクラス
 */
public class Password {
	/** ハッシュ化済みのパスワード */
	private String password;
	/** ハッシュ化に用いたsalt*/
	private String salt;

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

	/**
	 * ソルトを取得する
	 * @return ソルト
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * ソルトを設定する
	 * @param salt ソルト
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
}
