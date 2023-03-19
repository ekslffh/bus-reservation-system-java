package controller;

import service.ReservationService;
import view.MemberView;

public class ReservationController {
	private static MemberView memberView = new MemberView();
	private static ReservationService reservationService = new ReservationService();

	public void memberReservation() {
		boolean isBack = false;
		while (true) {
			if (isBack)
				break;
			String select = memberView.reservationMenu();
			// 1.예매하기 | 2.예매 확인 및 취소 | 3.메인페이지 | 4.종료
			switch (select) {
			// 예매하기
			case "1":
				reservationService.reservate();
				break;
			// 예매확인 + 취소
			case "2":
				reservationService.checkMyReservation(MemberController.member.getM_id());
				break;
			// 종료
			case "3":
				System.out.println("메인페이지로 이동합니다.");
				isBack = true;
				break;
			case "4":
				System.out.println("종료합니다.");
				System.exit(1);
			default:
				System.out.println("잘못된 입력입니다.");
			}
		}
	}

	public void adminReservation() {
		reservationService.checkAllReservation();
	}
}
