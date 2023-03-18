package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dto.ReservationDTO;
import util.JDBCTemplate;

public class ReservationDAO implements DaoIfs<ReservationDTO> {
	private JDBCTemplate util = JDBCTemplate.getInstance();

	@Override
	public List<ReservationDTO> findAll() {
		List<ReservationDTO> list = new ArrayList<>();

		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM RESERVATION";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ReservationDTO dto = new ReservationDTO();
//				dto.setReservationNum(rs.getString("R_NUM"));
//				dto.setMemId(rs.getString("M_ID"));
//				dto.setDriveNum("D_NUM");
//				dto.setScheduleCode("S_CODE");
//				dto.setBusCode("B_CODE");
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
	public ReservationDTO findById(String id) {
		ReservationDTO dto = new ReservationDTO();

		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT R_NUM, M_ID, D_NUM, S_CODE, B_CODE " + "WHERE R_NUM = \'" + id + "\'";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
//			dto.setReservationNum(rs.getString("R_NUM"));
//			dto.setMemId(rs.getString("M_ID"));
//			dto.setDriveNum(rs.getString("D_NUM"));
//			dto.setScheduleCode(rs.getString("S_CODE"));
//			dto.setBusCode(rs.getString("B_CODE"));
			
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
	public int insert(ReservationDTO dto) {

		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		int res = 0;

		String sql = "INSERT INTO RESERVATION(R_NUM, M_ID, D_NUM, S_CODE, B_CODE) VALUES(?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, dto.getReservationNum());
//			pstmt.setString(2, dto.getMemId());
			pstmt.setString(3, dto.getDriveNum());
//			pstmt.setString(4, dto.getScheduleCode());
//			pstmt.setString(5, dto.getBusCode());

			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt);
			util.close(conn);
		}
		return res;
	}

	@Override
	public int update(ReservationDTO dto) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteById(String id) {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		int res = 0;

		String sql = "DELETE FROM RESERVATION WHERE R_NUM = ? ";

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
