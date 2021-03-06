package com.honorarium.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.honorarium.DTO.ReimbursementDTO;
import com.honorarium.DTO.UserDTO;
import com.honorarium.service.ReimbursmentService;

@WebServlet("/getadmintickets")
public class AllManagerTickets extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3699238198808969758L;
	
	private ReimbursmentService rService = ReimbursmentService.getReimbursmentService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");
		List<ReimbursementDTO> tickets = rService.getAllManagerTickets();
		
		if(user.getUsername() != null && user.getType() == 1){
			
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(tickets);
			//System.out.println(json);
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			out.write(json);
		}else{
			resp.setStatus(418);
		}
	
	}
}
