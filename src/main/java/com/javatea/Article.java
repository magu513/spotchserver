package com.javatea;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Article")
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long postId;

	@ManyToOne
	private User user;

	private String content;
	private double locationX;
	private double locationY;
	private Date postDate;

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

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
