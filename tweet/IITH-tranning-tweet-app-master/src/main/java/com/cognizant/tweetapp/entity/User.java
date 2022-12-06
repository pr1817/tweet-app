package com.cognizant.tweetapp.entity;

import java.util.List;

public class User {

	private long id;
	
	private String firstname;
	
	private String password;
	
	private String email;
	
	private int isloggedin;
	
	private List<Tweet> tweets;

	public User(String firstname, String password, String email) {
		super();
		this.firstname = firstname;
		this.password = password;
		this.email = email;
	}

	public User(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getIsloggedin() {
		return isloggedin;
	}

	public void setIsloggedin(int isloggedin) {
		this.isloggedin = isloggedin;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {
		this.tweets = tweets;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", password=" + password + ", email=" + email
				+ ", isloggedin=" + isloggedin + ", tweets=" + tweets + "]";
	}
	
	
}
