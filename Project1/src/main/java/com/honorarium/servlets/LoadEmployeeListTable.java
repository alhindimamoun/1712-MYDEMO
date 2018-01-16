package com.honorarium.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/emptablestructure")
public class LoadEmployeeListTable extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2970522922033579775L;

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("features/employeeTable.html").forward(req, resp);
	}
}
