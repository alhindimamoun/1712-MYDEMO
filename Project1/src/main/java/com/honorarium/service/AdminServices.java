package com.honorarium.service;

import java.sql.Timestamp;
import java.util.List;

import com.honorarium.DTO.UserDTO;
import com.honorarium.doa.ReimbursmentDAO;
import com.honorarium.doa.UserDAO;

public class AdminServices {

	private static AdminServices adminService = new AdminServices();
	private UserDAO userDAO = UserDAO.getLoginDAO();
	private ReimbursmentDAO reiDAO = ReimbursmentDAO.getReimbursmentDAO();
	
	
	private AdminServices() {
	}
	
	public static AdminServices getAdminServices() {
		return adminService;
	}
	
	public List<UserDTO> getAllEmployee() {
	
		return userDAO.getAllUsersByType(0);
	}
	
	public int ticketHanlder(int type, int ticketNumber,String resolverUsername){
		
		int resolverID = userDAO.getUserID(resolverUsername);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		return reiDAO.ticketUpdate(ticketNumber, resolverID, time, type);
		

	}
	
}
