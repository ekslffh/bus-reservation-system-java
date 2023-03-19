package controller;

import service.ReservationService;
import view.MemberView;

public class ReservationController {
	private static MemberView memberView = new MemberView();
	private static ReservationService reservationService = new ReservationService();

	public void memberReservation() {
		boolean isBack = false;
		while (true) {
			if (isBack)
				break;
			String select = memberView.reservationMenu();
			// 1.�����ϱ� | 2.���� Ȯ�� �� ��� | 3.���������� | 4.����
			switch (select) {
			// �����ϱ�
			case "1":
				reservationService.reservate();
				break;
			// ����Ȯ�� + ���
			case "2":
				reservationService.checkMyReservation(MemberController.member.getM_id());
				break;
			// ����
			case "3":
				System.out.println("������������ �̵��մϴ�.");
				isBack = true;
				break;
			case "4":
				System.out.println("�����մϴ�.");
				System.exit(1);
			default:
				System.out.println("�߸��� �Է��Դϴ�.");
			}
		}
	}

	public void adminReservation() {
		reservationService.checkAllReservation();
	}
}
