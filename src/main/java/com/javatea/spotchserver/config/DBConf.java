package com.javatea.spotchserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * DBの設定を保持するクラス
 */
@Component
public class DBConf {
	/** DBの接続先のurl */
	@Value("${spotch.db.url}")
	private String url;

	/** 接続に使用するユーザー*/
	@Value("${spotch.db.user}")
	private String user;

	/** パスワード */
	@Value("${spotch.db.pass}")
	private String pass;

	/** 接続するDB名*/
	@Value("${spotch.db.dbname}")
	private String dbName;

	/**
	 * URLを取得する
	 * @return url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * URLを設定する
	 * @param url DBのURL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * ユーザーを取得する
	 * @return ユーザー
	 */
	public String getUser() {
		return user;
	}

	/**
	 * ユーザーを設定する
	 * @param user ユーザー名
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * パスワードを取得する
	 * @return パスワード
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * パスワードを設定する
	 * @param pass パスワード
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	public String toString() {
		return url + " " + user + " " + pass;
	}

	/**
	 * DB名を取得する
	 * @return DB名
	 */
	public String getDbName() {
		return dbName;
	}

	/**
	 * DB名を設定する
	 * @param dbName DB名
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
}
