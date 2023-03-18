package service;

import java.util.List;
import java.util.Scanner;

import dao.ReservationDAO;
import dto.ReservationDTO;
import dto.ReservationInfoDTO;
import view.MemberView;

public class ReservationService {
	static MemberView view = new MemberView();
	static ReservationDTO dto = new ReservationDTO();
	static ReservationDAO dao = new ReservationDAO();
	static ReservationInfoDTO reserveDto = new ReservationInfoDTO();
	static Scanner sc = new Scanner(System.in);
	static Scanner s = new Scanner(System.in);
	
	public String depart;
	public String arrive;
	public String date;
	public String row;
	public String column;
	public static String departTerminal;
	public static String arriveTerminal;
	
	public void findDriveInfo() {
		
	}
	
	public static void reservateDepart() {
		int num = 1;
		List<ReservationDTO>List = dao.findAll();
		for(ReservationDTO dto: List) {
			System.out.println(num + ") " + dto.getTName());
			num++;
		}
		System.out.print("��߿��� ������ �ּ��� 'u' : ");
		int departNum = sc.nextInt();
		
		switch (departNum) {
		case 1:
			System.out.println("���￪ ���");
			departTerminal = "���￪";
			reserveDto.setDepartTerminal(departTerminal);
			break;
		case 2:
			System.out.println("������ ���");
			departTerminal = "������";
			reserveDto.setDepartTerminal(departTerminal);
			break;
		case 3:
			System.out.println("�뱸�� ���");
			departTerminal = "�뱸��";
			reserveDto.setDepartTerminal(departTerminal);
			break;
		case 4:
			System.out.println("�λ꿪 ���");
			departTerminal = "�λ꿪";
			reserveDto.setDepartTerminal(departTerminal);
			break;
		}
		System.out.println("-------------------------------------------------------------");
		reservateArrive();
	}
	
	public static void reservateArrive() {
		int num = 1;
		System.out.println("-------------------------------------------------------------");
		List<ReservationDTO>List = dao.findAll();
		for(ReservationDTO dto: List) {
			if(departTerminal.equals(dto.getTName())) {
				System.out.println(num + ") " + dto.getTName() + "(��߿�)");
				num++;
			}else {
			System.out.println(num + ") " + dto.getTName());
			num++;
			}
		}
		System.out.print("�������� ������ �ּ��� 'u' : ");
		int departNum = sc.nextInt();
		
		switch (departNum) {
		case 1:
			System.out.println("���￪ ����");
			arriveTerminal = "���￪";
			reserveDto.setArriveTerminal(arriveTerminal);
			break;
		case 2:
			System.out.println("������ ����");
			arriveTerminal = "������";
			reserveDto.setArriveTerminal(arriveTerminal);
			break;
		case 3:
			System.out.println("�뱸�� ����");
			arriveTerminal = "�뱸��";
			reserveDto.setArriveTerminal(arriveTerminal);
			break;
		case 4:
			System.out.println("�λ꿪 ����");
			arriveTerminal = "�λ꿪";
			reserveDto.setArriveTerminal(arriveTerminal);
			break;
		}
		System.out.println("-------------------------------------------------------------");
		reservateTime();
	}
	
	public static void reservateTime() {
		
	}
	
	public static void reservate() {
			
		System.out.print("����� : ");
		String depart = sc.nextLine();

		System.out.print("������ : ");
		String arrive = sc.nextLine();

		System.out.print("��¥����(YYYY-MM-dd) : ");
		String date = sc.nextLine();

		// Ż���ִ� �������� ���� ��µǴ� ��
		view.printBusInfo();

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
		view.printrecheck();

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
		view.printrecheck();

		System.out.println("�����ȣ" + dto.getReservationNum() + " ���������� ����Ͻðڽ��ϱ�?(Y/N)");
		
		String cancle = s.nextLine();
		
		if (cancle.equals("Y") || cancle.equals("y")) {
			System.out.println("������ ��ҵǾ����ϴ�.");

			// ���೻�� �����Ϳ��� �����ϱ�
			dao.deleteById(dto.getReservationNum());

		} else {
			System.out.println("�� ��");			
			System.exit(1);
		}
	}
}
