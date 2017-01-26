package com.javatea.spotchserver;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * ユーザー情報を保持するクラス
 */
public class User {
	/** 投稿者ID */
	private long userId;

	/**  投稿者名 */
	private String userName;
	/** メールアドレス */
	private String email;
	/** 生年月日 */
	private Date birthDate;
	/** 登録(作成)日 */
	private Date createAt;
	/**
	 * ユーザのステータス
	 * 0:削除
	 * 1:通常
	 * 2:未認証
	 */
	private short status;

	/**
	 * デフォルトコンストラクタ
	 */
	public User() {}

	/**
	 * 仮登録時に呼ばれる
	 * @param userName
	 * @param email
	 * @param birthDate
	 */
	public User(String userName,
				String email,
				Date birthDate) {
		this.userName = userName;
		this.email = email;
		this.birthDate = birthDate;
	}

	/**
	 * データを設定する
	 * @param userId
	 * @param userName
	 * @param email
	 * @param birthDate
	 * @param createAt
	 */
	public User(long userId,
				String userName,
				String email,
				Date birthDate,
				Date createAt,
				short status) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.birthDate = birthDate;
		this.createAt = createAt;
		this.status = status;
	}

	/**
	 * 投稿者IDの取得
	 * @return 投稿者ID
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * 投稿者IDの設定
	 * @param userId
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 投稿者名の取得
	 * @return 投稿者ID
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 投稿者名の設定
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * メールアドレスの取得
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * メールアドレスの設定
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 生年月日の取得
	 * @return 生年月日
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * 生年月日の設定
	 * @param birthDate
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * 作成日の取得
	 * @return 作成日
	 */
	public Date getCreateAt() {
		return createAt;
	}

	/**
	 * 作成日の設定
	 * @param createAt
	 */
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
}
