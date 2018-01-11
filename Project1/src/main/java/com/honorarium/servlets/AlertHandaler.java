package com.honorarium.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/alert")
public class AlertHandaler extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4888824693891503815L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		boolean alert = (boolean) session.getAttribute("exist");
		
		if(alert)
		{
			req.getRequestDispatcher("features/fail.html").forward(req, resp);
		}
		
		else
		{
			req.getRequestDispatcher("features/success.html").forward(req, resp);
		}
	}

}
