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

		sb.append("====================== ������ ������ ======================\n");
		sb.append("--------------------------------------------------\n");
		sb.append("[1] �������  | [2] ������� | [3] ����\n");
		sb.append("--------------------------------------------------\n");

		System.out.println(sb);
		System.out.print("���Ͻô� ��ȣ�� �������ּ���: ");

		return sc.nextLine();
	}

	public String driveMenu(List<DriveDTO> driveDtos) {
		StringBuffer sb = new StringBuffer();

		System.out.println("====================== ������� ======================\n");
		// �������̺� ��ȸ
//		for (DriveDTO dto : driveDtos) {
//			System.out.println(dto);
//		}
		TablePrinter<DriveDTO> tablePrinter = new TablePrinter<>(driveDtos);
		tablePrinter.printTable();
		sb.append("--------------------------------------------------\n");
		sb.append("[1] �߰� | [2] ���� | [3] ���� | [4] ���ư��� | [5] ����\n");
		sb.append("--------------------------------------------------\n");

		System.out.println(sb);
		System.out.print("���Ͻô� ��ȣ�� �������ּ���: ");

		return sc.nextLine();
	}

	public String reservationMenu(List<ReservationDTO> reservationDtos) {
		StringBuffer sb = new StringBuffer();

		System.out.println("====================== ������� ======================\n");
		// �������̺� ��ȸ
		for (ReservationDTO dto : reservationDtos) {
			System.out.println(dto);
		}
		sb.append("--------------------------------------------------\n");
		sb.append("[1] ���� | [2] ���ư��� | [3] ����\n");
		sb.append("--------------------------------------------------\n");

		System.out.println(sb);
		System.out.print("���Ͻô� ��ȣ�� �������ּ���: ");

		return sc.nextLine();
	}
}
