package com.cognizant.tweetapp.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cognizant.tweetapp.dao.TweetDao;
import com.cognizant.tweetapp.entity.Tweet;
import com.cognizant.tweetapp.entity.User;
import com.cognizant.tweetapp.utility.Constants;

public class TweetDaoImpl implements TweetDao{

	@Override
	public boolean login(User user) {
	boolean status=false;
	String sql = new StringBuilder()
	.append("update user ")
	.append("set is_loggedin=?")
	.append(" where email=? and password=?")
	.toString();
	try(Connection connection=DriverManager.getConnection(Constants.url, Constants.username, Constants.password);
			PreparedStatement pt=connection.prepareStatement(sql);
			) {
		  pt.setBoolean(1, true);
		  pt.setString(2, user.getEmail());
		  pt.setString(3, user.getPassword());
		  int row=pt.executeUpdate();
		  if(row>0) status=true;
	} catch (SQLException e) {
		System.out.println(e.getMessage());
	}
		return status;
	}

	@Override
	public String register(User user) {
		String status=null;
		String sql=new StringBuilder()
				.append("insert into user(first_name,password,email)")
				.append("values(?,?,?)").toString();
		try(Connection connection=DriverManager.getConnection(Constants.url, Constants.username, Constants.password);
				PreparedStatement pt=connection.prepareStatement(sql)) {
			  pt.setString(1, user.getFirstname());
			  pt.setString(2, user.getPassword());
			  pt.setString(3, user.getEmail());
			  if(pt.executeUpdate()>0) status="User succesfully register";
			  else status="Something went wrong";
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return status;
	}

	@Override
	public String forgotPassword(String email) {
		String password=null;
		String sql = new StringBuilder().append("select password from user where email=?").toString();
		try(Connection connection=DriverManager.getConnection(Constants.url, Constants.username, Constants.password);
				PreparedStatement pt=connection.prepareStatement(sql);
		) {
			pt.setString(1, email);
			ResultSet rs=pt.executeQuery();
	          while (rs.next()) {
				password = rs.getString("password");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return password;
	}

	@Override
	public boolean logout(User user) {
		boolean status=false;
		String sql = new StringBuilder()
		.append("update user ")
		.append("set is_loggedin=?")
		.append(" where email=? and password=?")
		.toString();
		try(Connection connection=DriverManager.getConnection(Constants.url, Constants.username, Constants.password);
				PreparedStatement pt=connection.prepareStatement(sql);
				) {
			  pt.setBoolean(1, false);
			  pt.setString(2, user.getEmail());
			  pt.setString(3, user.getPassword());
			  int row=pt.executeUpdate();
			  if(row>0) status=true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return status;
	}

	@Override
	public boolean resetPassword(User user, String newPassword) {
		boolean status=false;
		String sql = new StringBuilder()
		.append("update user ")
		.append("set password=?")
		.append(" where email=? and password=?")
		.toString();
		try(Connection connection=DriverManager.getConnection(Constants.url, Constants.username, Constants.password);
				PreparedStatement pt=connection.prepareStatement(sql);
				) {
			  pt.setString(1, newPassword);
			  pt.setString(2, user.getEmail());
			  pt.setString(3, user.getPassword());
			  int row=pt.executeUpdate();
			  if(row>0) status=true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return status;
	}

	@Override
	public boolean postTweet(String message, User user) {
		boolean status=false;
		String sql=new StringBuilder()
				.append("insert into tweet(message,posted_at,id)")
				.append("values(?,?,?)").toString();
		try(Connection connection=DriverManager.getConnection(Constants.url, Constants.username, Constants.password);
				PreparedStatement pt=connection.prepareStatement(sql)) {
			  pt.setString(1, message);
			  pt.setString(2, LocalDateTime.now().toString());
			  pt.setInt(3, (int)user.getId());
			  if(pt.executeUpdate()>0) status=true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return status;
	}

	public static User getUser(String email) {
		User user=new User();
		String sql = new StringBuilder().append("select * from user where email=?").toString();
		try(Connection connection=DriverManager.getConnection(Constants.url, Constants.username, Constants.password);
				PreparedStatement pt=connection.prepareStatement(sql);
		) {
			pt.setString(1, email);
			ResultSet rs=pt.executeQuery();
			
	          while (rs.next()) {
	        	  user.setId(rs.getInt("id"));
	        	  user.setFirstname(rs.getString("first_name"));
	        	  user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return user;
		
	}

	@Override
	public List<Tweet> viewAllTweets(User loggedUser) {
		List<Tweet> tweets=new ArrayList<Tweet>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		//String sql = new StringBuilder().append("select id_tweet,message,posted_at from user u inner join tweet t on u.id=t.id where email=?").toString();
		
		String sql = new StringBuilder().append("select id_tweet,message,posted_at from tweet ").toString();
		try(Connection connection=DriverManager.getConnection(Constants.url, Constants.username, Constants.password);
				PreparedStatement pt=connection.prepareStatement(sql);
		) {
			pt.setString(1, loggedUser.getEmail());
			ResultSet rs=pt.executeQuery();
			Tweet t=null;
	          while (rs.next()) {
	        	  t=new Tweet();
	        	  t.setId(rs.getLong("tweet_id"));
	        	  t.setMessage(rs.getString("message"));
	        	  t.setPostedAt(LocalDateTime.parse(rs.getString("posted_at"),formatter));
				tweets.add(t);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return tweets;
	}

	@Override
	public List<User> viewAllTweetsOfUsers() {
		List<User> users=new ArrayList<User>();
		String sql = new StringBuilder().append("select * from user").toString();
		try(Connection connection=DriverManager.getConnection(Constants.url, Constants.username, Constants.password);
				PreparedStatement pt=connection.prepareStatement(sql);
		) {
			ResultSet rs=pt.executeQuery();
			User user=null;
	          while (rs.next()) {
	        	  user=new User(); 
	        	  user.setId(rs.getInt("id"));
	        	  user.setFirstname(rs.getString("first_name"));
	        	  user.setEmail(rs.getString("email"));
	        	  user.setTweets(viewAllTweets(user));
	        	  users.add(user);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return users;
	}

	@Override
	public Optional<Tweet> viewMyTweet(User loggedUser, int id) {
		System.out.println("view my tweet");
		
		List<Tweet> allTweets = viewAllTweets(loggedUser);
		return allTweets.stream()
				.filter(tw->tw.getId()==id)
				.findFirst();
	}
		
}
