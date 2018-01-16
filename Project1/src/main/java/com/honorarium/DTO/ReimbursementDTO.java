package com.honorarium.DTO;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ReimbursementDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8301293937974642409L;
	
	private int ticketKey;
	private float amount;
	private String description;
	private byte[] picture;
	private Timestamp submitted;
	private Timestamp resolved;
	private String authorFirstname;
	private String authorLastname;
	private String resolverFirstname;
	private String resolverLastname;
	private String type;
	private String status;

	public ReimbursementDTO(int ticketKey,float amount, String description, byte[] picture, Timestamp submitted, Timestamp resolved,
			String authorFirstname, String authorLastname, String resolverFirstname, String resolverLastname,
			String type, String status) {
		this.ticketKey = ticketKey;
		this.amount = amount;
		this.description = description;
		this.picture = picture;
		this.submitted = submitted;
		this.resolved = resolved;
		this.authorFirstname = authorFirstname;
		this.authorLastname = authorLastname;
		this.resolverFirstname = resolverFirstname;
		this.resolverLastname = resolverLastname;
		this.type = type;
		this.status = status;
	}




	

	public int getTicketKey() {
		return ticketKey;
	}






	public void setTicketKey(int ticketKey) {
		this.ticketKey = ticketKey;
	}






	public float getAmount() {
		return amount;
	}





	public void setAmount(float amount) {
		this.amount = amount;
	}





	public String getDescription() {
		return description;
	}





	public void setDescription(String description) {
		this.description = description;
	}





	public byte[] getPicture() {
		return picture;
	}





	public void setPicture(byte[] picture) {
		
		this.picture = picture;
	}





	public String getSubmitted() {
		
		if(this.submitted != null) {
			return new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(this.submitted);
		}
		return null;
	}





	public void setSubmitted(Timestamp submitted) {
		
		
		this.submitted = submitted;
	}





	public String getResolved() {
		if(this.resolved != null) {
			return new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(this.resolved);
		}
		return null;
	}





	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}





	public String getAuthorFirstname() {
		return authorFirstname;
	}





	public void setAuthorFirstname(String authorFirstname) {
		this.authorFirstname = authorFirstname;
	}





	public String getAuthorLastname() {
		return authorLastname;
	}





	public void setAuthorLastname(String authorLastname) {
		this.authorLastname = authorLastname;
	}





	public String getResolverFirstname() {
		return resolverFirstname;
	}





	public void setResolverFirstname(String resolverFirstname) {
		this.resolverFirstname = resolverFirstname;
	}





	public String getResolverLastname() {
		return resolverLastname;
	}





	public void setResolverLastname(String resolverLastname) {
		this.resolverLastname = resolverLastname;
	}





	public String getType() {
		return type;
	}





	public void setType(String type) {
		this.type = type;
	}





	public String getStatus() {
		return status;
	}





	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		
		return this.getResolved() + "\n" + description;
	}
	
	

	

}
