package com.cognizant.tweetapp.dao;

import java.util.List;
import java.util.Optional;

import com.cognizant.tweetapp.entity.Tweet;
import com.cognizant.tweetapp.entity.User;

public interface TweetDao {

	boolean login(User user);

	String register(User user);

	String forgotPassword(String email);

	boolean logout(User user);

	boolean resetPassword(User user, String newPassword);

	boolean postTweet(String message, User user);

	List<Tweet> viewAllTweets(User loggedUser);

	List<User> viewAllTweetsOfUsers();

	Optional<Tweet> viewMyTweet(User loggedUser, int id);

}
