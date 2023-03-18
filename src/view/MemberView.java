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

	// 예매 시작화면 출력 메소드
	public static int printReservation() {
		StringBuffer sb = new StringBuffer();

		int select = 0;

		System.out.println("====================== 예매 구역 ======================");
		System.out.println();
		System.out.println();

		sb.append("--------------------------------------------------\n");
		sb.append("[1] 예매하기  | [2] 예매확인  | [3] 예매취소 | [4] 종료 \n");
		sb.append("--------------------------------------------------\n");

		System.out.println(sb);
		System.out.println("루피버스 예매를 도와드릴게요.");
		System.out.print("원하시는 번호를 선택해 주세요 'u' : ");

		select = sc.nextInt();

		return select;

	}

	// 탈수있는 버스정보 전부 출력되는 뷰 쿼리문
	public ReservationDTO selectbusinfo(String depart,String arrive,String date) {
		ReservationDTO dto = new ReservationDTO();
		 PreparedStatement pstmt = null;

		Connection conn = util.getConnection();
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT S_DRPARTTIME,S_ARRIVETIME, FROM WHERE = ?,?,?"; // 쿼리문은 짜주세요조건은 출발지,도착지,날짜 3개받기

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
	
	// 탈수있는 버스정보 전부 출력메소드
	public void printBusInfo() {
		System.out.println("-------------------------------------------------------------");
		//System.out.println("버스 :" + dto.get ); 출발시간 + 도착시간 + 잔여좌석 + 등급 + 가격 dto에 상속받아 불러와주기
		System.out.println("-------------------------------------------------------------");
	}
		
	
	// 예약확인 출력메소드
	public void printrecheck() {
		System.out.println();
		System.out.println("=====================================");
		System.out.println("예약한 버스내역");
		System.out.println("예약번호 "+ redto.getReservationNum());
		System.out.println("출발지 : " + reserveDTO.getDepartTerminal());
		System.out.println("도착지 : " + reserveDTO.getArriveTerminal());
		System.out.println("날짜 : " + rservice.date);
		System.out.println("좌석 : " + rservice.row + "행" + rservice.column + "열");
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
		sb.append("                  ハ____ハ        \n");
		sb.append("                  (   ˙ ω ˙ ) \n");
		sb.append("                        |   つ ~ ♡ ♡ ♡  괜찮겠서? 난 멈추는 법을 모르는 루피인데 \n");

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
		sb.append("1.로그인 | 2.회원가입 | 3.ID/PW 찾기 | 4.PW 변경 | 5.회원탈퇴 | 6.종료\n");
		sb.append("-------------------------------------------------------------\n");
		sb.append("루피버스에 오신걸 환영합니다 !\n");
		System.out.print(sb);
		System.out.print("원하시는 번호를 선택해 주세요 'u' : ");
	}
	
	public void printIDorPW() {
		StringBuffer sb = new StringBuffer();
		
		printDefault();
		sb.append("1.ID찾기 | 2.PW찾기\n");
		System.out.print(sb);
		System.out.print("원하시는 번호를 선택해 주세요 'u' : ");
	}
	
}
