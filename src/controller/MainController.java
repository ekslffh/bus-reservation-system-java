package controller;

import java.util.Scanner;

import service.MemberService;
import util.Screen;
import view.AdminView;
import view.CommonView;
import view.MemberView;

public class MainController {

	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
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

		// [1] ����� | [2] ������ | [3] ����
		String select = commonView.startMenu();
		// ����� ������
		if (select.equals("1")) {
			while (true) {
				// 1.�α��� | 2.ȸ������ | 3.ID/PW ã�� | 4.����
				select = memberView.authMenu();
				switch (select) {
				// �α���
				case "1":
					memberView.printDefault();
					// �α��� ������ ���Ű��� �������� �̵�
					if (memberService.login()) {
						while(true) {
							// 1.���� | ���������� | 3.�α׾ƿ� | 4.����
							select = memberView.mainMenu();
							if (select.equals("1")) { // ����
								reservationController.memberReservation();
							} else if (select.equals("2")) { // ����������
								memberController.manageMyPage();
							} else if (select.equals("3")) { // �α׾ƿ�
								System.out.println("�α׾ƿ� �մϴ�.");
								break;
							} else if (select.equals("4")){ // ����
								System.out.println("���α׷��� �����մϴ�.");
								System.exit(0);
							} else { 
								System.out.println("�߸��� �Է��Դϴ�.");
							}
						}
					}
					break;
				// ȸ������
				case "2":
					memberService.insert();
					break;
				// ID/PW ã��
				case "3":
					memberController.findIdOrPw();
					break;
				// ����
				case "4":
					System.out.println("���α׷��� �����մϴ�.");
					System.exit(0);
				default:
					System.out.println("�߸��� �Է��Դϴ�.");
				}
			}
			// ������ ������
		} else if (select.equals("2")) {
			System.out.print("�����ں�й�ȣ: ");
			String pw = sc.nextLine();
			if (!pw.equals("javajavajava")) {
				System.out.println("������ ������ �����ϴ�.");
				System.exit(0);
			}
			while (true) {
				select = adminView.mainMenu();
				if (select.equals("1")) {
					// �������
					driveController.manageDrive();
				} else if (select.equals("2")) {
					// �������
					reservationController.adminReservation();
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
