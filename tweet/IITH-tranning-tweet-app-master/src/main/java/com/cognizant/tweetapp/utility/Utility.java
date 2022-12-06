package com.cognizant.tweetapp.utility;


import java.util.Scanner;

public class Utility {
 final static Scanner sc=new Scanner(System.in);
	
	public static int getInteger() {
		 int input = sc.nextInt();
		 sc.nextLine();
		return input;
	}
	
	public static String getString() {
		return sc.nextLine();
	}
	

}
