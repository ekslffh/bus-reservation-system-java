package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dto.BusCompanyDTO;
import util.JDBCTemplate;

public class BusCompanyDAO implements DaoIfs<BusCompanyDTO> {
	private JDBCTemplate util = JDBCTemplate.getInstance();
	
	@Override
	public List<BusCompanyDTO> findAll() {
		List<BusCompanyDTO> list = new ArrayList<>();
		
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM BUS_COMPANY";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				BusCompanyDTO dto = new BusCompanyDTO();
				dto.setCode(rs.getString("C_CODE"));
				dto.setName(rs.getString("C_NAME"));
				dto.setAddress(rs.getString("C_ADDRESS"));
				dto.setTel(rs.getString("C_TELNO"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(stmt);
			util.close(conn);
		}
		
		return list;
	}

	@Override
	public BusCompanyDTO findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(BusCompanyDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(BusCompanyDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
