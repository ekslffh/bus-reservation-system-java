package controller;

import java.util.Scanner;

import dao.MemberDAO;
import dto.MemberDTO;
import service.MemberService;
import view.MemberView;

public class MemberController {
	private static final String y = null;
	private static Scanner sc = new Scanner(System.in);
	private static MemberDAO dao = new MemberDAO();
	private static MemberService meberService = new MemberService();
	private static MemberView memberView = new MemberView();
	private static String mId;

	public static int getMenu() {
		int select = 0;
		select = Integer.parseInt(sc.nextLine());

		return select;
	}

	public static void run() {
		memberView.printMenu();
		int no = 0;

		do {
			no = getMenu();
			switch (no) {
			case 1:
				memberView.printDefault();
				meberService.rogin();
				break;
			case 2:
				memberView.printDefault();
				meberService.insert();
				break;
			case 3:
				//int selectiIDPW = Integer.parseInt(sc.nextLine());
				memberView.printIDorPW();
				IDorPW();
				break;
			case 4:
				memberView.printDefault();
				MemberService.update();
				break;
			case 5:
				memberView.printDefault();
				meberService.deleteById();
				break;
			case 6:
				System.out.println("종료");
				sc.close();
				break;
			default:
				System.out.println("잘못 입력하셨습니다.");
				memberView.printMenu();
			}

		} while (no != 6);
	}
	
	private static void IDorPW() {
		int selectIDPW = Integer.parseInt(sc.nextLine());
		
		if(selectIDPW == 1) {
			System.out.println("아이디 찾기");
			meberService.findByPw();
		}else if(selectIDPW == 2) {
			System.out.println("비밀번호 찾기");
			meberService.findById();
		}
	}
}
