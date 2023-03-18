package controller;

import service.ReservationService;
import view.MemberView;

public class ReservationController {
	private static MemberView mView = new MemberView();
	private static ReservationService reservationService = new ReservationService(); 
	
	public static void reservationMenu() {
		int no = 0;

		do {
			no = mView.printReservation();
			switch (no) {
			case 1:
				System.out.println();
				System.out.println("예매하기 메뉴를 선택하셨습니다. 예매를 진행해주세요.");
				reservationService.reservateDepart();
				break;
			case 2:
				System.out.println();
				System.out.println("예매확인 메뉴를 선택하셨습니다. 아래는 예약한 내역입니다.");
				mView.printrecheck();
				break;
			case 3:
				System.out.println();
				System.out.println("예매취소 메뉴를 선택하셨습니다. 아래 예약한 내역을 보고 취소를 진행해주세요.");
				reservationService.recancle();
				break;
			case 4:
				System.out.println();
				System.out.println("종 료");
				
				System.exit(1);
			}
		} while (no != 4);
	}
}
