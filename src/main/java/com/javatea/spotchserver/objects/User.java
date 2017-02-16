package com.javatea.spotchserver.objects;

import com.javatea.spotchserver.opt.DateFormatter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
	private LocalDate birthDay;
	/** 登録(作成)日 */
	private LocalDateTime createAt;
	/** 更新日時*/
	private LocalDateTime updateAt;

	/**
	 * ユーザのステータス
	 * 2:削除
	 * 1:通常
	 * 0:未認証
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
	 * @param birthDay
	 */
	public User(String userName,
				String email,
				String birthDay) {
		this.userName = userName;
		this.email = email;
		this.birthDay = DateFormatter.stringToDate(birthDay);
		this.status = 2;
	}

	/**
	 * データを設定する
	 * @param userId
	 * @param userName
	 * @param email
	 * @param birthDay
	 * @param createAt
	 */
	public User(long userId,
				String userName,
				String email,
				LocalDate birthDay,
				LocalDateTime createAt,
				LocalDateTime updateAt,
				short status) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.birthDay = birthDay;
		this.createAt = createAt;
		this.updateAt = updateAt;
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
	public LocalDate getBirthDay() {
		return birthDay;
	}

	/**
	 * 生年月日の設定
	 * @param birthDay
	 */
	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	/**
	 * 作成日の取得
	 * @return 作成日
	 */
	public String getCreateAt() {
//		return createAt;
		return DateFormatter.dateTimeToString(createAt);
	}

	/**
	 * 作成日の設定
	 * @param createAt
	 */
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	/**
	 * ステータスの取得
	 * @return
	 */
	public short getStatus() {
		return status;
	}

	/**
	 * ステータスの設定
	 * @param status
	 */
	public void setStatus(short status) {
		this.status = status;
	}

	/**
	 * 更新日時の取得
	 * @return
	 */
	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	/**
	 * 投稿日時の設定
	 * @param updateAt
	 */
	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}
}
