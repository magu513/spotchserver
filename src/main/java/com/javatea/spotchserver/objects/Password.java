package com.javatea.spotchserver.objects;

/**
 * Created by ShoyaYamaguchi on 2017/02/16.
 */
public class Password {
	private String password;
	private String salt;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
}
