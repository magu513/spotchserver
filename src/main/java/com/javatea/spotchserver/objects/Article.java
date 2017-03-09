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
	/** 位置情報経度*/
	private double longitude;

	/**  投稿内容 */
	private String content;
	/** 投稿（作成）日時 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createAt;
	/** お気に入り判定 */
	private boolean favorite;
	/** お気に入り数 */
	private int favCount;

	/**
	 * デフォルトコンストラクタ
	 */
	public Article() {}

	/**
	 * 位置情報を文字列として渡すことで、自動で日付型に変換する.
	 * @param postId 投稿ID
	 * @param userId ユーザーID
	 * @param location DBから取得した状態の位置情報の文字列
	 * @param content 投稿内容
	 * @param createAt 投稿日時
	 * @param favorite お気に入りの成否
	 * @param favCount お気に入りの総数
	 */
	public Article(long postId,
				   long userId,
				   String location,
				   String content,
				   String createAt,
				   boolean favorite,
				   int favCount) {

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
		this.favCount = favCount;
	}

	/**
	 * 作成された時間が自動で挿入される
	 * @param userId ユーザーID
	 * @param latitude 緯度
	 * @param longitude 経度
	 * @param content 投稿内容
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
	 * @return 投稿ID
	 */
	public long getPostId() {
		return postId;
	}

	/**
	 * 投稿IDの取得
	 * @param postId 投稿ID
	 */
	public void setPostId(long postId) {
		this.postId = postId;
	}

	/**
	 * 投稿内容の取得
	 * @return 投稿内容
	 */
	public String getContent() {
		return content == null ? "" : content;
	}

	/**
	 * 投稿内容の設定
	 * @param content 投稿内容
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
	 * @param createAt 投稿日時
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
	 * @param userId ユーザーID
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 緯度の取得
	 * @return 緯度
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * 緯度の設定
	 * @param latitude 緯度
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * 経度の取得
	 * @return 経度
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * 経度の設定
	 * @param longitude 経度
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return userId + " " + postId + " " + latitude + " " + longitude + " " + content + " "  + createAt;
	}

	/**
	 * お気に入りの成否を返す
	 * @return お気に入りの成否
	 */
	public boolean isFavorite() {
		return favorite;
	}

	/**
	 * お気に入りの成否を登録する
	 * @param favorite お気に入りの成否
	 */
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}

	/**
	 * お気に入り総数を返す
	 * @return お気に入り総数
	 */
	public int getFavCount() {
		return favCount;
	}

	/**
	 * お気に入りの総数を設定する
	 * @param favCount お気に入りの総数
	 */
	public void setFavCount(int favCount) {
		this.favCount = favCount;
	}
}
