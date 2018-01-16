package com.honorarium.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.honorarium.DTO.TicketHandlerDTO;
import com.honorarium.DTO.UserDTO;
import com.honorarium.service.AdminServices;

@WebServlet("/tickethandler")
public class TicketHandler extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8965300321441568592L;
	
	private AdminServices adminServices = AdminServices.getAdminServices();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		Map<String,String[]> myMap = req.getParameterMap();
		
		Set<String> ticketSet = myMap.keySet();
		
		Object ticketObject = ticketSet.toArray()[0];
		
		ObjectMapper jackson = new ObjectMapper();
		
		TicketHandlerDTO ticket = jackson.readValue(((String)ticketObject), TicketHandlerDTO.class);
		
		
		HttpSession session = req.getSession();
		
		UserDTO user = (UserDTO) session.getAttribute("user");
		
		Integer x = adminServices.ticketHanlder(ticket.getTicketType(), ticket.getTicketNumber(),user.getUsername());
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(x);
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		out.write(json);
			
	}

}
