package controller;

import java.util.List;
import java.util.Scanner;
import dto.BusDTO;
import dto.DriveDTO;
import dto.RouteDTO;
import service.DriveService;
import view.AdminView;

public class DriveController {	
	private Scanner sc = new Scanner(System.in);
	private DriveService driveService = new DriveService();
	private AdminView adminView = new AdminView();
	
	public void manageDrive() {
		while (true) {
			// ������ ��ȸ : ���൥���ʹ� ����ؼ� ������� �ϰ� �߰�, ������ ������Ʈ �� ������ �����;� �Ѵ�.!
			String select = adminView.driveMenu(driveService.findAll());
			if (select.equals("1")) {
				// ���൥���� �߰�
				// ���൥���͸� �߰��ϱ� ���ؼ��� �뼱������ �ʿ��ϰ�, ���������� �ʿ��ϴ�. 
				// �Ѵ� ��ȸ�ؼ� ��������
				driveService.insert();
			} else if (select.equals("2")) {
				// ���൥���� ����
				driveService.update();
			} else if (select.equals("3")) {
				// ���൥���� ����
				driveService.deleteById();
			} else if (select.equals("4")) {
				// ���ư���
				System.out.println("������ ������������ ���ư��ϴ�.");
				break;
			} else if (select.equals("5")) {
				// ����
				System.out.println("���α׷��� �����մϴ�.");
				System.exit(0);
			} else {
				System.out.println("�߸��� �Է��Դϴ�.");
			}
		}
	}
	
}