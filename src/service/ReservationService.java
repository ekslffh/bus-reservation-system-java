package service;

import java.util.Scanner;

import dao.ReservationDAO;
import dto.ReservationDTO;
import view.MemberView;

public class ReservationService {
	static MemberView mView = new MemberView();
	static ReservationDTO redto = new ReservationDTO();
	static ReservationDAO redao = new ReservationDAO();
	static Scanner sc = new Scanner(System.in);
	static Scanner s = new Scanner(System.in);
	

	public String depart;
	public String arrive;
	public String date;
	public String row;
	public String column;

	public static void reservationMenu() {
		int no = 0;

		do {
			no = mView.printReservation();
			switch (no) {
			case 1:
				System.out.println();
				System.out.println("�����ϱ� �޴��� �����ϼ̽��ϴ�. ���Ÿ� �������ּ���.");
				reservate();
				break;
			case 2:
				System.out.println();
				System.out.println("����Ȯ�� �޴��� �����ϼ̽��ϴ�. �Ʒ��� ������ �����Դϴ�.");
				mView.printrecheck();
				break;
			case 3:
				System.out.println();
				System.out.println("������� �޴��� �����ϼ̽��ϴ�. �Ʒ� ������ ������ ���� ��Ҹ� �������ּ���.");
				recancle();
				break;
			case 4:
				System.out.println();
				System.out.println("�� ��");
				
				System.exit(1);
			}
		} while (no != 4);
	}

	public static void reservate() {
		
	
		System.out.print("����� : ");
		String depart = sc.nextLine();

		System.out.print("������ : ");
		String arrive = sc.nextLine();

		System.out.print("��¥����(YYYY-MM-dd) : ");
		String date = sc.nextLine();

		// Ż���ִ� �������� ���� ��µǴ� ��
		mView.printBusInfo();

		System.out.println("�¼������� ���ּ���");
		System.out.println("  12");
		System.out.println("1 ���");
		System.out.println("2 ���");
		System.out.println("3 ���");
		System.out.println("4 ���");
		System.out.println("5 ���");

		System.out.print("���� �������ּ��� : ");
		String row = sc.nextLine();
		System.out.print("���� �������ּ��� : ");
		String column = sc.nextLine();
		System.out.print("���̸� �Է����ּ��� : ");
		int age = Integer.parseInt(sc.nextLine());

		// ������ �¼����� �����ִ� ��
		mView.printrecheck();

		System.out.println("������ �Ͻðڽ��ϱ� ? (Y/N)");
		
		String answer = sc.nextLine(); 
		
		if (answer.equals("Y") || answer.equals("y")) {
			System.out.println("������ �Ϸ�Ǿ����ϴ�.");
			// ���� �Ϸ�� ���Ź�ȣ �����ʿ� , �¼��� ����ǥ��

			System.out.println();
			System.out.println("���� �Ϸ�!");
			System.out.println("���Ǳ�簡 �����ϰ� ��ðڽ���");

		} else {
			System.out.println("������ ��� �Ǿ����ϴ�.");
			// ���� ������ ���� ���Ź�ȣ �������� ����

	
			System.exit(1);
		}
	}

	// ������� �޼ҵ�
	public static void recancle() {
		mView.printrecheck();

//		System.out.println("�����ȣ" + redto.getReservationNum() + " ���������� ����Ͻðڽ��ϱ�?(Y/N)");
		
		String cancle = s.nextLine();
		
		if (cancle.equals("Y") || cancle.equals("y")) {
			System.out.println("������ ��ҵǾ����ϴ�.");

			// ���೻�� �����Ϳ��� �����ϱ�
//			redao.deleteById(redto.getReservationNum());

		} else {
			System.out.println("�� ��");			
			System.exit(1);
		}
	}


}
