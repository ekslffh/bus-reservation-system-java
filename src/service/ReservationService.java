package service;

import java.util.List;
import java.util.Scanner;

import dao.ReservationDAO;
import dto.ReservationDTO;
import dto.ReservationInfoDTO;
import view.MemberView;

public class ReservationService {
	static MemberView view = new MemberView();
	static ReservationDTO dto = new ReservationDTO();
	static ReservationDAO dao = new ReservationDAO();
	static ReservationInfoDTO reserveDto = new ReservationInfoDTO();
	static Scanner sc = new Scanner(System.in);
	static Scanner s = new Scanner(System.in);
	
	public String depart;
	public String arrive;
	public String date;
	public String row;
	public String column;
	public static String departTerminal;
	public static String arriveTerminal;
	
	public void findDriveInfo() {
		
	}
	
	public static void reservateDepart() {
		int num = 1;
		List<ReservationDTO>List = dao.findAll();
		for(ReservationDTO dto: List) {
			System.out.println(num + ") " + dto.getTName());
			num++;
		}
		System.out.print("출발역을 선택해 주세요 'u' : ");
		int departNum = sc.nextInt();
		
		switch (departNum) {
		case 1:
			System.out.println("서울역 출발");
			departTerminal = "서울역";
			reserveDto.setDepartTerminal(departTerminal);
			break;
		case 2:
			System.out.println("대전역 출발");
			departTerminal = "대전역";
			reserveDto.setDepartTerminal(departTerminal);
			break;
		case 3:
			System.out.println("대구역 출발");
			departTerminal = "대구역";
			reserveDto.setDepartTerminal(departTerminal);
			break;
		case 4:
			System.out.println("부산역 출발");
			departTerminal = "부산역";
			reserveDto.setDepartTerminal(departTerminal);
			break;
		}
		System.out.println("-------------------------------------------------------------");
		reservateArrive();
	}
	
	public static void reservateArrive() {
		int num = 1;
		System.out.println("-------------------------------------------------------------");
		List<ReservationDTO>List = dao.findAll();
		for(ReservationDTO dto: List) {
			if(departTerminal.equals(dto.getTName())) {
				System.out.println(num + ") " + dto.getTName() + "(출발역)");
				num++;
			}else {
			System.out.println(num + ") " + dto.getTName());
			num++;
			}
		}
		System.out.print("도착역을 선택해 주세요 'u' : ");
		int departNum = sc.nextInt();
		
		switch (departNum) {
		case 1:
			System.out.println("서울역 도착");
			arriveTerminal = "서울역";
			reserveDto.setArriveTerminal(arriveTerminal);
			break;
		case 2:
			System.out.println("대전역 도착");
			arriveTerminal = "대전역";
			reserveDto.setArriveTerminal(arriveTerminal);
			break;
		case 3:
			System.out.println("대구역 도착");
			arriveTerminal = "대구역";
			reserveDto.setArriveTerminal(arriveTerminal);
			break;
		case 4:
			System.out.println("부산역 도착");
			arriveTerminal = "부산역";
			reserveDto.setArriveTerminal(arriveTerminal);
			break;
		}
		System.out.println("-------------------------------------------------------------");
		reservateTime();
	}
	
	public static void reservateTime() {
		
	}
	
	public static void reservate() {
			
		System.out.print("출발지 : ");
		String depart = sc.nextLine();

		System.out.print("도착지 : ");
		String arrive = sc.nextLine();

		System.out.print("날짜선택(YYYY-MM-dd) : ");
		String date = sc.nextLine();

		// 탈수있는 버스정보 전부 출력되는 뷰
		view.printBusInfo();

		System.out.println("좌석선택을 해주세요");
		System.out.println("  12");
		System.out.println("1 □■");
		System.out.println("2 □□");
		System.out.println("3 ■□");
		System.out.println("4 □□");
		System.out.println("5 □□");

		System.out.print("행을 선택해주세요 : ");
		String row = sc.nextLine();
		System.out.print("열을 선택해주세요 : ");
		String column = sc.nextLine();
		System.out.print("나이를 입력해주세요 : ");
		int age = Integer.parseInt(sc.nextLine());

		// 선택한 좌석정보 보여주는 뷰
		view.printrecheck();

		System.out.println("결제를 하시겠습니까 ? (Y/N)");
		
		String answer = sc.nextLine(); 
		
		if (answer.equals("Y") || answer.equals("y")) {
			System.out.println("결제가 완료되었습니다.");
			// 예약 완료시 예매번호 생성필요 , 좌석에 예약표시

			System.out.println();
			System.out.println("예매 완료!");
			System.out.println("루피기사가 안전하게 모시겠습뤂");

		} else {
			System.out.println("결제가 취소 되었습니다.");
			// 결제 안했을 경우는 예매번호 생성하지 않음

	
			System.exit(1);
		}
	}

	// 예약취소 메소드
	public static void recancle() {
		view.printrecheck();

		System.out.println("예약번호" + dto.getReservationNum() + " 예약정보를 취소하시겠습니까?(Y/N)");
		
		String cancle = s.nextLine();
		
		if (cancle.equals("Y") || cancle.equals("y")) {
			System.out.println("예약이 취소되었습니다.");

			// 예약내역 데이터에서 삭제하기
			dao.deleteById(dto.getReservationNum());

		} else {
			System.out.println("종 료");			
			System.exit(1);
		}
	}
}
