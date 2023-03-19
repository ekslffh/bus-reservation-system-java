package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.DriveDTO;
import dto.DriveInfoDTO;
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
	public DriveDTO findById(String id) {
		DriveDTO dto = new DriveDTO();

		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM DRIVE WHERE D_NUM = \'" + id + "\'";

		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			rs.last();

			int rowCount = rs.getRow();
			rs.beforeFirst();

			if (rowCount == 0) {
				return null;
			} else {
				rs.next();
				dto.setDriveNumber(rs.getString("D_NUM"));
				dto.setDepartmentTime(rs.getString("D_DEPARTTIME"));
				dto.setArriveTime(rs.getString("D_ARRIVETIME"));
				dto.setBusCode(rs.getString("B_CODE"));
				dto.setRouteCode(rs.getString("R_CODE"));
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

			res = pstmt.executeUpdate();
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

	@Override
	public int update(DriveDTO dto) {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = "UPDATE DRIVE SET D_DEPARTTIME = ?, D_ARRIVETIME = ?, B_CODE = ?, R_CODE = ? WHERE D_NUM = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getDepartmentTime());
			pstmt.setString(2, dto.getArriveTime());
			pstmt.setString(3, dto.getBusCode());
			pstmt.setString(4, dto.getRouteCode());
			pstmt.setString(5, dto.getDriveNumber());

			res = pstmt.executeUpdate();
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

	@Override
	public int deleteById(String id) {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		int res = 0;

		String sql = "DELETE FROM DRIVE WHERE D_NUM = ?";

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

	// 운행을 가지고 시간과 잔여좌석 + 등급 + 가격 추가로 보여주려면? 해당 날짜에 대한 잔여석
	public List<DriveInfoDTO> getDriveInfos(String depart, String arrive, String date) {
		// 해당 데이터를 담을만한 dto필요
		// 담을 정보: 운행번호, 운행일자, 출발시간, 도착시간, 잔여석, 버스등급, 가격
		// 넣어줘야 할 정보: 운행일자, 출발역, 도착역
		List<DriveInfoDTO> list = new ArrayList<>();
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT " + " D.D_NUM AS 운행번호, " + " ? AS 운행일자, " + " D.D_DEPARTTIME AS 출발시간, "
				+ " D.D_ARRIVETIME AS 도착시간, "
				+ " (B.B_NUM - (SELECT COUNT(*) FROM SEAT WHERE D_NUM = D.D_NUM AND S_RDATE = ?)) AS 잔여석, "
				+ " B.G_CLASS AS 등급, " + " G.G_PRICE AS 가격 " + " FROM DRIVE D, BUS B, BUS_GRADE G "
				+ " WHERE D.R_CODE = (SELECT R_CODE FROM ROUTE WHERE R_DEPART = ? AND R_ARRIVE = ?) "
				+ " AND D.B_CODE = B.B_CODE " + " AND B.G_CLASS = G.G_CLASS " + " ORDER BY D.D_DEPARTTIME ";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setString(2, date);
			pstmt.setString(3, depart);
			pstmt.setString(4, arrive);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				DriveInfoDTO dto = new DriveInfoDTO();

				// 담을 정보: 운행번호, 운행일자, 출발시간, 도착시간, 잔여석, 버스등급, 가격
				dto.setDriveNum(rs.getString("운행번호"));
				dto.setDriveDate(rs.getDate("운행일자"));
				dto.setDepartTime(rs.getString("출발시간"));
				dto.setArriveTime(rs.getString("도착시간"));
				dto.setNumOfRemainSeats(rs.getInt("잔여석"));
				dto.setBusGrade(rs.getString("등급"));
				dto.setPrice(rs.getInt("가격"));

				list.add(dto);
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
}
