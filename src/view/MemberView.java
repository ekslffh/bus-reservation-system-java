package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import dao.ReservationDAO;
import dto.ReservationDTO;
import service.ReservationService;
import util.JDBCTemplate;

public class MemberView {
	private JDBCTemplate util = JDBCTemplate.getInstance();
	private static Scanner sc = new Scanner(System.in);
	ReservationService rservice = new ReservationService();
	ReservationDTO redto = new ReservationDTO();

	// ���� ����ȭ�� ��� �޼ҵ�
	public static int printReservation() {
		StringBuffer sb = new StringBuffer();

		int select = 0;

		System.out.println("====================== ���� ���� ======================");
		System.out.println();
		System.out.println();

		sb.append("--------------------------------------------------\n");
		sb.append("[1] �����ϱ�  | [2] ����Ȯ��  | [3] ������� | [4] ���� \n");
		sb.append("--------------------------------------------------\n");

		System.out.println(sb);
		System.out.println("���ǹ��� ���Ÿ� ���͵帱�Կ�.");
		System.out.println("���Ͻô� ��ȣ�� ������ �ּ��� 'u'");

		select = sc.nextInt();

		return select;

	}

	// Ż���ִ� �������� ���� ��µǴ� �� ������
	public ReservationDTO selectbusinfo(String depart,String arrive,String date) {
		ReservationDTO dto = new ReservationDTO();
		 PreparedStatement pstmt = null;

		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT S_DRPARTTIME,S_ARRIVETIME, FROM WHERE = ?,?,?"; // �������� ¥�ּ��������� �����,������,��¥ 3���ޱ�

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				 pstmt = conn.prepareStatement(sql);
				 pstmt.setString(1, depart);
				 pstmt.setString(2, arrive);
				 pstmt.setString(3, date);
				// dto.setDepartTime(rs.getInt("S_DRPARTTIME"));
				// dto.setArriveTime(rs.getString("S_ARRIVETIME"));
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
	
	// Ż���ִ� �������� ���� ��¸޼ҵ�
	public void printBusInfo() {
		System.out.println("-------------------------------------------------------------");
		//System.out.println("���� :" + dto.get ); ��߽ð� + �����ð� + �ܿ��¼� + ��� + ���� dto�� ��ӹ޾� �ҷ����ֱ�
		System.out.println("-------------------------------------------------------------");
	}
		
	
	// ����Ȯ�� ��¸޼ҵ�
	public void printrecheck() {
		System.out.println();
		System.out.println("=====================================");
		System.out.println("������ ��������");
		System.out.println("�����ȣ "+ redto.getReservationNum());
		System.out.println("����� : " + rservice.depart);
		System.out.println("������ : " + rservice.arrive);
		System.out.println("��¥ : " + rservice.date);
		System.out.println("�¼� : " + rservice.row + "��" + "rservice.column" + "��");
		System.out.println("=====================================");
		System.out.println();
		
	}
	
	
	
	

}
