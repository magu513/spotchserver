package com.javatea.spotchserver.objects.websocket;

/**
 * 検索用MessageのJSONを読み込むためのクラス
 */
public class FindMessage {
	/** 緯度 */
	private double latitude;
	/** 経度 */
	private double longitude;
	/** 範囲 */
	private int range;

	/**
	 * 緯度を取得する
	 * @return 緯度
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * 緯度を設定する
	 * @param latitude 緯度
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * 経度を取得する
	 * @return 経度
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * 経度を設定する
	 * @param longitude 経度
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * 投稿を取得する範囲を取得する
	 * @return 範囲
	 */
	public int getRange() {
		return range;
	}

	/**
	 * 投稿を取得する範囲を設定する
	 * @param range 範囲
	 */
	public void setRange(int range) {
		this.range = range;
	}

	public String toString() {
		return latitude + " " + latitude + " " + range;
	}
}
