package com.honorarium.service;

import java.util.List;

import com.honorarium.DTO.ReimbursementDTO;
import com.honorarium.doa.ReimbursmentDAO;
import com.honorarium.doa.UserDAO;

public class ReimbursmentService {
	
	
	private static ReimbursmentService rService = new ReimbursmentService();
	private ReimbursmentDAO rDAO = ReimbursmentDAO.getReimbursmentDAO();
	private UserDAO uDAO = UserDAO.getLoginDAO();
	
	
	private ReimbursmentService() {
		
	}
	
	public static ReimbursmentService getReimbursmentService(){
		return rService;
	}
	
	
	public List<ReimbursementDTO> getAllUserTickets(String username){
		
		List<ReimbursementDTO> tickets = rDAO.getAllEmployeeTicketsByUsername(username);	
		return tickets;	
	}
	
	public List<ReimbursementDTO> getAllManagerTickets(){
		
		List<ReimbursementDTO> tickets = rDAO.getAllManagerTickets();	
		return tickets;	
	}
	
	public int newTicket(ReimbursementDTO ticket,String username ,int type) {
		
		int userID = uDAO.getUserID(username);
		return rDAO.ticketInsert(ticket, userID, type);
	}
	
	
	
}
