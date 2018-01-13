package com.honorarium.JUNITTESTINGDAO;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.honorarium.DTO.ReimbursementDTO;
import com.honorarium.doa.ReimbursmentDAO;

class TestingReimbursmentsDAO {

	@Test
	void test() {
		ReimbursmentDAO reimbursmentDAO = ReimbursmentDAO.getReimbursmentDAO();
		List<ReimbursementDTO> ticketList = reimbursmentDAO.getAllEmployeeTicketsByUsername("mmalhind");
		
		for(ReimbursementDTO r: ticketList)
		{
			System.out.println(r.toString());
		}
	}

}
