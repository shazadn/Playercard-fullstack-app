package com.qa.user;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.qa.model.User;

public class UserTest {
	User user;
	
	
	@BeforeClass
	public static void initial() {
		System.out.println("Start Report");
	}
	

	@Before
	public void setup() {
		System.out.println("Begin");
		user = new User();
	}
	
	
	@Test
	public void userName() {
		user.setUserName("Shazad");
		assertEquals("UserName error","Shazad", user.getUserName());
	}
	
}
