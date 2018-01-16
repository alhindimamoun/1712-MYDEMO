package com.honorarium.DTO;

import java.io.Serializable;

public class TicketHandlerDTO implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8328342767072870648L;
	
	private int ticketNumber;
	private int ticketType;
	
	
	public TicketHandlerDTO() {
		
	}
	
	public TicketHandlerDTO(int ticketNumber, int ticketType) {
		this.ticketNumber = ticketNumber;
		this.ticketType = ticketType;
	}
	
	
	public int getTicketNumber() {
		return ticketNumber;
	}
	
	public void setTicketNumber(int tikcetNumber) {
		this.ticketNumber = tikcetNumber;
	}
	
	public int getTicketType() {
		return ticketType;
	}
	
	public void setTicketType(int ticketType) {
		this.ticketType = ticketType;
	}
	
	
}
