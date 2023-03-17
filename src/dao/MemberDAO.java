package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.List;

import dto.MemberDTO;
import service.MemberService;
import util.JDBCTemplate;
import view.MemberView;

public class MemberDAO implements DaoIfs<MemberDTO>{
	private JDBCTemplate util = JDBCTemplate.getInstance();
	private static MemberView memberView = new MemberView();
	private static MemberService memberService = new MemberService();
	
	public int insert(MemberDTO dto) {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		String sql = " insert into member values(?, ?, ?, ?, ?, 0) ";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getM_id());
			pstmt.setString(2, dto.getM_pw());
			pstmt.setString(3, dto.getM_name());
			pstmt.setString(4, dto.getM_telno());
			pstmt.setString(5, dto.getM_add());

			res = pstmt.executeUpdate();
		}catch(SQLIntegrityConstraintViolationException e) {
			System.out.println("아이디가 중복되었습니다.");
			memberView.printDefault();
			memberService.insert();
		}		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt);
			util.close(conn);
		}
		return res;
	}
	
	public int deleteById(String mid) {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		int res = 0;

		String sql = "delete from member where m_id = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mid);

			res = pstmt.executeUpdate();
			if (res > 0) {
				util.commit(conn);
			}

		} catch (SQLException e) {
			System.out.println("잘못된 아이디 입니다.");
		} finally {
			util.close(pstmt);
			util.close(conn);
		}
		return res;
	}

	@Override
	public List<MemberDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public  MemberDTO findById(String id) {
		MemberDTO dto = new MemberDTO();
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select m_pw, m_telno from member where m_id = \'" + id + "\'";
		
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			rs.last();      

	        int rowcount = rs.getRow();
	        rs.beforeFirst();
			if(rowcount == 0) {
				System.out.println("잘못된 아이디입니다.");
			}else {
			rs.next();
			}
			
			dto.setM_pw(rs.getString("M_PW"));
			dto.setM_telno(rs.getString("M_TELNO"));
		} catch (SQLException e) {

		} finally {
			util.close(rs);
			util.close(stmt);
			util.close(conn);
		}
		
		return dto;
	}
	
	@Override
	public  MemberDTO findByPw(String pw) {
		MemberDTO dto = new MemberDTO();
		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select m_id, m_telno from member where m_pw = \'" + pw + "\'";
		
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery(sql);
			rs.last();      

	        int rowcount = rs.getRow();
	        rs.beforeFirst();
			if(rowcount == 0) {
				System.out.println("잘못된 비밀번호입니다.");
			}else {
			rs.next();
			}
			
			dto.setM_id(rs.getString("M_ID"));
			dto.setM_telno(rs.getString("M_TELNO"));
		} catch (SQLException e) {
			
		} finally {
			util.close(rs);
			util.close(stmt);
			util.close(conn);
		}
		
		return dto;
	}

	public int update(MemberDTO dto) {
		Connection conn = util.getConnection();
		PreparedStatement pstmt = null;
		int res = 0;
		
		String sql = "update member set m_pw = ? ";
		sql = sql + " where m_id = ?";
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getM_pw());
			pstmt.setString(2, dto.getM_id());
			
			res=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			util.close(pstmt);
			util.close(conn);
		}
		return res;
	}
}
