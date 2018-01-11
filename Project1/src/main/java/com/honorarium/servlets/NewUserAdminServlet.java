package com.honorarium.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/newuseradmin")
public class NewUserAdminServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7062089217958596889L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		req.getRequestDispatcher("features/newUserEmployee.html").forward(req, resp);;
	}

	
}
