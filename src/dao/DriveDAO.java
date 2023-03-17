package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.BusDTO;
import dto.DriveDTO;
import util.JDBCTemplate;

public class DriveDAO implements DaoIfs<DriveDTO> {
	private JDBCTemplate util = JDBCTemplate.getInstance();
	
	@Override
	public List<DriveDTO> findAll() {
		List<DriveDTO> list = new ArrayList<>();
		
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM DRIVE";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				DriveDTO dto = new DriveDTO();
				dto.setDriveNumber(rs.getString("D_NUM"));
				dto.setBusCode(rs.getString("B_CODE"));
				dto.setScheduleCode(rs.getString("S_CODE"));
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
	public DriveDTO findById(String id) {
		DriveDTO dto = new DriveDTO();
		
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM DRIVE WHERE D_NUM = \'" + id + "\'";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			rs.next();
			dto.setDriveNumber(rs.getString("D_NUM"));
			dto.setBusCode(rs.getNString("B_CODE"));
			dto.setScheduleCode(rs.getString("S_CODE"));
			
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
	public int insert(DriveDTO dto) {
	    Connection conn = util.getConnection();
	    PreparedStatement pstmt = null;
	    int res = 0;
	    
	    String sql = "INSERT INTO DRIVE(D_NUM, B_CODE, S_CODE)"
	    		     + "VALUES(?,?,?)";
	    
	    try {
	        pstmt = conn.prepareStatement(sql);
				
			pstmt.setString(1,dto.getDriveNumber());
			pstmt.setString(2,dto.getBusCode());
			pstmt.setString(3,dto.getScheduleCode());
				
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
	public int update(DriveDTO dto) {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		
		String sql = "UPDATE DRIVE"
				+     "SET B_CODE = ?, "
				+     "SET S_CODE = ?"
				+     "WHERE D_NUM = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBusCode());
			pstmt.setString(2, dto.getScheduleCode());
			pstmt.setString(3, dto.getDriveNumber());
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			util.close(pstmt);
			util.close(conn);
		}
		return res;
	}
	
	public int updateBus(DriveDTO dto) {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		
		String sql = "UPDATE DRIVE"
				+     "SET B_CODE = ?, "
				+     "WHERE D_NUM = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBusCode());
			pstmt.setString(2, dto.getDriveNumber());
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			util.close(pstmt);
			util.close(conn);
		}
		return res;
	}
	
	public int updateSchedule(DriveDTO dto) {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		
		String sql = "UPDATE DRIVE"
				+     "SET S_CODE = ?, "
				+     "WHERE B_CODE = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getScheduleCode());
			pstmt.setString(2, dto.getBusCode());
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
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		
		String sql = "DELETE FROM DIRVE WHERE D_CODE = ?";
	
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,id);	
			
			res = pstmt.executeUpdate();
			if (res > 0) {
				util.commit(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt);
			util.close(conn);
		}
		
				
		return res;
	}

}
