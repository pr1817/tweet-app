package com.cognizant.tweetapp.client;

import java.util.List;
import java.util.Optional;

import com.cognizant.tweetapp.dao.impl.TweetDaoImpl;
import com.cognizant.tweetapp.entity.Tweet;
import com.cognizant.tweetapp.entity.User;
import com.cognizant.tweetapp.service.TweetService;
import com.cognizant.tweetapp.service.impl.TweetServiceImpl;
import com.cognizant.tweetapp.utility.Utility;

public class TweetApp {
	public static void main(String[] args) {
		boolean flag = true;
		TweetService twtService = new TweetServiceImpl();
		do {
			System.out.println("\n\n------------------WELCOM TO TWEET APP------------------------------");
			System.out.println("1.Register");
			System.out.println("2.Login");
			System.out.println("3.Forgot Password");
			System.out.println("4.Exit\n\n");
			System.out.println("Please choose Option");
			int option = Utility.getInteger();
			switch (option) {
			case 1: {
				System.out.println("Enter name");
				String name = Utility.getString().trim();
				System.out.println("Enter email");
				String email = Utility.getString().trim();
				System.out.println("Enter password");
				String password = Utility.getString().trim();
				User user = new User(name, password, email);
				String status = twtService.register(user);
				System.out.println(status);
				break;
			}
			case 2: {
				System.out.println("Enter email");
				String email = Utility.getString().trim();
				System.out.println("Enter password");
				String password = Utility.getString().trim();
				User user = new User(email, password);
				boolean status = twtService.login(user);
				if (status) {
					System.out.println("You are succsfully login");
					User loggedUser = TweetDaoImpl.getUser(user.getEmail());
					do {
						System.out.println("1.Post a Tweet");
						System.out.println("2.View My Tweet");
						System.out.println("3.View All Tweets");
						System.out.println("4.View All Users");
						System.out.println("5.Reset Password");
						System.out.println("6.Logout\n\n");
						System.out.println("Please choose Option");
						int op = Utility.getInteger();
						switch (op) {
						case 1: {
							System.out.println("Enter Your Tweet");
							String message = Utility.getString();
							boolean isPosted = twtService.postTweet(message, loggedUser);
							if (isPosted)
								System.out.println("Your Tweet has succsfully posted");
							else
								System.err.println("Your Tweet not posted .. Somthing went wrong !");
							break;
						}
						case 2: {
							System.out.println("Enter the Tweet Id");
							int id = Utility.getInteger();
							Optional<Tweet> twe = twtService.viewMyTweet(loggedUser, id);
							if (twe.isPresent()) {
								Tweet tweet = twe.get();
								System.out.println("ID: "+tweet.getId());
								System.out.println("MESSAGE: " + tweet.getMessage());
								System.out.println("POSTED AT: : " + tweet.getPostedAt() + "\n");
							} else {
								System.err.println("Tweet Not Present with Id " + id);
							}
							break;
						}
						case 3: {
							List<Tweet> tweets = twtService.viewAllTweets(loggedUser);
							tweets.forEach(tweet -> {
								System.out.println("MESSAGE: " + tweet.getMessage());
								System.out.println("POSTED AT: : " + tweet.getPostedAt() + "\n");
							});
							break;
						}
						case 4: {
							List<User> users = twtService.viewAllTweetsOfUsers();
							for (int i = 0; i < users.size(); i++) {
								User u = users.get(i);
								System.out.println("ID: " + u.getId());
								System.out.println("FIRST NAME: " + u.getFirstname());
								System.out.println("EMAIL: " + u.getEmail() + "\n");
								List<Tweet> tweets = u.getTweets();
								for (int j = 0; j < tweets.size(); j++) {
									Tweet tweet = tweets.get(j);
									System.out.println("               MESSAGE: " + tweet.getMessage());
									System.out.println("               POSTED AT: : " + tweet.getPostedAt() + "\n");
								}
							}
							break;
						}
						case 5: {
							System.out.println("Enter new password");
							String newPassword = Utility.getString().trim();
							boolean isReset = twtService.resetPassword(user, newPassword);
							if (isReset)
								System.out.println("Password Changed succfully ..");
							else
								System.err.println("Somthing went wrong re-try again !");
							break;
						}
						case 6: {
							status = false;
							boolean isLoggedout = twtService.logout(user);
							if (isLoggedout)
								System.out.println("Logout succsfully..");
							else
								System.err.println("Somthing went wrong ..");
							break;
						}
						default:
							System.err.println("Invalid Option! re-try again...");
							break;
						}
					} while (status);
				} else {
					System.err.println("Please Enter Valid Creditials");
				}
				break;
			}
			case 3: {
				System.out.println("Enter the email to get password");
				String email = Utility.getString().trim();
				String password = twtService.forgotPassword(email);
				System.out.println("Your Password is : " + password);
				break;
			}
			case 4: {
				flag = false;
				System.out.println("Thank You ....");
				break;
			}
			default:
				System.err.println("Invalid Option! re-try again...");
				break;
			}

		} while (flag);
	}
}
