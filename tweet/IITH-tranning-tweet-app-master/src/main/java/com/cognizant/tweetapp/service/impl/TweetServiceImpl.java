package com.cognizant.tweetapp.service.impl;

import java.util.List;
import java.util.Optional;

import com.cognizant.tweetapp.dao.TweetDao;
import com.cognizant.tweetapp.dao.impl.TweetDaoImpl;
import com.cognizant.tweetapp.entity.Tweet;
import com.cognizant.tweetapp.entity.User;
import com.cognizant.tweetapp.service.TweetService;

public class TweetServiceImpl implements TweetService{
	
	TweetDao dao=new TweetDaoImpl();

	@Override
	public boolean login(User user) {
		return dao.login(user);
	}

	@Override
	public String register(User user) {
		return dao.register(user);
	}

	@Override
	public String forgotPassword(String email) {
		return dao.forgotPassword(email);
	}

	@Override
	public boolean logout(User user) {
		
		return dao.logout(user);
	}

	@Override
	public boolean resetPassword(User user, String newPassword) {
		
		return dao.resetPassword(user,newPassword);
	}

	@Override
	public boolean postTweet(String message, User user) {
		return dao.postTweet(message,user);
	}

	@Override
	public List<Tweet> viewAllTweets(User loggedUser) {
		
		return dao.viewAllTweets(loggedUser);
	}

	@Override
	public List<User> viewAllTweetsOfUsers() {
		return dao.viewAllTweetsOfUsers();
	}

	@Override
	public Optional<Tweet> viewMyTweet(User loggedUser, int id) {
		
		return dao.viewMyTweet(loggedUser, id);
	}

}
