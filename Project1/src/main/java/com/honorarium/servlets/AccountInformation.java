package com.honorarium.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.honorarium.DTO.UserDTO;

@WebServlet("/getaccountinfo")
public class AccountInformation extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7883623791496923583L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		UserDTO sessionUser = (UserDTO) session.getAttribute("user");
		
			if(sessionUser !=null){
				
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(sessionUser);
				System.out.println(json);
				PrintWriter out = resp.getWriter();
				resp.setContentType("application/json");
				out.write(json);
			}else{
				resp.setStatus(418);
			}}
	}


