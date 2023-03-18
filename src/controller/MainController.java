package controller;

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
			// ����� ������
			memberView.printMenu();
		} else if (select.equals("2")) {
			// ������ ������
			while (true) {
				select = adminView.mainMenu();
				if (select.equals("1")) {
					// �������
					driveController.manageDrive();
				} else if (select.equals("2")) {
					// �������
//					adminView.reservationMenu();
					
				} else if (select.equals("3")) {
					// ����
					System.out.println("���α׷��� �����մϴ�.");
					System.exit(0);
				} else {
					System.out.println("�߸��� �Է��Դϴ�.");
				}
			}
		} else if (select.equals("3")) {
			// ����
			System.out.println("���α׷��� �����մϴ�.");
			System.exit(0);
		} else {
			System.out.println("�߸��� �Է��Դϴ�.");
		}
	}

}
