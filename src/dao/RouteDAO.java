package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dto.DriveDTO;
import dto.RouteDTO;
import util.JDBCTemplate;

public class RouteDAO {
private JDBCTemplate util = JDBCTemplate.getInstance();
	
	public List<RouteDTO> findAll() {
		List<RouteDTO> list = new ArrayList<>();
		
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ROUTE";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				RouteDTO dto = new RouteDTO();
				dto.setCode(rs.getString("R_CODE"));
				dto.setDistance(rs.getInt("R_DISTANCE"));
				dto.setDriveTime(rs.getInt("R_DRIVETIME"));
				dto.setDepartment(rs.getString("R_DEPART"));
				dto.setArrive(rs.getString("R_ARRIVE"));
				
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
}
