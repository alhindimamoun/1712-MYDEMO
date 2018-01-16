package com.honorarium.doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.honorarium.DTO.UserDTO;
import com.honorarium.JB.UserBean;

/**
 * 
 * @author Mohamad Mamoun Alhindi
 * @version 0.0.1
 * @since January/5/2018
 *
 */

/**
 * <h1>Login Database Access Object(SINGLETON)</h1> This class is specifically
 * used by the service layer to pull any information from the database
 * specifically for the user login
 * 
 *
 */
public class UserDAO {

	private static final String SURL = "jdbc:oracle:thin:@usfdbjava.cxqypwex6ekz.us-east-2.rds.amazonaws.com:1521:orcl";
	private static final String SUSERNAME = "databaseadmin";
	private static final String SSERVERPASS = "honorarium";
	private static UserDAO lgd = new UserDAO();

	/**
	 * <h1>GET USERS METHOD</h1>
	 * 
	 * This method gives back a {@code USER} from the database given a
	 * {@code USERNAME} and {@code PASSWORD} if the {@code USERNAME} &
	 * {@code PASSWORD} combination does not exist the method will return
	 * {@code NULL}
	 * 
	 * @param userName
	 * @param password
	 * @return <b>User(JavaBean)</b>
	 */

	private UserDAO() {
	}

	static {

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}

	}

	public static final UserDAO getLoginDAO() {
		return lgd;
	}

	/**
	 * Select Method for service layer used to retrieve User
	 *
	 * @param userName
	 * @param password
	 * @return UserDTO
	 */
	public UserBean getUser(String userName) {
		UserBean u = null;

		try (Connection conn = DriverManager.getConnection(SURL, SUSERNAME, SSERVERPASS)) {
			String sql = "Select u_username,u_password,u_firstname,u_lastname,u_email,ur_id from honorarium_users where u_username=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return new UserBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getInt(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}
	
	public int getUserID(String userName) {
		
		int userID = -1;

		try (Connection conn = DriverManager.getConnection(SURL, SUSERNAME, SSERVERPASS)) {
			String sql = "Select u_id from honorarium_users where u_username=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				userID = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return userID;
	}
	
	public List<UserDTO> getAllUsersByType(int type) {
		List<UserDTO> u = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(SURL, SUSERNAME, SSERVERPASS)) {
			String sql = "Select u_username,u_firstname,u_lastname,u_email,ur_id from honorarium_users where ur_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, type);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				u.add (new UserDTO(rs.getString(1), rs.getString(2), 
						rs.getString(3), rs.getString(4),rs.getInt(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return u;
	}
	
	public void insertUser(UserBean user) {
		
		try (Connection conn = DriverManager.getConnection(SURL, SUSERNAME, SSERVERPASS)) {
			String sql = "INSERT INTO honorarium_users values(null,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstname());
			ps.setString(4, user.getLastname());
			ps.setString(5, user.getEmail());
			ps.setInt(6, user.getType());
			ps.executeUpdate();
		}
			catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void updatePassword(String newPass,String oldPass)
	{
		try (Connection conn = DriverManager.getConnection(SURL, SUSERNAME, SSERVERPASS)) {
			String sql = "UPDATE honorarium_users SET u_password = ? WHERE u_username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, oldPass);
			ps.setString(2, newPass);
		}
			catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(String username){
		
		
		try (Connection conn = DriverManager.getConnection(SURL, SUSERNAME, SSERVERPASS)) {
			String sql = "DELETE FROM honorarium_users WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.executeUpdate();
			
		}
			catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
