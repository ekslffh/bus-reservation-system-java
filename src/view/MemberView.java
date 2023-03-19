package view;

import java.util.Scanner;
import dto.ReservationDTO;
import service.ReservationService;
import util.JDBCTemplate;

public class MemberView {
	private JDBCTemplate util = JDBCTemplate.getInstance();
	private static Scanner sc = new Scanner(System.in);
	ReservationService rservice = new ReservationService();
	ReservationDTO redto = new ReservationDTO();

	public String authMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("==================== 루피버스 ====================\n");
		sb.append("------------------------------------------------\n");
		sb.append("1.로그인 | 2.회원가입 | 3.ID/PW 찾기 | 4.종료\n");
		sb.append("------------------------------------------------\n");
		sb.append("루피버스에 오신걸 환영합니다 !\n");
		sb.append("원하시는 번호를 선택해 주세요: ");
		System.out.print(sb);
		return sc.nextLine();
	}
	
	// 예매 시작화면 출력 메서드
	public String mainMenu() {		
		StringBuffer sb = new StringBuffer();
		sb.append("==================== 사용자 메뉴 ====================\n");
		sb.append("--------------------------------------------------\n");
		sb.append("1.예매 | 2.마이페이지 | 3.로그아웃 | 4.종료\n");
		sb.append("--------------------------------------------------\n");
		sb.append("루피버스에 오신걸 환영합니다 !\n");
		sb.append("원하시는 번호를 선택해 주세요: ");
		System.out.print(sb);
		return sc.nextLine();
	}
	
		// 예매 시작화면 출력 메서드
		public String reservationMenu() {		
			StringBuffer sb = new StringBuffer();
			sb.append("==================== 예약 메뉴 ====================\n");
			sb.append("-------------------------------------------------\n");
			sb.append("1.예매하기 | 2.예매 확인 및 취소 | 3.메인페이지 | 4.종료\n");
			sb.append("-------------------------------------------------\n");
			sb.append("루피버스에 오신걸 환영합니다 !\n");
			sb.append("원하시는 번호를 선택해 주세요: ");
			System.out.print(sb);
			return sc.nextLine();
		}
		
		public String myPageMenu() {
			StringBuffer sb = new StringBuffer();
			sb.append("==================== 마이페이지  ====================\n");
			sb.append("--------------------------------------------------\n");
			sb.append("1.비밀번호변경 | 2.회원탈퇴 | 3.메인페이지 | 4.종료\n");
			sb.append("--------------------------------------------------\n");
			sb.append("루피버스에 오신걸 환영합니다 !\n");
			sb.append("원하시는 번호를 선택해 주세요: ");
			System.out.print(sb);
			return sc.nextLine();
		}

	// 탈수있는 버스 정보 전부 출력메서드
	public void printBusInfo() {
		System.out.println("-------------------------------------------------------------");
		// System.out.println("버스 :" + dto.get ); 출발시간 + 도착시간 + 잔여좌석 + 등급 + 가격 dto에 상속받아
		// 불러와주기
		System.out.println("-------------------------------------------------------------");
	}

	// 예약확인 출력메서드
	public void printrecheck() {
		System.out.println();
		System.out.println("=====================================");
		System.out.println("예약한 버스내역");
//		System.out.println("예약번호: "+ redto.getReservationNum());
//		System.out.println("출발지: " + reserveDTO.getDepartTerminal());
//		System.out.println("도착지 : " + reserveDTO.getArriveTerminal());
//		System.out.println("예약번호 "+ redto.getReservationNum());
		System.out.println("출발지 : " + rservice.depart);
		System.out.println("도착지 : " + rservice.arrive);
		System.out.println("날짜: " + rservice.date);
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
		System.out.print(sb);

	}

	public void printDefault() {
		StringBuffer sb = new StringBuffer();

		sb.append("------------------------------------------------------------\n");
		sb.append("\n");
		sb.append("-------------------------------------------------------------\n");
		System.out.print(sb);
	}

	public String findIdOrPw() {
		StringBuffer sb = new StringBuffer();
		sb.append("==================== ID,PW 찾기 ==================\n");
		sb.append("-------------------------------------------------\n");
		sb.append("1.ID찾기 | 2.PW찾기 | 3.돌아가기\n");
		sb.append("-------------------------------------------------\n");
		sb.append("원하시는 번호를 선택해 주세요: ");
		System.out.print(sb);
		return sc.nextLine();
	}

}
