package view;

import java.util.List;
import java.util.Scanner;
import dto.DriveDTO;
import dto.ReservationDTO;
import util.TablePrinter;

public class AdminView {
	Scanner sc = new Scanner(System.in);

	public String mainMenu() {
		StringBuffer sb = new StringBuffer();

		sb.append("====================== 관리자 페이지 ======================\n");
		sb.append("--------------------------------------------------\n");
		sb.append("[1] 운행관리  | [2] 예약관리 | [3] 종료\n");
		sb.append("--------------------------------------------------\n");

		System.out.println(sb);
		System.out.print("원하시는 번호를 선택해주세요: ");

		return sc.nextLine();
	}

	public String driveMenu(List<DriveDTO> driveDtos) {
		StringBuffer sb = new StringBuffer();

		System.out.println("====================== 운행관리 ======================\n");
		// 운행테이블 조회
//		for (DriveDTO dto : driveDtos) {
//			System.out.println(dto);
//		}
		TablePrinter<DriveDTO> tablePrinter = new TablePrinter<>(driveDtos);
		tablePrinter.printTable();
		sb.append("--------------------------------------------------\n");
		sb.append("[1] 추가 | [2] 수정 | [3] 삭제 | [4] 돌아가기 | [5] 종료\n");
		sb.append("--------------------------------------------------\n");

		System.out.println(sb);
		System.out.print("원하시는 번호를 선택해주세요: ");

		return sc.nextLine();
	}

	public String reservationMenu(List<ReservationDTO> reservationDtos) {
		StringBuffer sb = new StringBuffer();

		System.out.println("====================== 예약관리 ======================\n");
		// 예약테이블 조회
		for (ReservationDTO dto : reservationDtos) {
			System.out.println(dto);
		}
		sb.append("--------------------------------------------------\n");
		sb.append("[1] 삭제 | [2] 돌아가기 | [3] 종료\n");
		sb.append("--------------------------------------------------\n");

		System.out.println(sb);
		System.out.print("원하시는 번호를 선택해주세요: ");

		return sc.nextLine();
	}
}
