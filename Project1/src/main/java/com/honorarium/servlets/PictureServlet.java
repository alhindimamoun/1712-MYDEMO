package com.honorarium.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/showpicture")
public class PictureServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2193679765224426954L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("features/showPicture.html").forward(req, resp);
	}
}
