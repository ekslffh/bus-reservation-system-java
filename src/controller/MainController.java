package controller;

import service.MemberService;
import util.Screen;
import view.AdminView;
import view.CommonView;
import view.MemberView;

//1.예매하기 | 2.예매 확인 및 취소 | 3.마이페이지 | 4.종료\n
//// PW 변경
//case "4":
//	memberView.printDefault();
//	memberService.update();
//	break;
//// 회원탈퇴
//case "5":
//	memberView.printDefault();
//	memberService.deleteById();
//	break;
public class MainController {

	public static void main(String[] args) {
		// view
		CommonView commonView = new CommonView();
		AdminView adminView = new AdminView();
		MemberView memberView = new MemberView();
		// controller
		DriveController driveController = new DriveController();
		MemberController memberController = new MemberController();
		ReservationController reservationController = new ReservationController();
		// service
		MemberService memberService = new MemberService();

		// [1] 사용자 | [2] 관리자 | [3] 종료
		String select = commonView.startMenu();
		// 사용자 페이지
		if (select.equals("1")) {
			while (true) {
				System.out.print("\033[H\033[2J");
				// 1.로그인 | 2.회원가입 | 3.ID/PW 찾기 | 4.종료
				select = memberView.authMenu();
				switch (select) {
				// 로그인
				case "1":
					memberView.printDefault();
					// 로그인 성공시 예매관련 페이지로 이동
					if (memberService.login()) {
						while(true) {
							// 1.예매 | 마이페이지 | 3.로그아웃 | 4.종료
							select = memberView.mainMenu();
							if (select.equals("1")) { // 예매
								reservationController.memberReservation();
							} else if (select.equals("2")) { // 마이페이지
								memberController.manageMyPage();
							} else if (select.equals("3")) { // 로그아웃
								System.out.println("로그아웃 합니다.");
								break;
							} else if (select.equals("4")){ // 종료
								System.out.println("프로그램을 종료합니다.");
								System.exit(0);
							} else { 
								System.out.println("잘못된 입력입니다.");
							}
						}
					}
					break;
				// 회원가입
				case "2":
					memberService.insert();
					break;
				// ID/PW 찾기
				case "3":
					memberController.findIdOrPw();
					break;
				// 종료
				case "4":
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
				default:
					System.out.println("잘못된 입력입니다.");
				}
			}
			// 관리자 페이지
		} else if (select.equals("2")) {
			while (true) {
				select = adminView.mainMenu();
				if (select.equals("1")) {
					// 운행관리
					driveController.manageDrive();
				} else if (select.equals("2")) {
					// 예약관리
					reservationController.adminReservation();
				} else if (select.equals("3")) {
					// 종료
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
				} else {
					System.out.println("잘못된 입력입니다.");
				}
			}
		} else if (select.equals("3")) {
			// 종료
			System.out.println("프로그램을 종료합니다.");
			System.exit(0);
		} else {
			System.out.println("잘못된 입력입니다.");
		}
	}
}
