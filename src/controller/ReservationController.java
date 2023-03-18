package controller;

import service.ReservationService;
import view.MemberView;

public class ReservationController {
	private static MemberView mView = new MemberView();
	private static ReservationService reservationService = new ReservationService(); 
	
	public static void reservationMenu() {
		int no = 0;

		do {
			no = mView.printReservation();
			switch (no) {
			case 1:
				System.out.println();
				System.out.println("�����ϱ� �޴��� �����ϼ̽�ϴ�. ���Ÿ� �������ּ���.");
				reservationService.reservateDepart();
				break;
			case 2:
				System.out.println();
				System.out.println("����Ȯ�� �޴��� �����ϼ̽�ϴ�. �Ʒ��� ������ �����Դϴ�.");
				mView.printrecheck();
				break;
			case 3:
				System.out.println();
				System.out.println("������� �޴��� �����ϼ̽�ϴ�. �Ʒ� ������ ����� ���� ��Ҹ� �������ּ���.");
				reservationService.recancle();
				break;
			case 4:
				System.out.println();
				System.out.println("� ��");
				
				System.exit(1);
			}
		} while (no != 4);
	}
}
