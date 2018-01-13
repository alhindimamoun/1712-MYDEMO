package com.honorarium.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.honorarium.DTO.UserDTO;
import com.honorarium.service.LoginService;

@WebServlet("/employerlogin")
public class EmployerLogin extends HttpServlet{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 7082615945051763855L;
	private LoginService lgs = LoginService.getLoginService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("features/employerLogin.html").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		UserDTO user = lgs.checkLogin(username,password);
		
		
			if(user != null && user.getType()==1)
			{	
				System.err.println("Login Sucessful");
				HttpSession session = req.getSession(); 
				session.setAttribute("user", user);
				req.getRequestDispatcher("mainPage.html").forward(req, resp);
			}
			
			else {
				System.err.println("username/password missmatch");
				req.getRequestDispatcher("Login.html").forward(req, resp);
			}

	}
}
