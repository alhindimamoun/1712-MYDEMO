package com.honorarium.doa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.honorarium.DTO.ReimbursementDTO;

public class ReimbursmentDAO {
	
	private static final String SURL = "jdbc:oracle:thin:@usfdbjava.cxqypwex6ekz.us-east-2.rds.amazonaws.com:1521:orcl";
	private static final String SUSERNAME = "databaseadmin";
	private static final String SSERVERPASS = "honorarium";
	private static ReimbursmentDAO rd = new ReimbursmentDAO();
	
	static {

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		}

	}
	
	
	public static ReimbursmentDAO getReimbursmentDAO() {
		return rd;
	}
	

	public List<ReimbursementDTO> getAllEmployeeTicketsByUsername(String username) {
		
		List<ReimbursementDTO> allTickets = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(SURL, SUSERNAME, SSERVERPASS);){
			String sql = "SELECT r_amount,r_description,r_reciept,r_submitted,r_resolved,U.u_firstname,"
					+ "U.u_lastname,A.u_firstname,A.u_lastname,S.rs_status,T.rt_type FROM reimbursements R " + 
					"INNER JOIN honorarium_users u ON R.u_id_author = u.u_id " + 
					"INNER JOIN honorarium_users A ON R.u_id_resolver = A.u_id " + 
					"INNER JOIN reimbursement_status S ON R.rt_status = S.rs_id " + 
					"INNER JOIN reimbursement_type T ON R.rt_type = T.rt_id WHERE u.u_username = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				allTickets.add(new ReimbursementDTO(rs.getFloat(1),rs.getString(2),rs.getBlob(3),
						rs.getTimestamp(4),rs.getTimestamp(5),rs.getString(6),rs.getString(7),rs.getString(8),
						rs.getString(9),rs.getString(10),rs.getString(11)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return allTickets;
	}
	
	
	public List<ReimbursementDTO> getAllManagerTicketsByUsername(String username) {
		
		List<ReimbursementDTO> allTickets = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(SURL, SUSERNAME, SSERVERPASS);){
			String sql = "SELECT r_amount,r_description,r_reciept,r_submitted,r_resolved,U.u_firstname,"
					+ "U.u_lastname,A.u_firstname,A.u_lastname,S.rs_status,T.rt_type FROM reimbursements R " + 
					"INNER JOIN honorarium_users u ON R.u_id_author = u.u_id " + 
					"INNER JOIN honorarium_users A ON R.u_id_resolver = A.u_id " + 
					"INNER JOIN reimbursement_status S ON R.rt_status = S.rs_id " + 
					"INNER JOIN reimbursement_type T ON R.rt_type = T.rt_id WHERE a.u_username = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				allTickets.add(new ReimbursementDTO(rs.getFloat(1),rs.getString(2),rs.getBlob(3),
						rs.getTimestamp(4),rs.getTimestamp(5),rs.getString(6),rs.getString(7),rs.getString(8),
						rs.getString(9),rs.getString(10),rs.getString(11)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return allTickets;
	}
}
