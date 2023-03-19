package controller;

import java.util.Scanner;
import service.DriveService;
import view.AdminView;

public class DriveController {
	private static Scanner sc = new Scanner(System.in);
	private static DriveService driveService = new DriveService();
	private static AdminView adminView = new AdminView();

	public void manageDrive() {
		while (true) {
			// 데이터 조회: 운영데이터는 계속해서 보여줘야 하고 추가, 수정시 업데이트 된 정보를 가져와야 한다.
			String select = adminView.driveMenu(driveService.findAll());
			if (select.equals("1")) {
				// 운행데이터 추가
				// 운행데이터를 추가하기 위해서는 노선정보가 필요하고, 버스정보가 필요하다.
				// 둘다 조회해서 가져오기
				driveService.insert();
			} else if (select.equals("2")) {
				// 운행데이터 수정
				driveService.update();
			} else if (select.equals("3")) {
				// 운행데이터 삭제
				driveService.deleteById();
			} else if (select.equals("4")) {
				// 돌아가기
				System.out.println("관리자 메인페이지로 돌아갑니다.");
				break;
			} else if (select.equals("5")) {
				// 종료
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
	}
}
