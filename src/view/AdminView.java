package view;

import java.util.List;
import java.util.Scanner;
import dto.DriveDTO;
import dto.ReservationDTO;
import util.TablePrinter;

public class AdminView {
	Scanner sc = new Scanner(System.in);
	   // 파트별 시작선
	   public static void bar() {
	      System.out.print("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
	   }

	   // 시작선 밑에 여백
	   public static void barMiddle() {
	      System.out.println();
	      System.out.println();
	      System.out.println();
	   }

	   // 파트별 끝선
	   public static void barEnd() {
	      System.out.println();
	      System.out.println();
	      System.out.print("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
	      System.out.println();
	      System.out.println();
	   }

	
	public String mainMenu() {
		StringBuffer sb = new StringBuffer();
		
	      barMiddle();
	      bar();
	      System.out.print(" Administrator ");
	      bar();
	      barMiddle();
	      sb.append("----------------------------------------\n");
	      sb.append(" 1. 운행관리🚌  | 2. 예약관리🚌 | 3. 종료🚌\n");
	      sb.append("----------------------------------------\n");

	      System.out.println(sb);
	      System.out.print("원하시는 번호를 선택해주세요 ₍ᐢ..ᐢ₎ : ");

	      return sc.nextLine();
	      //barEnd();
	}

	public String driveMenu(List<DriveDTO> driveDtos) {
		StringBuffer sb = new StringBuffer();

	      barMiddle();
	      bar();
	      System.out.print(" 운행관리 ");
	      bar();
	      barMiddle();

	      // 운행테이블 조회
//	      for (DriveDTO dto : driveDtos) {
//	         System.out.println(dto);
//	      }

	      TablePrinter<DriveDTO> tablePrinter = new TablePrinter<>(driveDtos);
	      tablePrinter.printTable();

	      sb.append("-------------------------------------------------------\n");
	      sb.append(" 1. 추가🚌 | 2. 수정🚌 | 3. 삭제🚌 | 4. 돌아가기🚌 | 5. 종료🚌\n");
	      sb.append("-------------------------------------------------------\n");

	      System.out.println(sb);
	      System.out.print("원하시는 번호를 선택해주세요 ₍ᐢ..ᐢ₎ : ");

	      return sc.nextLine();

	}

	public String reservationMenu(List<ReservationDTO> reservationDtos) {
		StringBuffer sb = new StringBuffer();

		 bar();
	      System.out.print(" 예약관리 ");
	      bar();
	      barMiddle();
	      // 예약테이블 조회
	      for (ReservationDTO dto : reservationDtos) {
	         System.out.println(dto);
	      }
	      sb.append("------------------------------------\n");
	      sb.append(" 1. 삭제🚌 | 2. 돌아가기🚌 | 3. 종료🚌\n");
	      sb.append("------------------------------------\n");

	      System.out.println(sb);
	      System.out.print("원하시는 번호를 선택해주세요 ₍ᐢ..ᐢ₎ : ");

	      return sc.nextLine();
	   }

}
