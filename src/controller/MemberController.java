package controller;

import java.util.Scanner;
import dto.MemberDTO;
import service.MemberService;
import view.MemberView;

public class MemberController {
	public static MemberDTO member = null;
	private static Scanner sc = new Scanner(System.in);
	private static MemberService memberService = new MemberService();
	private static MemberView memberView = new MemberView();

	public void manageMyPage() {
		while (true) {
			// 1.��й�ȣ���� | 2.ȸ��Ż�� | 3.���������� | 4.����\n
			String select = memberView.myPageMenu();
			if (select.equals("1")) {
				// ��й�ȣ ����
				memberService.update();
			} else if (select.equals("2")) {
				// ȸ��Ż��
				memberService.deleteById();
			} else if (select.equals("3")) {
				// ����������
				System.out.println("������������ �̵��մϴ�.");
				break;
			} else if (select.equals("4")) {
				System.out.println("���α׷��� �����մϴ�.");
				System.exit(0);
			} else {
				System.out.println("�߸��� �Է��Դϴ�.");
			}
		}
	}
	public void run() {
		memberView.authMenu();
		do {
			switch (sc.nextLine()) {
			case "1":
				memberView.printDefault();
				memberService.login();
				break;
			case "2":
				memberView.printDefault();
				memberService.insert();
				break;
			case "3":
				memberView.findIdOrPw();
				findIdOrPw();
				break;
			case "4":
				memberView.printDefault();
				memberService.update();
				break;
			case "5":
				memberView.printDefault();
				memberService.deleteById();
				break;
			case "6":
				System.out.println("����");
				sc.close();
				break;
			default:
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
				memberView.authMenu();
			}

		} while (true);
	}

	public void findIdOrPw() {
		String selectIDPW = memberView.findIdOrPw();
		if (selectIDPW.equals("1")) {
			System.out.println("���̵� ã��");
			memberService.findByPw();
		} else if (selectIDPW.equals("2")) {
			System.out.println("��й�ȣ ã��");
			memberService.findById();
		} else if (selectIDPW.equals("3")) {
			System.out.println("����ȭ������ ���ư��ϴ�.");
		} else {
			System.out.println("�߸��� �Է��Դϴ�.");
		}
	}
}
