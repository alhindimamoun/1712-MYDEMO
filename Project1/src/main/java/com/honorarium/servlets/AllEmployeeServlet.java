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
import com.honorarium.DTO.UserDTO;
import com.honorarium.service.AdminServices;

@WebServlet("/getallusers")
public class AllEmployeeServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2619095637209678677L;
	
	private AdminServices adminService = AdminServices.getAdminServices();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");
		
		if(user.getUsername() != null && user.getType() == 1){
			
			List<UserDTO> users = adminService.getAllEmployee();
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(users);
			//System.out.println(json);
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			out.write(json);
		}else{
			resp.setStatus(418);
		}
	}

}
