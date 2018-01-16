package com.honorarium.doa;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
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
			String sql = "SELECT r_id,r_amount,r_description,r_reciept,r_submitted,r_resolved,U.u_firstname,"
					+ "U.u_lastname,A.u_firstname,A.u_lastname,T.rt_type,S.rs_status FROM reimbursements R " + 
					"INNER JOIN honorarium_users u ON R.u_id_author = u.u_id " + 
					"LEFT JOIN honorarium_users A ON R.u_id_resolver = A.u_id " + 
					"INNER JOIN reimbursement_status S ON R.rt_status = S.rs_id " + 
					"INNER JOIN reimbursement_type T ON R.rt_type = T.rt_id WHERE u.u_username = ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				allTickets.add(new ReimbursementDTO(rs.getInt(1),rs.getFloat(2),rs.getString(3),
						rs.getBlob(4).getBytes(1, (int) rs.getBlob(4).length()),
						rs.getTimestamp(5),rs.getTimestamp(6),rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return allTickets;
	}
	
	public List<ReimbursementDTO> getAllManagerTickets() {
		
		List<ReimbursementDTO> allTickets = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(SURL, SUSERNAME, SSERVERPASS);){
			String sql = "SELECT r_id,r_amount,r_description,r_reciept,r_submitted,r_resolved,U.u_firstname,"
					+ "U.u_lastname,A.u_firstname,A.u_lastname,T.rt_type,S.rs_status FROM reimbursements R " + 
					"INNER JOIN honorarium_users u ON R.u_id_author = u.u_id " + 
					"LEFT JOIN honorarium_users A ON R.u_id_resolver = A.u_id " + 
					"INNER JOIN reimbursement_status S ON R.rt_status = S.rs_id " + 
					"INNER JOIN reimbursement_type T ON R.rt_type = T.rt_id";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				allTickets.add(new ReimbursementDTO(rs.getInt(1),rs.getFloat(2),rs.getString(3),
						rs.getBlob(4).getBytes(1, (int) rs.getBlob(4).length()),
						rs.getTimestamp(5),rs.getTimestamp(6),rs.getString(7),rs.getString(8),rs.getString(9),
						rs.getString(10),rs.getString(11),rs.getString(12)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	return allTickets;
	}

	public int ticketUpdate(int ticketNumber, int resolverID,Timestamp timeresolved,int resolveType) {
		
		try (Connection conn = DriverManager.getConnection(SURL, SUSERNAME, SSERVERPASS)) {
			String sql = "update reimbursements set r_resolved= ?, u_id_resolver=?, rt_status= ? where r_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setTimestamp(1, timeresolved);
			ps.setInt(2, resolverID);
			ps.setInt(3, resolveType);
			ps.setInt(4, ticketNumber);
			ps.executeUpdate();
			return 1;
		}
			catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int ticketInsert(ReimbursementDTO ticket,int userID,int type) {
		
		try (Connection conn = DriverManager.getConnection(SURL, SUSERNAME, SSERVERPASS)) {
			String sql = "INSERT INTO reimbursements(r_amount,r_description,r_reciept,r_submitted,u_id_author,rt_type,rt_status) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, ticket.getAmount());
			ps.setString(2, ticket.getDescription());
			ps.setBinaryStream(3, new ByteArrayInputStream(ticket.getPicture()), ticket.getPicture().length);
			ps.setTimestamp(4,new Timestamp(System.currentTimeMillis()));
			ps.setInt(5,userID);
			ps.setInt(6,type);
			ps.setInt(7,0);
			ps.executeUpdate();
			return 1;
		}
			catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
		
		
	}
}


