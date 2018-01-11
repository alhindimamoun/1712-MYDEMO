package com.honorarium.JB;
import java.io.Serializable;

public class UserBean implements Serializable {

	/**
	 * <h1>VERSION UID FOR SERIALIZATION OF OBJECT</h1>
	 */
	private static final long serialVersionUID = 8489037924599432501L;

	/**
	 * <h1>Instance Variables of the User Java Bean</h1> 
	 */
	private String username;
	private String password;
	private String email;
	private String firstname;
	private String lastname;
	private int type;
	
	public UserBean() {
		
	}
	
	public UserBean(String username, String password, String firstname, String lastname,String email,int type) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.type = type;
	}



	public String getUsername() {
		return username;
	}



	public String getPassword() {
		return password;
	}



	public String getEmail() {
		return email;
	}



	public String getFirstname() {
		return firstname;
	}



	public String getLastname() {
		return lastname;
	}
	
	public int getType() {
		return type;
	}
	
	



	@Override
	public String toString() {
		String p = "";
		p = p.concat(password.substring(0,1));
		for(int i = 1;i<password.length();i++) {
			p = p.concat(("*"));
		}
		return "Username: " + username + "\nPassword: " + p; 
	}
	
}
