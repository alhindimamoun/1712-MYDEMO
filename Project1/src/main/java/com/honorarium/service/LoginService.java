package com.honorarium.service;

import com.honorarium.DTO.UserDTO;
import com.honorarium.JB.UserBean;
import com.honorarium.doa.UserDAO;

public class LoginService {
	public static LoginService lis = new LoginService();
	UserDAO loginDAO = UserDAO.getLoginDAO();

	private LoginService() {
	}

	public static LoginService getLoginService() {
		return lis;
	}

	public UserDTO checkLogin(String username, String password) {

		UserBean user = loginDAO.getUser(username);

		if (user != null) {
			if (password.equals(user.getPassword())) {
				return dtoTransformer(user);
			}
		}
		return null;
	}

	public UserDTO dtoTransformer(UserBean user) {
		String username = user.getUsername();
		String firstname = user.getFirstname();
		String lastname = user.getLastname();
		String email = user.getEmail();
		int type = user.getType();

		return new UserDTO(username, firstname, lastname, email, type);
	}

}
