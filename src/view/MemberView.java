package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import dao.ReservationDAO;
import dto.ReservationDTO;
import dto.ReservationInfoDTO;
import service.ReservationService;
import util.JDBCTemplate;

public class MemberView {
	private JDBCTemplate util = JDBCTemplate.getInstance();
	private static Scanner sc = new Scanner(System.in);
	ReservationService rservice = new ReservationService();
	ReservationInfoDTO reserveDTO = new ReservationInfoDTO();
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
		System.out.print("���Ͻô� ��ȣ�� ������ �ּ��� 'u' : ");

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
		System.out.println("����� : " + reserveDTO.getDepartTerminal());
		System.out.println("������ : " + reserveDTO.getArriveTerminal());
		System.out.println("��¥ : " + rservice.date);
		System.out.println("�¼� : " + rservice.row + "��" + rservice.column + "��");
		System.out.println("=====================================");
		System.out.println();
		
	}
	
	public void Default() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		sb.append("\n");
		sb.append("\n");
		sb.append("                  _       ___     ___      ___  __   __            ___    _   _    ___   \n");
		sb.append("              | |     / _ \\   / _ \\    | _ \\ \\ \\ / /    o O O  | _ )  | | | |  / __| \n");
		sb.append("               | |__  | (_) | | (_) |   |  _/  \\ V /    o       | _ \\  | |_| |  \\__ \\  \n");
		sb.append("                  |____|  \\___/   \\___/   _|_|_   _|_|_   TS__[O]  |___/   \\___/   |___/  \n");
		sb.append(
				"            _|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_| \"\"\" |_| \"\"\" | {======|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n");
		sb.append("         "
				+ "   \"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'./o--000'\"`-0-0-'\"`-0-0-'\"`-0-0-' \n");
		sb.append("\n");
		sb.append("\n");
		sb.append("\n");
		sb.append("\n");
		sb.append("                  ��____��        \n");
		sb.append("                  (   �� �� �� ) \n");
		sb.append("                        |   �� ~ �� �� ��  �����ڼ�? �� ���ߴ� ���� �𸣴� �����ε� \n");

		System.out.print(sb);

	}

	public void printDefault() {
		StringBuffer sb = new StringBuffer();

		sb.append("------------------------------------------------------------\n");
		sb.append("\n");
		sb.append("-------------------------------------------------------------\n");
		System.out.print(sb);
	}
	
	public void printMenu() {
		StringBuffer sb = new StringBuffer();
		
		printDefault();
		sb.append("1.�α��� | 2.ȸ������ | 3.ID/PW ã�� | 4.PW ���� | 5.ȸ��Ż�� | 6.����\n");
		sb.append("-------------------------------------------------------------\n");
		sb.append("���ǹ����� ���Ű� ȯ���մϴ� !\n");
		System.out.print(sb);
		System.out.print("���Ͻô� ��ȣ�� ������ �ּ��� 'u' : ");
	}
	
	public void printIDorPW() {
		StringBuffer sb = new StringBuffer();
		
		printDefault();
		sb.append("1.IDã�� | 2.PWã��\n");
		System.out.print(sb);
		System.out.print("���Ͻô� ��ȣ�� ������ �ּ��� 'u' : ");
	}
	
}
