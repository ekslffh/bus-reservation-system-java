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
				dto.setDepartmentTime(rs.getString("D_DEPARTTIME"));
				dto.setArriveTime(rs.getString("D_ARRIVETIME"));
				dto.setBusCode(rs.getString("B_CODE"));
				dto.setRouteCode(rs.getString("R_CODE"));
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
			dto.setDepartmentTime(rs.getString("D_DEPARTTIME"));
			dto.setArriveTime(rs.getString("D_ARRIVETIME"));
			dto.setBusCode(rs.getString("B_CODE"));
			dto.setRouteCode(rs.getString("R_CODE"));
			
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
	    
	    String sql = "INSERT INTO DRIVE(D_NUM, D_DEPARTTIME, D_ARRIVETIME, B_CODE, R_CODE) VALUES(?, ?, ?, ?, ?)";
	    
	    try {
	        pstmt = conn.prepareStatement(sql);
				
			pstmt.setString(1, dto.getDriveNumber());
			pstmt.setString(2, dto.getDepartmentTime());
			pstmt.setString(3, dto.getArriveTime());
			pstmt.setString(4, dto.getBusCode());
			pstmt.setString(5, dto.getRouteCode());
			
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
		System.out.println("ddd: " + dto);
//		UPDATE DRIVE SET D_DEPARTTIME = '14:10', D_ARRIVETIME = '15:10', B_CODE = 'SH0039', R_CODE = '9' WHERE D_NUM = '100';
		System.out.println("ddd: " + dto);
		String sql = "UPDATE DRIVE SET D_DEPARTTIME = ?, D_ARRIVETIME = ?, B_CODE = ?, R_CODE = ? WHERE D_NUM = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getDepartmentTime());
			pstmt.setString(2, dto.getArriveTime());
			pstmt.setString(3, dto.getBusCode());
			pstmt.setString(4, dto.getRouteCode());
			pstmt.setString(5, dto.getDriveNumber());
			
			res = pstmt.executeUpdate();
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
		
		String sql = "DELETE FROM DRIVE WHERE D_NUM = ?";
	
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
