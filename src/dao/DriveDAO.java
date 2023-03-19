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

	// ������ ������ �ð��� �ܿ��¼� + ��� + ���� �߰��� �����ַ���? �ش� ��¥�� ���� �ܿ���
	public List<DriveInfoDTO> getDriveInfos(String depart, String arrive, String date) {
		// �ش� �����͸� �������� dto�ʿ�
		// ���� ����: �����ȣ, ��������, ��߽ð�, �����ð�, �ܿ���, �������, ����
		// �־���� �� ����: ��������, ��߿�, ������
		List<DriveInfoDTO> list = new ArrayList<>();
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT " + " D.D_NUM AS �����ȣ, " + " ? AS ��������, " + " D.D_DEPARTTIME AS ��߽ð�, "
				+ " D.D_ARRIVETIME AS �����ð�, "
				+ " (B.B_NUM - (SELECT COUNT(*) FROM SEAT WHERE D_NUM = D.D_NUM AND S_RDATE = ?)) AS �ܿ���, "
				+ " B.G_CLASS AS ���, " + " G.G_PRICE AS ���� " + " FROM DRIVE D, BUS B, BUS_GRADE G "
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

				// ���� ����: �����ȣ, ��������, ��߽ð�, �����ð�, �ܿ���, �������, ����
				dto.setDriveNum(rs.getString("�����ȣ"));
				dto.setDriveDate(rs.getDate("��������"));
				dto.setDepartTime(rs.getString("��߽ð�"));
				dto.setArriveTime(rs.getString("�����ð�"));
				dto.setNumOfRemainSeats(rs.getInt("�ܿ���"));
				dto.setBusGrade(rs.getString("���"));
				dto.setPrice(rs.getInt("����"));

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
