package com.javatea.spotchserver;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 投稿を保持するためのクラス
 */
public class Article {
	/** 投稿ID */
	private long postId;
	/** 投稿者ID*/
	private long userId;

	/** 位置情報X軸 */
	private double x;
	/** 位置情報Y軸*/
	private double y;

	/**  投稿内容 */
	private String content;
	/** 投稿（作成）日時 */
	private String createAt;

	/**
	 * デフォルトコンストラクタ
	 */
	public Article() {}

	/**
	 * 位置情報を文字列として渡すことで、自動で日付型に変換する.
	 * @param postId
	 * @param userId
	 * @param location
	 * @param content
	 * @param createAt
	 */
	public Article(long postId,
				   long userId,
				   String location,
				   String content,
				   String createAt) {

		this.postId = postId;
		this.userId = userId;

		location = location.replace("POINT","");
		location = location.replaceAll("[()]","");
		String tmp[] = location.split(" ");
		this.x = Double.parseDouble(tmp[0]);
		this.y = Double.parseDouble(tmp[1]);

		this.content = content;
		this.createAt = createAt;
	}

	/**
	 * 作成された時間が自動で挿入される
	 * @param userId
	 * @param x
	 * @param y
	 * @param content
	 */
	public Article (long userId,
					double x,
					double y,
					String content) {
		this.userId = userId;
		this.x = x;
		this.y = y;
		this.content = content;
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		this.createAt = sdf.format(c.getTime());
	}

	/**
	 * 投稿IDを設定
	 * @return
	 */
	public long getPostId() {
		return postId;
	}

	/**
	 * 投稿IDの取得
	 * @param postId
	 */
	public void setPostId(long postId) {
		this.postId = postId;
	}

	/**
	 * 投稿内容の取得
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 投稿内容の設定
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 投稿日時の取得
	 * @return 投稿日時
	 */
	public String getCreateAt() {
		return createAt;
	}

	/**
	 * 投稿日時の設定
	 * @param createAt
	 */
	public void setCreateAt(String createAt) {
		this.createAt = createAt;
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
	 * X軸の取得
	 * @return X軸
	 */
	public double getX() {
		return x;
	}

	/**
	 * X軸の設定
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Y軸の取得
	 * @return Y軸
	 */
	public double getY() {
		return y;
	}

	/**
	 * Y軸の設定
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}
}
