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
			// 1.비밀번호변경 | 2.회원탈퇴 | 3.메인페이지 | 4.종료\n
			String select = memberView.myPageMenu();
			if (select.equals("1")) {
				// 비밀번호 변경
				memberService.update();
			} else if (select.equals("2")) {
				// 회원탈퇴
				memberService.deleteById();
			} else if (select.equals("3")) {
				// 메인페이지
				System.out.println("메인페이지로 이동합니다.");
				break;
			} else if (select.equals("4")) {
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			} else {
				System.out.println("잘못된 입력입니다.");
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
				System.out.println("종료");
				sc.close();
				break;
			default:
				System.out.println("잘못 입력하셨습니다.");
				memberView.authMenu();
			}

		} while (true);
	}

	public void findIdOrPw() {
		String selectIDPW = memberView.findIdOrPw();
		if (selectIDPW.equals("1")) {
			System.out.println("아이디 찾기");
			memberService.findByPw();
		} else if (selectIDPW.equals("2")) {
			System.out.println("비밀번호 찾기");
			memberService.findById();
		} else if (selectIDPW.equals("3")) {
			System.out.println("이전화면으로 돌아갑니다.");
		} else {
			System.out.println("잘못된 입력입니다.");
		}
	}
}
