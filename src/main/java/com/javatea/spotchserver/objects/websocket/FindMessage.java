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

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public String toString() {
		return latitude + " " + latitude + " " + range;
	}
}
