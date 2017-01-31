package com.javatea.spotchserver.objects;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by ShoyaYamaguchi on 2017/01/26.
 */
public class PreUser {
	/** 投稿者ID */
	private long userId;
	/** 有効期限 */
	private LocalDateTime expirationDate;
	/** 認証文字列 */
	private String token;
	public PreUser(long userId,LocalDateTime expirationDate,String token) {
		this.userId = userId;
		this.expirationDate = expirationDate;
		this.token = token;
	}

	/**
	 * 投稿者IDの取得
	 * @return 投稿者ID
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 有効期限の取得
	 * @return 有効期限
	 */
	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	/**
	 * 認証文字列の取得
	 * @return 認証文字列
	 */
	public String getToken() {
		return token;
	}
}
