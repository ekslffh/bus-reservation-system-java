package view;

import java.util.Scanner;
import dto.ReservationDTO;
import service.ReservationService;
import util.JDBCTemplate;

public class MemberView {
	private JDBCTemplate util = JDBCTemplate.getInstance();
	private static Scanner sc = new Scanner(System.in);
	ReservationService rservice = new ReservationService();
	ReservationDTO redto = new ReservationDTO();

	public String authMenu() {
		StringBuffer sb = new StringBuffer();
		sb.append("==================== ���ǹ��� ====================\n");
		sb.append("------------------------------------------------\n");
		sb.append("1.�α��� | 2.ȸ������ | 3.ID/PW ã�� | 4.����\n");
		sb.append("------------------------------------------------\n");
		sb.append("���ǹ����� ���Ű� ȯ���մϴ� !\n");
		sb.append("���Ͻô� ��ȣ�� ������ �ּ���: ");
		System.out.print(sb);
		return sc.nextLine();
	}
	
	// ���� ����ȭ�� ��� �޼���
	public String mainMenu() {		
		StringBuffer sb = new StringBuffer();
		sb.append("==================== ����� �޴� ====================\n");
		sb.append("--------------------------------------------------\n");
		sb.append("1.���� | 2.���������� | 3.�α׾ƿ� | 4.����\n");
		sb.append("--------------------------------------------------\n");
		sb.append("���ǹ����� ���Ű� ȯ���մϴ� !\n");
		sb.append("���Ͻô� ��ȣ�� ������ �ּ���: ");
		System.out.print(sb);
		return sc.nextLine();
	}
	
		// ���� ����ȭ�� ��� �޼���
		public String reservationMenu() {		
			StringBuffer sb = new StringBuffer();
			sb.append("==================== ���� �޴� ====================\n");
			sb.append("-------------------------------------------------\n");
			sb.append("1.�����ϱ� | 2.���� Ȯ�� �� ��� | 3.���������� | 4.����\n");
			sb.append("-------------------------------------------------\n");
			sb.append("���ǹ����� ���Ű� ȯ���մϴ� !\n");
			sb.append("���Ͻô� ��ȣ�� ������ �ּ���: ");
			System.out.print(sb);
			return sc.nextLine();
		}
		
		public String myPageMenu() {
			StringBuffer sb = new StringBuffer();
			sb.append("==================== ����������  ====================\n");
			sb.append("--------------------------------------------------\n");
			sb.append("1.��й�ȣ���� | 2.ȸ��Ż�� | 3.���������� | 4.����\n");
			sb.append("--------------------------------------------------\n");
			sb.append("���ǹ����� ���Ű� ȯ���մϴ� !\n");
			sb.append("���Ͻô� ��ȣ�� ������ �ּ���: ");
			System.out.print(sb);
			return sc.nextLine();
		}

	// Ż���ִ� ���� ���� ���� ��¸޼���
	public void printBusInfo() {
		System.out.println("-------------------------------------------------------------");
		// System.out.println("���� :" + dto.get ); ��߽ð� + �����ð� + �ܿ��¼� + ��� + ���� dto�� ��ӹ޾�
		// �ҷ����ֱ�
		System.out.println("-------------------------------------------------------------");
	}

	// ����Ȯ�� ��¸޼���
	public void printrecheck() {
		System.out.println();
		System.out.println("=====================================");
		System.out.println("������ ��������");
//		System.out.println("�����ȣ: "+ redto.getReservationNum());
//		System.out.println("�����: " + reserveDTO.getDepartTerminal());
//		System.out.println("������ : " + reserveDTO.getArriveTerminal());
//		System.out.println("�����ȣ "+ redto.getReservationNum());
		System.out.println("����� : " + rservice.depart);
		System.out.println("������ : " + rservice.arrive);
		System.out.println("��¥: " + rservice.date);
		System.out.println("�¼� : " + rservice.row + "��" + rservice.column + "��");
		System.out.println("=====================================");
		System.out.println();

	}

	public void Default() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		sb.append("\n");
		sb.append("\n");
		sb.append("                  _       ___     ___      ___  __   __            ___    _   _    ___   \n");
		sb.append("              | |     / _ \\   / _ \\    | _ \\ \\ \\ / /    o O O  | _ )  | | | |  / __| \n");
		sb.append("               | |__  | (_) | | (_) |   |  _/  \\ V /    o       | _ \\  | |_| |  \\__ \\  \n");
		sb.append("                  |____|  \\___/   \\___/   _|_|_   _|_|_   TS__[O]  |___/   \\___/   |___/  \n");
		sb.append(
				"            _|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"|_| \"\"\" |_| \"\"\" | {======|_|\"\"\"\"\"|_|\"\"\"\"\"|_|\"\"\"\"\"| \n");
		sb.append("         "
				+ "   \"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'\"`-0-0-'./o--000'\"`-0-0-'\"`-0-0-'\"`-0-0-' \n");
		sb.append("\n");
		sb.append("\n");
		sb.append("\n");
		sb.append("\n");
		System.out.print(sb);

	}

	public void printDefault() {
		StringBuffer sb = new StringBuffer();

		sb.append("------------------------------------------------------------\n");
		sb.append("\n");
		sb.append("-------------------------------------------------------------\n");
		System.out.print(sb);
	}

	public String findIdOrPw() {
		StringBuffer sb = new StringBuffer();
		sb.append("==================== ID,PW ã�� ==================\n");
		sb.append("-------------------------------------------------\n");
		sb.append("1.IDã�� | 2.PWã�� | 3.���ư���\n");
		sb.append("-------------------------------------------------\n");
		sb.append("���Ͻô� ��ȣ�� ������ �ּ���: ");
		System.out.print(sb);
		return sc.nextLine();
	}

}
