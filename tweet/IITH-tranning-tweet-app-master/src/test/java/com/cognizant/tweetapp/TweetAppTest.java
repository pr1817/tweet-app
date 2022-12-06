package com.cognizant.tweetapp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.cognizant.tweetapp.dao.TweetDao;
import com.cognizant.tweetapp.dao.impl.TweetDaoImpl;
import com.cognizant.tweetapp.entity.Tweet;
import com.cognizant.tweetapp.entity.User;

public class TweetAppTest {
	
	private TweetDao dao;

	@Before
	public void setUp() {
		dao=new TweetDaoImpl();
	}
	
	@Test
	public void loginSuccessFul() {
	User user = new User("er", "rtrt");
	boolean login = dao.login(user);
	assertTrue(login);

	}
	@Test
	public void loginFail() {
	User user = new User("unknown", "rtrt@gmailcom");
	boolean login = dao.login(user);
	assertFalse(login);

	}
	@Test
	public void logoutSuccessFul() {
	User user = new User("er", "rtrt");
	boolean login = dao.logout(user);
	assertTrue(login);
	}
	@Test
	public void logoutFail() {
	User user = new User("unknown", "rtrt@gmailcom");
	boolean login = dao.login(user);
	assertFalse(login);
	}
	
	@Test
	@Ignore
	public void register() {
		User user = new User("Marui", "1234hj","maruti@gmail.com");
		String registeredUser = dao.register(user);
		assertEquals(registeredUser, "User succesfully register");
	}
	@Test
	public void forgotPasswordSuccess() {
		String email="maruti@gmail.com";
		String forgotPassword = dao.forgotPassword(email);
		assertEquals(forgotPassword, "pass12");
	}
	@Test
	public void forgotPasswordFail() {
		String email="suresh@gmail.com";
		String forgotPassword = dao.forgotPassword(email);
		assertNull(forgotPassword);
	}
	
	@Test
	public void resetPasswordSuccess() {
		User user = new User("Marui", "pass12","maruti@gmail.com");
		String newPass="pass12";
		boolean status = dao.resetPassword(user, newPass);
		assertTrue(status);
		
	}
	@Test
	public void resetPasswordFail() {
		User user = new User("ravi", "1234hj12","ravi@gmail.com");
		String newPass="pass12";
		boolean status = dao.resetPassword(user, newPass);
		assertFalse(status);
		
	}
	
	@Test
	public void postTweetSuccess() {
		String message="Hi i am testing ";
		User user = new User("Marui", "pass12","maruti@gmail.com");
		user.setId(10);
		boolean postTweeted = dao.postTweet(message, user);
		assertTrue(postTweeted);

	}
	@Test
	public void getUserSuccess() {
		String email="maruti@gmail.com";
		User user = TweetDaoImpl.getUser(email);
		assertEquals("maruti@gmail.com", user.getEmail());
		assertEquals("Marui", user.getFirstname());

	}
	@Test
	public void getUserFail() {
		String email="anna@gmail.com";
		User user = TweetDaoImpl.getUser(email);
		assertNull(user.getEmail());
		assertNull(user.getPassword());

	}
	
	@Test
	public void viewAllTweets() {
		User user = new User("er", "rtrt");
		List<Tweet> viewAllTweets = dao.viewAllTweets(user);
		assertTrue(viewAllTweets.size()>0);
	}
	@Test
	public void viewAllTweetsOfUsers() {
		List<User> viewAllTweets = dao.viewAllTweetsOfUsers();
		assertTrue(viewAllTweets.size()>0);
	}
	@Test
	public void viewMyTweet() {
		User user = new User("er", "rtrt");
		Optional<Tweet> tweet = dao.viewMyTweet(user,1);
		assertEquals("Hi",tweet.get().getMessage());
	}
}
