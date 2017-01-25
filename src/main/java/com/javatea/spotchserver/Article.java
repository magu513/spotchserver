package com.javatea.spotchserver;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Article {
	private long postId;

	private long userId;

	private double locationX;
	private double locationY;

	private String content;
	private String createAt;

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
		this.locationX = Double.parseDouble(tmp[0]);
		this.locationY = Double.parseDouble(tmp[1]);

		this.content = content;
		this.createAt = createAt;
	}

	public Article (long userId,
					double x,
					double y,
					String content) {
		this.userId = userId;
		this.locationX = x;
		this.locationY = y;
		this.content = content;
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:dd");
		this.createAt = sdf.format(c.getTime());
	}

	public long getPostId() {
		return postId;
	}

	public void setPostId(long postId) {
		this.postId = postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreateAt() {
		return createAt;
	}

	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public double getLocationX() {
		return locationX;
	}

	public void setLocationX(double locationX) {
		this.locationX = locationX;
	}

	public double getLocationY() {
		return locationY;
	}

	public void setLocationY(double locationY) {
		this.locationY = locationY;
	}
}
