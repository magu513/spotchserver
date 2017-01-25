package com.javatea.spotchserver;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class User {
	private long userId;

	private List<Article> articles;

	private String userName;
	private String email;
	private Date birthDate;
	private Date createAt;

	private byte status;

	public User(long userId,
				String userName,
				String email,
				Date birthDate,
				Date createAt) {
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.birthDate = birthDate;
		this.createAt = createAt;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date registerDate) {
		this.createAt = registerDate;
	}

	public Iterator<Article> getArticles() {
		return articles.iterator();
	}

	public void addArticles(Article article) {
		articles.add(article);
	}
}
