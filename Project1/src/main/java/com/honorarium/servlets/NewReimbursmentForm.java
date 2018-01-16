package com.honorarium.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/newrequest")
public class NewReimbursmentForm extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7855074010709953782L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("features/newReimbursement.html").forward(req, resp);
	}
}

