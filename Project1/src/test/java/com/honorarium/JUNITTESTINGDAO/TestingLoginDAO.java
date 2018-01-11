package com.honorarium.JUNITTESTINGDAO;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.honorarium.JB.UserBean;
import com.honorarium.doa.UserDAO;


class TestingLoginDAO {
	
	UserDAO loginTester = UserDAO.getLoginDAO();
	
	@Test
	void gettingUsersValidation() {
		
		//both incorrect username and password
		UserBean fail = loginTester.getUser("fail"); 
		assertNull(fail);
		
		//correct username and password
		UserBean pass = loginTester.getUser("TEST");
		assertNotNull(pass);
		
		//correct username wrong
		fail = loginTester.getUser("TEST");
		assertNull(fail);
		
		//correct password wrong username
		fail = loginTester.getUser("fail");
		assertNull(fail);
	}

}
