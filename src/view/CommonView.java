package view;

import java.util.Scanner;

public class CommonView {
	Scanner sc = new Scanner(System.in);
	
	public String startMenu() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("====================== ���ǹ��� ======================\n");
		sb.append("--------------------------------------------------\n");
		sb.append("[1] �����  | [2] ������  | [3] ����\n");
		sb.append("--------------------------------------------------\n");

		System.out.println(sb);
		System.out.print("��ȣ�� ������ �ּ��� : ");

		return sc.nextLine();
	}
	
	
}
