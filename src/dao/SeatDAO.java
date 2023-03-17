package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.SeatDTO;
import util.JDBCTemplate;

public class SeatDAO implements DaoIfs<SeatDTO> {
	private JDBCTemplate util = JDBCTemplate.getInstance();

	@Override
	public List<SeatDTO> findAll() {
		List<SeatDTO> list = new ArrayList<>();

		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM SEAT";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				SeatDTO dto = new SeatDTO();
				dto.setSeatCode(rs.getString("S_CODE"));
				dto.setDriveNumber(rs.getString("D_NUM"));
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
	public SeatDTO findById(String id) {
		SeatDTO dto = new SeatDTO();
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM SEAT " + "WHERE S_CODE = " + id;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				dto.setSeatCode(rs.getString("S_CODE"));
				dto.setDriveNumber(rs.getString("D_NUM"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(stmt);
			util.close(conn);
		}
		return dto;
	}

	@Override
	public int insert(SeatDTO dto) {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		int res = 0;

		String sql = "INSERT INTO SEAT(S_CODE,D_NUM) VALUES(?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSeatCode());
			pstmt.setString(2, dto.getDriveNumber());

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt);
			util.close(conn);
		}

		return res;
	}

	@Override
	public int update(SeatDTO dto) {

		return 0;
	}

	@Override
	public int deleteById(String id) {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		int res = 0;

		String sql = "DELETE FROM SEAT WHERE S_CODE = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
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
