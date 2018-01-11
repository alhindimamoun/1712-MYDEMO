package com.honorarium.DTO;

import java.io.Serializable;

public class UserDTO implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3067098854821710529L;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private int type;
	
	public UserDTO(String username,String firstname,String lastname,String email,int type)
	{
		this.username = username;
		this.firstName = firstname;
		this.lastName = lastname;
		this.email = email;
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}
	
	public int getType() {
		return type;
	}
	
	@Override
	public String toString() {
		
		return "Firstname: "+ firstName +  "\nLastname: " + lastName + "\nEmail: " + email + "\nUsername: " + username;
	}
	
	
	
	
}
