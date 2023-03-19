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

	public List<String> findAllByDriveAndDate(String date, String driveNum) {
		List<String> list = new ArrayList<>();
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT S_CODE FROM SEAT WHERE S_RDATE = ? AND D_NUM = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, driveNum);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("S_code"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(pstmt);
			util.close(conn);
		}

		return list;
	}

	@Override
	public int insert(SeatDTO dto) {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		int res = 0;

		String sql = "INSERT INTO SEAT(S_CODE,D_NUM, S_RDATE) VALUES(?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSeatCode());
			pstmt.setString(2, dto.getDriveNumber());
			pstmt.setNString(3, dto.getReservationDate());

			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			util.rollback(conn);
		} finally {
			util.close(pstmt);
			util.close(conn);
		}
		return res;
	}

	public int insertAll(List<SeatDTO> dtos) {
		int status = 0;
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO SEAT(S_CODE, D_NUM, S_RDATE) VALUES(?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			for (SeatDTO dto : dtos) {
				pstmt.setString(1, dto.getSeatCode());
				pstmt.setString(2, dto.getDriveNumber());
				pstmt.setString(3, dto.getReservationDate());
				pstmt.addBatch();
			}
			// 다 당은후 Batch 실행
			pstmt.executeBatch();
			// 커밋
			conn.commit();
			// Batch 초기화
			pstmt.clearBatch();
			// 상태 업데이트
			status = 1;
		} catch (Exception e) {
			e.printStackTrace();
			// 실패시 rollback 해줘야 함
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			status = -1;
		} finally {
			util.close(pstmt);
			util.close(conn);
		}
		// 결과상태 반환
		return status;
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
