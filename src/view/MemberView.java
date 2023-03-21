package view;

import java.util.List;
import java.util.Scanner;

import dto.DriveInfoDTO;
import dto.MemberReservationDTO;
import dto.ReservationDTO;
import service.ReservationService;
import util.JDBCTemplate;

public class MemberView {
	private JDBCTemplate util = JDBCTemplate.getInstance();
	private static Scanner sc = new Scanner(System.in);
	ReservationService rservice = new ReservationService();
	ReservationDTO redto = new ReservationDTO();

	// 메인 파트별 시작선
	public void bar() {
		System.out.print("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
	}

	// 시작선 밑에 여백
	public static void barMiddle() {
		System.out.println();
		System.out.println();
		System.out.println();
	}

	// 파트별 끝선
	public static void barEnd() {
		System.out.println();
		System.out.println();
		System.out.print("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
		System.out.println();
		System.out.println();
	}

	public String authMenu() {
		StringBuffer sb = new StringBuffer();
		barMiddle();
		bar();
		System.out.print(" Main ");
		bar();
		sb.append("\n");
		sb.append("\n");
		sb.append("\n");
		sb.append("--------------------------------------------------\n");//
		sb.append(" 1.로그인🚌 | 2.회원가입🚌 | 3.ID/PW 찾기🚌 | 4.종료🚌\n");
		sb.append("--------------------------------------------------\n");
		sb.append("\n");
		sb.append("원하시는 번호를 선택해 주세요 ₍ᐢ..ᐢ₎ ̑̑: ");
		System.out.print(sb);
		// mainbarEnd(); //메인파트 끝나는 곳에 넣어주세요

		return sc.nextLine();

	}

	// 예매 시작화면 출력 메서드
	public String mainMenu() {
		StringBuffer sb = new StringBuffer();

		barMiddle();
		bar();
		System.out.print(" User Menu ");
		bar();
		sb.append("\n");
		sb.append("\n");
		sb.append("\n");
		sb.append("--------------------------------------------------\n");//
		sb.append(" 1.예매🚌 | 2.마이페이지🚌 | 3.로그아웃🚌 | 4.종료🚌\n");
		sb.append("--------------------------------------------------\n");
		sb.append("원하시는 번호를 선택해 주세요 ₍ᐢ..ᐢ₎ : ");
		System.out.print(sb);
		return sc.nextLine();

	}

	// 예매 시작화면 출력 메서드
	public String reservationMenu() {
		StringBuffer sb = new StringBuffer();
		barMiddle();
		bar();
		System.out.print(" Reservation ");
		bar();
		barMiddle();
		sb.append("-------------------------------------------\n");
		sb.append(" 1. 예매하기🚌 | 2. 예매 확인 및 취소🚌 | 3. 종료🚌\n");
		sb.append("-------------------------------------------\n");

		System.out.println(sb);
		System.out.println("루피버스 예매를 도와드릴게요.");
		System.out.print("원하시는 번호를 선택해 주세요 ₍ᐢ..ᐢ₎ : ");
		// barEnd(); 예매 끝나는곳에 넣어주세요

		return sc.nextLine();
	}

	public String myPageMenu() {
		StringBuffer sb = new StringBuffer();
		barMiddle();
		bar();
		System.out.print(" MyPage ");
		bar();
		barMiddle();

		sb.append("--------------------------------------------------\n");
		sb.append(" 1. 비밀번호변경🚌 | 2. 회원탈퇴🚌 | 3. 메인페🚌이지 | 4. 종료🚌\n");
		sb.append("--------------------------------------------------\n");
		System.out.print(sb);
		System.out.print("원하시는 번호를 선택해 주세요 ₍ᐢ..ᐢ₎ : ");

		return sc.nextLine();
	}

	// 탈수있는 버스 정보 전부 출력메서드
	public void printBusInfo(List<DriveInfoDTO> dtos) {

		System.out.println();
		System.out.println("  🚍 배차조회              ");
		System.out.println(" ――――――――――――――――――――――――――――――――――――――――――――――――――――――");
		System.out.println("  " + dtos.get(0).getDriveDate());
		System.out.println(" ――――――――――――――――――――――――――――――――――――――――――――――――――――――");
		System.out.println("  " + "운행번호" + "  " + " 📍출발" + "     📍도착" + "    💰등급" + "    🪑잔여석" + "    💳가격");
		System.out.println(" ──────────────────────────────────────────────────────");
		for (DriveInfoDTO dto : dtos) {
			
			System.out.println("    " + dto.getDriveNum() + "     " + dto.getDepartTime() + "    " + dto.getArriveTime()
					+ "    " + dto.getBusGrade() + "       " + dto.getNumOfRemainSeats() + "     " + dto.getPrice());
			System.out.println(" ──────────────────────────────────────────────────────");
		}
		
		System.out.println(" ――――――――――――――――――――――――――――――――――――――――――――――――――――――");
	}

	// 예약확인 출력메서드
	public void printrecheck(MemberReservationDTO dto) {
		System.out.println();
		System.out.println("  🚍 가는 편 승차권 정보              ");
		System.out.println(" ―――――――――――――――――――――――――――――――――――――――――――");
		System.out.println("  2023.3.20 (월) 13:00 " + dto.getReservationDate() + dto.getDateTime());
		System.out.println(" ―――――――――――――――――――――――――――――――――――――――――――");
		System.out.println("  📍 출발지 : " + dto.getDepartment());
		System.out.println(" ───────────────────────────────────────────");
		System.out.println("  📍 도착지 : " + dto.getArrive());
		System.out.println(" ───────────────────────────────────────────");
		System.out.println("  💰 등급 : " + dto.getBusGrade());
		System.out.println(" ───────────────────────────────────────────");
		System.out.println("  🪑 좌석 : " + dto.getSeatCode());
		System.out.println(" ───────────────────────────────────────────");
		System.out.println("  💳 총 결제금액 : ");
		System.out.println(" ―――――――――――――――――――――――――――――――――――――――――――");
		System.out.println();
	}
	/*
	 * 
	 * public void Default() { StringBuffer sb = new StringBuffer();
	 * sb.append("\n"); sb.append("\n"); sb.append("\n"); sb.
	 * append("                  _       ___     ___      ___  __   __            ___    _   _    ___   \n"
	 * ); sb.
	 * append("              | |     / _ \\   / _ \\    | _ \\ \\ \\ / /    o O O  | _ )  | | | |  / __| \n"
	 * ); sb.
	 * append("               | |__  | (_) | | (_) |   |  _/  \\ V /    o       | _ \\  | |_| |  \\__ \\  \n"
	 * ); sb.
	 * append("                  |____|  \\___/   \\___/   _|_|_   _|_|_   TS__[O]  |___/   \\___/   |___/  \n"
	 * ); sb.append(
	 * "            _|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_| \"\"\" |_| \"\"\" | {======|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n"
	 * ); sb.append("         " +
	 * "   \"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'./o--000'\"`-0-0-'\"`-0-0-'\"`-0-0-' \n"
	 * ); sb.append("\n"); sb.append("\n"); sb.append("\n"); sb.append("\n");
	 * System.out.print(sb);
	 * 
	 * }
	 */

	public void printDefault() {
		StringBuffer sb = new StringBuffer();

		/*
		 * sb.append("------------------------------------------------------------\n");
		 * sb.append("\n");
		 * sb.append("-------------------------------------------------------------\n");
		 * System.out.print(sb);
		 */
	}

	// 예약티켓 화면
	public void ticket(MemberReservationDTO dto) {
		System.out.println();
		System.out.println("┌───────────────────────────────────────────┐");
		System.out.println();
		System.out.println("  🚍 LoopyBus Ticket 🚍              🎫 ₁/₁");
		System.out.println();
		System.out.println(" ───────────────────────────────────────────");
		System.out.println("  출발일시 📆 │" + dto.getReservationDate() + dto.getDateTime());
		System.out.println(" ───────────────────────────────────────────");
		System.out.println(" ―――――――――――――――――――――――――――――――――――――――――――");
		System.out.println();
		System.out.println("              ▣⣶⡶⣶⣴⡶⣶⣶▣\r\n" + "              ⣿⣿⣿⣿⣷⣾⣷⣿⣿\r\n"
				+ "              ⣷⣿⣿⡏⣷⣿⣿⣷⣷\r\n" + "              ⣿⣿⣷⢾⣿⣿⡏⣿⣿\r\n" + "              ▣⠟⠿⠷⠿⠷⠿⠿▣");
		System.out.println();
		System.out.println("            " + dto.getReservationNum());
		System.out.println();
		System.out.println(" ―――――――――――――――――――――――――――――――――――――――――――");
		System.out.println(" ───────────────────────────────────────────");
		System.out.println("     출발지 : " + dto.getDepartment()+ "   → " + "  도착지 :" + dto.getArrive());
		System.out.println(" ───────────────────────────────────────────");
		;
		System.out.println("         💰 등급           " + "    📍 좌석 ");
		System.out.print("           " + dto.getBusGrade() + "                 "+dto.getSeatCode());
		// mb.grade();
		System.out.print("                ");
		// mb.seat();
		System.out.println();
		System.out.println(" ───────────────────────────────────────────");
		System.out.println();
		System.out.println("└───────────────────────────────────────────┘");

		System.out.println();
		System.out.println();
	}

	public String findIdOrPw() {
		StringBuffer sb = new StringBuffer();
		barMiddle();
		bar();
		System.out.print(" ID,PW Find ");
		bar();
		barMiddle();
		sb.append("--------------------------------------\n");
		sb.append(" 1. ID 찾기🔑 | 2. PW 찾기🔒 | 3.돌아가기🚌 \n");
		sb.append("--------------------------------------\n");
		System.out.print(sb);
		System.out.println();
		System.out.print("원하시는 번호를 선택해 주세요 ₍ᐢ..ᐢ₎ : ");

		return sc.nextLine();
		// barEnd(); //파트 끝나는 곳에 넣어주세요
	}

}
