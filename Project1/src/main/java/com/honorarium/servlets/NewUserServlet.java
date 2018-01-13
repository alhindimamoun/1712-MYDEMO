package com.honorarium.servlets;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.honorarium.JB.UserBean;
import com.honorarium.service.NewUserLogic;


@WebServlet("/newuser")
public class NewUserServlet extends HttpServlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2916749416642865531L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		req.getRequestDispatcher("features/newUser.html").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		NewUserLogic nwl = NewUserLogic.getNewUserLogic();
		
		Map<String,String[]> myMap = req.getParameterMap();
		
		Set<String> userObject = myMap.keySet();
		
		
		Object object = userObject.toArray()[0];
		
		
		ObjectMapper jackson = new ObjectMapper();
		
		UserBean userBean = jackson.readValue((String)object, UserBean.class);
		
		
		boolean exist = nwl.insertNewUser(userBean);
		
		if(exist)
		{

			System.out.println("exist");
			HttpSession session = req.getSession();
			session.setAttribute("exist", true);
		}
		
		else {
			HttpSession session = req.getSession();
			session.setAttribute("exist", false);
		}
	}
		
	
}
