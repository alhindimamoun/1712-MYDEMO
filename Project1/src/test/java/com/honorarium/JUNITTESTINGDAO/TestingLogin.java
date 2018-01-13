//package com.honorarium.JUNITTESTINGDAO;
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//
//import org.junit.Test;
//
//import com.honorarium.JB.UserBean;
//import com.honorarium.doa.UserDAO;
//
//public class TestingLogin {
//	
//	UserDAO loginTester = UserDAO.getLoginDAO();
//	
//	@Test
//	public void gettingUsersValidation() {
//		
//		//both incorrect username and password
//		UserBean fail = loginTester.getUser("fail"); 
//		assertNull(fail);
//		
//		//correct username and password
//		UserBean pass = loginTester.getUser("username");
//		assertNotNull(pass);
//
//	}
//
//}
