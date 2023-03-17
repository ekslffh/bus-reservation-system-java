package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dto.BusCompanyDTO;
import util.JDBCTemplate;


//SELECT * FROM BUS_COMPANY;
//SELECT * FROM BUS_COMPANY WHERE C_CODE = '1234';
//INSERT INTO BUS_COMPANY(C_CODE, C_NAME, C_ADDRESS, C_TELNO) VALUES('1234', '금호고속', '대전 서구 월평동 123', '01084866687');
//UPDATE BUS_COMPANY SET C_ADDRESS = '대전 만년동 327-16', C_TELNO = '01064875511' WHERE C_CODE = '1234';
//UPDATE BUS_COMPANY SET C_ADDRESS = '대전 만년동2 327-16' WHERE C_CODE = '1234';
//UPDATE BUS_COMPANY SET C_TELNO = '01097445511' WHERE C_CODE = '1234';
//DELETE FROM BUS_COMPANY WHERE C_CODE = '1234';

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

	//SELECT * FROM BUS_COMPANY WHERE C_CODE = '1234';

	@Override
	public BusCompanyDTO findById(String id) {
		BusCompanyDTO dto = new BusCompanyDTO();
		
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM BUS_COMPANY WHERE C_CODE = " + id;
		
		try {
			stmt = conn.createStatement();
			stmt.executeQuery(sql);
			
			rs.next();
			dto.setCode(rs.getString("C_CODE"));
			dto.setName(rs.getString("C_NAME"));
			dto.setAddress(rs.getString("C_ADDRESS"));
			dto.setTel(rs.getString("C_TELNO"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(stmt);
			util.close(conn);
		}
		
		return dto;
	}

	//INSERT INTO BUS_COMPANY(C_CODE, C_NAME, C_ADDRESS, C_TELNO) VALUES('1234', '금호고속', '대전 서구 월평동 123', '01084866687');

	@Override
	public int insert(BusCompanyDTO dto) {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		
		String sql = "INSERT INTO BUS_COMPANY(C_CODE, C_NAME, C_ADDRESS, C_TELNO) VALUES()";
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

	@Override
	public BusCompanyDTO findByPw(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
