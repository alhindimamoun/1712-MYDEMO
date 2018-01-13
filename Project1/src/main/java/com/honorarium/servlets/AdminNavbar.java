package com.honorarium.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adminnavbar")
public class AdminNavbar extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3179369643186204334L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("features/adminNavbar.html").forward(req, resp);
	}

}
