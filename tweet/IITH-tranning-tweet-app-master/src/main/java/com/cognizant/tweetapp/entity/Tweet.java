package com.cognizant.tweetapp.entity;

import java.time.LocalDateTime;

public class Tweet {

	private long id;
	
	private String message;
	
	private LocalDateTime postedAt;
	
	

	public Tweet() {
	
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(LocalDateTime postedAt) {
		this.postedAt = postedAt;
	}

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", message=" + message + ", postedAt=" + postedAt + "]";
	}
	
	
}
