package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dto.TerminalDTO;
import util.JDBCTemplate;

public class TerminalDAO {
	
	private JDBCTemplate util = JDBCTemplate.getInstance();
	
	public List<TerminalDTO> findAll() {
		List<TerminalDTO> list = new ArrayList<>();
		
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM BUS_TERMINAL";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				TerminalDTO dto = new TerminalDTO();
				dto.setName(rs.getString("T_NAME"));
				dto.setLocation(rs.getString("T_LOC"));
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
