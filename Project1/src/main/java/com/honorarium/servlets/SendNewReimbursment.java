package com.honorarium.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.honorarium.DTO.ReimbursementDTO;
import com.honorarium.DTO.UserDTO;
import com.honorarium.service.ReimbursmentService;

@WebServlet("/newticketsend")
@MultipartConfig
public class SendNewReimbursment extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5955671030328157823L;
	private ReimbursmentService reimServ  = ReimbursmentService.getReimbursmentService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Object amount = req.getParameter("amount");
		float amountFloat = Float.valueOf((String)amount);
		Object description = req.getParameter("description");
		Object ticketType = req.getParameter("ticketType");
		int ticketTypeInt = getTicketTypeInt(ticketType);
		Part picture = req.getPart("picture");
		byte[] pictureInBytes = toByteArray(picture.getInputStream());
		HttpSession session = req.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");

		ReimbursementDTO ticket = new ReimbursementDTO(0, amountFloat, (String) description, pictureInBytes,
				new Timestamp(System.currentTimeMillis()), null,
				user.getFirstName(), user.getLastName(), "", "", "", "");

		System.out.println(amount);
		System.out.println(description);
		System.out.println(ticketType);
		
		int error = reimServ.newTicket(ticket,user.getUsername(), ticketTypeInt);
		
		if(error == 1) {
			req.getRequestDispatcher("features/ticketSent.html").forward(req, resp);;
		}
		
		else {
			req.getRequestDispatcher("features/ticketFail.html").forward(req, resp);;
		}
		

	}

	private byte[] toByteArray(InputStream in) {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();

		int nRead;
		byte[] data = new byte[16384];

		try {
			while ((nRead = in.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}
			buffer.flush();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return buffer.toByteArray();
	}

	private int getTicketTypeInt(Object ticketType) {

		String type = (String) ticketType;
		int intType = -1;

		switch (type) {
		case "Educational":
			intType = 0;
			break;
		case "Travel":
			intType = 1;
			break;
		case "Food":
			intType = 2;
			break;
		case "Gas":
			intType = 3;
			break;
		case "Other":
			intType = 4;
			break;
		}

		return intType;

	}
}