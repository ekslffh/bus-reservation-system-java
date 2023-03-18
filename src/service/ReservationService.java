package service;

import java.util.Scanner;

import dao.ReservationDAO;
import dto.ReservationDTO;
import view.MemberView;

public class ReservationService {
	static MemberView mView = new MemberView();
	static ReservationDTO redto = new ReservationDTO();
	static ReservationDAO redao = new ReservationDAO();
	static Scanner sc = new Scanner(System.in);
	static Scanner s = new Scanner(System.in);
	

	public String depart;
	public String arrive;
	public String date;
	public String row;
	public String column;

	public static void reservationMenu() {
		int no = 0;

		do {
			no = mView.printReservation();
			switch (no) {
			case 1:
				System.out.println();
				System.out.println("예매하기 메뉴를 선택하셨습니다. 예매를 진행해주세요.");
				reservate();
				break;
			case 2:
				System.out.println();
				System.out.println("예매확인 메뉴를 선택하셨습니다. 아래는 예약한 내역입니다.");
				mView.printrecheck();
				break;
			case 3:
				System.out.println();
				System.out.println("예매취소 메뉴를 선택하셨습니다. 아래 예약한 내역을 보고 취소를 진행해주세요.");
				recancle();
				break;
			case 4:
				System.out.println();
				System.out.println("종 료");
				
				System.exit(1);
			}
		} while (no != 4);
	}

	public static void reservate() {
		
	
		System.out.print("출발지 : ");
		String depart = sc.nextLine();

		System.out.print("도착지 : ");
		String arrive = sc.nextLine();

		System.out.print("날짜선택(YYYY-MM-dd) : ");
		String date = sc.nextLine();

		// 탈수있는 버스정보 전부 출력되는 뷰
		mView.printBusInfo();

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
		mView.printrecheck();

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
		mView.printrecheck();

//		System.out.println("예약번호" + redto.getReservationNum() + " 예약정보를 취소하시겠습니까?(Y/N)");
		
		String cancle = s.nextLine();
		
		if (cancle.equals("Y") || cancle.equals("y")) {
			System.out.println("예약이 취소되었습니다.");

			// 예약내역 데이터에서 삭제하기
//			redao.deleteById(redto.getReservationNum());

		} else {
			System.out.println("종 료");			
			System.exit(1);
		}
	}


}
