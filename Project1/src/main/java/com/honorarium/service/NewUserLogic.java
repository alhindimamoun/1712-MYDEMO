package com.honorarium.service;

import com.honorarium.JB.UserBean;
import com.honorarium.doa.UserDAO;

public class NewUserLogic {

	private static NewUserLogic newUserLogic = new NewUserLogic();
	private UserDAO userDAO = UserDAO.getLoginDAO();
	
	private NewUserLogic() {	
	}
	
	public static NewUserLogic getNewUserLogic() {
		return newUserLogic;
	}
	
	public boolean insertNewUser(UserBean newUser){
		
		boolean exist = userDAO.getUser(newUser.getUsername()) != null;
		
		if(!exist)
		{
			userDAO.insertUser(newUser);
			return false;
		}
		
		else
		{
			return true;
		}
	}
}
