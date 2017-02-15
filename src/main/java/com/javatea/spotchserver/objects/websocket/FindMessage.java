package com.javatea.spotchserver.objects.websocket;

public class FindMessage {
	private double latitude;
	private double longitude;
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
