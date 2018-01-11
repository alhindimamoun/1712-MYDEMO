package com.honorarium.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/existUser")
public class ExistingUser extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3074447495359368755L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("features/existlogin.html").forward(req,resp);
	}

}
