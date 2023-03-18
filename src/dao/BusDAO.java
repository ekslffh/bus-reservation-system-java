package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.BusDTO;
import util.JDBCTemplate;

public class BusDAO {
	private JDBCTemplate util = JDBCTemplate.getInstance();

	public List<BusDTO> findAll() {
		List<BusDTO> list = new ArrayList<>();
		
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM BUS";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				BusDTO dto = new BusDTO();
				dto.setBuscode(rs.getString("B_CODE"));
				dto.setCompanycode(rs.getString("C_CODE"));
				dto.setCapacity(rs.getInt("B_NUM"));
				dto.setBusclass(rs.getString("G_CLASS"));
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