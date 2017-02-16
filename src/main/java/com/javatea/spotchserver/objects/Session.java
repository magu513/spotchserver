package com.javatea.spotchserver.objects;

import java.time.LocalDateTime;

/**
 * セッションの情報を保持するクラス
 */
public class Session {
	private long userId;
	/** セッションのトークン */
	private String token;
	/** 有効期限 */
	private LocalDateTime expirationDate;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}
}
