package com.honorarium.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.honorarium.JB.UserBean;
import com.honorarium.service.NewUserLogic;

@WebServlet("/newuseradmin")
public class NewUserAdminServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7062089217958596889L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		req.getRequestDispatcher("features/newUserEmployer.html").forward(req, resp);;
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		NewUserLogic nwl = NewUserLogic.getNewUserLogic();
		String username = "";
		
		String jsonString ="";
		String line = "";
		BufferedReader reader = req.getReader();
		while ((line = reader.readLine()) != null) {
			jsonString += line;
		}
		
		JsonFactory factory = new JsonFactory();
		JsonParser parser = factory.createParser(jsonString);
		
		while (!parser.isClosed()) {
			JsonToken jsonToken = parser.nextToken();
			if(jsonToken.FIELD_NAME.equals(jsonToken))
			{
				String fieldname = parser.getCurrentName();
				jsonToken = parser.nextToken();
				
				switch(fieldname) {
				case "username":
					username = parser.getValueAsString();
					break;
				}
			}
			
			
//			System.out.println("Current Name " + parser.getCurrentName());
//			System.out.println("Token " + parser.getValueAsString());
			
		}
		
		System.out.println(username);

		
		
		
		
		
//		if(exist)
//		{
//
//			System.out.println("exist");
//			HttpSession session = req.getSession();
//			session.setAttribute("exist", true);
//		}
//		
//		else {
//			HttpSession session = req.getSession();
//			session.setAttribute("exist", false);
//		}
	}

	
}
