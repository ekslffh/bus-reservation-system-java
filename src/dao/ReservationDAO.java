package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.MemberReservationDTO;
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
				dto.setNum(rs.getString("R_NUM"));
				dto.setMemberId(rs.getString("M_ID"));
				dto.setSeatCode(rs.getString("S_CODE"));
				dto.setDriveNum(rs.getString("D_NUM"));
				dto.setReservationDate(rs.getString("S_RDATE"));
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
			dto.setNum(rs.getString("R_NUM"));
			dto.setMemberId(rs.getString("M_ID"));
			dto.setSeatCode(rs.getString("S_CODE"));
			dto.setDriveNum(rs.getString("D_NUM"));
			dto.setReservationDate(rs.getString("S_RDATE"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs);
			util.close(stmt);
			util.close(conn);
		}
		return dto;
	}

	public List<MemberReservationDTO> findByMemberId(String id) {
		List<MemberReservationDTO> list = new ArrayList<>();

		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT R_DEPART AS 출발역, R_ARRIVE AS 도착역, S_RDATE AS 출발일, D_DEPARTTIME AS 출발시간, S_CODE AS 좌석번호, RO.R_DRIVETIME AS 운행시간 "
		+ " FROM RESERVATION RE, DRIVE D, ROUTE RO, MEMBER M "
		+ " WHERE M.M_ID = \'" + id  
		+ "\' AND RE.D_NUM = D.D_NUM "
		+ " AND D.R_CODE = RO.R_CODE ";
//		String sql = "SELECT * FROM RESERVATION WHERE M_ID = \'" + id + "\'";
//		SELECT R_DEPART AS 출발역, R_ARRIVE AS 도착역, S_RDATE AS 출발일, D_DEPARTTIME AS 출발시간, S_CODE AS 좌석번호, RO.R_DRIVETIME AS 운행시간  
//		FROM RESERVATION RE, DRIVE D, ROUTE RO, MEMBER M 
//		WHERE M.M_ID = 'ekslffh'  
//		AND RE.D_NUM = D.D_NUM 
//		AND D.R_CODE = RO.R_CODE; 
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			rs.last();

			int rowCount = rs.getRow();
			rs.beforeFirst();

			if (rowCount == 0) {
				return null;
			} else {
				while (rs.next()) {
					MemberReservationDTO dto = new MemberReservationDTO();
					dto.setDateTime(rs.getString("출발일").substring(0, 12) + rs.getString("출발시간"));
					dto.setDepartment(rs.getString("출발역"));
					dto.setArrive(rs.getString("도착역"));
					dto.setSeatCode(rs.getString("좌석번호"));
					dto.setDriveTime(rs.getInt("운행시간"));
//					ReservationDTO dto = new ReservationDTO();
//					dto.setNum(rs.getString(rs.getString("R_NUM")));
//					dto.setMemberId(rs.getString("M_ID"));
//					dto.setSeatCode(rs.getString("S_CODE"));
//					dto.setDriveNum(rs.getString("D_NUM"));
//					dto.setReservationDate(rs.getString("S_RDATE"));
					list.add(dto);
				}
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
	public int insert(ReservationDTO dto) {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		int res = 0;

		String sql = "INSERT INTO RESERVATION(R_NUM, M_ID, D_NUM, S_CODE, B_CODE) VALUES(?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getNum());
			pstmt.setString(2, dto.getMemberId());
			pstmt.setString(3, dto.getSeatCode());
			pstmt.setString(4, dto.getDriveNum());
			pstmt.setString(5, dto.getReservationDate());
			
			res = pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			util.close(pstmt);
			util.close(conn);
		}
		return res;
	}

	public int insertAll(List<ReservationDTO> dtos) {
		int status = 0;
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO RESERVATION(R_NUM, M_ID, D_NUM, S_CODE, S_RDATE) VALUES(?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);
			for (ReservationDTO dto : dtos) {
				pstmt.setString(1, dto.getNum());
				pstmt.setString(2, dto.getMemberId());
				pstmt.setString(3, dto.getDriveNum());
				pstmt.setString(4, dto.getSeatCode());
				pstmt.setString(5, dto.getReservationDate());
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
			util.rollback(conn);
			status = -1;
		} finally {
			util.close(pstmt);
			util.close(conn);
		}
		// 결과상태 반환
		return status;
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
			util.rollback(conn);
		} finally {
			util.close(pstmt);
			util.close(conn);
		}

		return res;
	}

}
