package com.honorarium.service;

import java.util.List;

import com.honorarium.DTO.ReimbursementDTO;
import com.honorarium.doa.ReimbursmentDAO;

public class ReimbursmentService {
	
	
	private static ReimbursmentService rService = new ReimbursmentService();
	private ReimbursmentDAO rDAO = ReimbursmentDAO.getReimbursmentDAO();
	
	
	private ReimbursmentService() {
		
	}
	
	public static ReimbursmentService getReimbursmentService(){
		return rService;
	}
	
	
	public List<ReimbursementDTO> getAllUserTickets(String username){
		
		List<ReimbursementDTO> tickets = rDAO.getAllEmployeeTicketsByUsername(username);	
		return tickets;	
	}
	
	public List<ReimbursementDTO> getAllManagerTickets(String username){
		
		List<ReimbursementDTO> tickets = rDAO.getAllManagerTicketsByUsername(username);	
		return tickets;	
	}
	
	
	
}
