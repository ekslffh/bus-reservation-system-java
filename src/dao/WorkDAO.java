package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.DriveDTO;
import dto.WorkDTO;
import util.JDBCTemplate;

public class WorkDAO implements DaoIfs<WorkDTO> {
	private JDBCTemplate util = JDBCTemplate.getInstance();
	
	@Override
	public List<WorkDTO> findAll(){
		List<WorkDTO> list = new ArrayList<>();
		
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM WORK";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				WorkDTO dto = new WorkDTO();
				dto.setWorkCode(rs.getString("W_CODE"));
				dto.setWorkDate(rs.getString("W_DATE"));
				dto.setId(rs.getString("E_ID"));
				dto.setBusCode(rs.getString("B_CODE"));
				list.add(dto);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			util.close(rs);
			util.close(stmt);
			util.close(conn);
		}
	
		return list;
	
	}

	@Override
	public WorkDTO findById(String id) {
        WorkDTO dto = new WorkDTO();
		
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT FROM WORK";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				dto.setWorkCode(rs.getString("W_CODE"));
				dto.setWorkDate(rs.getString("W_DATE"));
				dto.setId(rs.getString("E_ID"));
				dto.setBusCode(rs.getString("B_CODE"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			util.close(rs);
			util.close(stmt);
			util.close(conn);
		}
		return dto;
	}

	@Override
	public int insert(WorkDTO dto) {
		Connection conn = util.getConnection();
	    PreparedStatement pstmt = null;
		int res = 0;
		    
	    String sql = "INSERT INTO WORK(W_CODE, W_DATE, E_ID, B_CODE)"
		    		     + "VALUES(?,?,?,?)";
		    
	    try {
		    pstmt = conn.prepareStatement(sql);
					
		    pstmt.setString(1,dto.getWorkCode());
			pstmt.setString(2,dto.getWorkDate());
			pstmt.setString(3,dto.getId());
			pstmt.setString(4,dto.getBusCode());
			
					
			res= pstmt.executeUpdate();
		 }catch (SQLException e) {
		    e.printStackTrace();
		 }finally {
		    util.close(pstmt);
		    util.close(conn);
		 }
		return res;
	}

	@Override
	public int update(WorkDTO dto) {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		
		String sql1 = "UPDATE WORK"
				+     "SET W_DATE = ?"
				+     "WHERE E_ID = ?";
		try {
			pstmt = conn.prepareStatement(sql1);
		    pstmt.setString(1,dto.getWorkCode());
		    pstmt.setString(2,dto.getWorkDate());
			pstmt.setString(3,dto.getId());
		    pstmt.setString(4,dto.getBusCode());
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			util.close(pstmt);
			util.close(conn);
		}
		return res;
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
