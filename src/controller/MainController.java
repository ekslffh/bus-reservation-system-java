package controller;

import java.util.List;
import dao.DriveDAO;
import dto.DriveDTO;
import view.AdminView;
import view.CommonView;
import view.MemberView;

public class MainController {

	public static void main(String[] args) {

		// view
		CommonView commonView = new CommonView();
		AdminView adminView = new AdminView();
		MemberView memberView = new MemberView();
		// controller
		DriveController driveController = new DriveController();
		
		String select = commonView.startMenu();
		if (select.equals("1")) {
			// 사용자 페이지
			memberView.printMenu();
		} else if (select.equals("2")) {
			// 관리자 페이지
			while (true) {
				select = adminView.mainMenu();
				if (select.equals("1")) {
					// 운행관리
					driveController.manageDrive();
				} else if (select.equals("2")) {
					// 예약관리
					adminView.reservationMenu();
					
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
