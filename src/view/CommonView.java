package view;

import java.util.Scanner;

public class CommonView {
	Scanner sc = new Scanner(System.in);
	
	public String startMenu() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("====================== 루피버스 ======================\n");
		sb.append("--------------------------------------------------\n");
		sb.append("[1] 사용자  | [2] 관리자  | [3] 종료\n");
		sb.append("--------------------------------------------------\n");

		System.out.println(sb);
		System.out.print("번호를 선택해 주세요 : ");

		return sc.nextLine();
	}
	
	
}
