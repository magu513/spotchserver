package com.javatea.spotchserver.objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javatea.spotchserver.opt.DateFormatter;

import java.time.LocalDateTime;

/**
 * 投稿を保持するためのクラス
 */
public class Article {
	/** 投稿ID */
	private long postId;
	/** 投稿者ID*/
	private long userId;

	/** 位置情報X軸 */
	private double latitude;
	/** 位置情報Y軸*/
	private double longitude;

	/**  投稿内容 */
	private String content;
	/** 投稿（作成）日時 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createAt;
	/** お気に入り判定 */
	private boolean favorite;

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
				   String createAt,
				   boolean favorite) {

		this.postId = postId;
		this.userId = userId;

		location = location.replace("POINT","");
		location = location.replaceAll("[()]","");
		String tmp[] = location.split(" ");
		this.latitude = Double.parseDouble(tmp[0]);
		this.longitude = Double.parseDouble(tmp[1]);

		this.content = content;
		this.createAt = DateFormatter.stringToDateTime(createAt,"yyyy-MM-dd HH:mm:ss");
		this.favorite = favorite;
	}

	/**
	 * 作成された時間が自動で挿入される
	 * @param userId
	 * @param latitude
	 * @param longitude
	 * @param content
	 */
	public Article (long userId,
					double latitude,
					double longitude,
					String content) {
		this.userId = userId;
		this.latitude = latitude;
		this.longitude = longitude;
		this.content = content;
		this.createAt = LocalDateTime.now();
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
		return content == null ? "" : content;
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
//		return createAt;
		return DateFormatter.dateTimeToString(createAt);
	}

	/**
	 * 投稿日時の設定
	 * @param createAt
	 */
	public void setCreateAt(LocalDateTime createAt) {
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
	public double getLatitude() {
		return latitude;
	}

	/**
	 * X軸の設定
	 * @param latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Y軸の取得
	 * @return Y軸
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * Y軸の設定
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return userId + " " + postId + " " + latitude + " " + longitude + " " + content + " "  + createAt;
	}

	public boolean isFavorite() {
		return favorite;
	}

	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
}
