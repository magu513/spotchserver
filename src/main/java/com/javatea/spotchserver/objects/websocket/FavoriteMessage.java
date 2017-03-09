package com.javatea.spotchserver.objects.websocket;

/**
 * WebSocketでお気に入りに用いるメッセージを保持するクラス
 */
public class FavoriteMessage {
	/** ユーザID */
	private long userId;
	/**
	 * 投稿ID
	 */
	private long articleId;
	/** お気に入りの成否 */
	private boolean status;

	/**
	 * ユーザIDを取得する
	 * @return ユーザID
	 */
	public long getUserId() {
		return userId;
	}

	/**
	 * ユーザIDを登録する
	 * @param userId ユーザID
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * 投稿IDを取得する
	 * @return 投稿ID
	 */
	public long getArticleId() {
		return articleId;
	}

	/**
	 * 投稿IDを設定する
	 * @param articleId 投稿ID
	 */
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	/**
	 * お気に入り済みならtrue,お気に入りでないならfalseを返す
	 * @return status
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * お気に入り済みならtrue,お気に入りでないならfalseを設定する
	 * @param status status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
}
