package com.javatea.spotchserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DBConf {
	@Value("${spotch.db.url}")
	private String url;
	@Value("${spotch.db.user}")
	private String user;
	@Value("${spotch.db.pass}")
	private String pass;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String toString() {
		return url + " " + user + " " + pass;
	}
}
