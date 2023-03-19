package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.BusDAO;
import dao.DriveDAO;
import dao.RouteDAO;
import dto.BusDTO;
import dto.DriveDTO;
import dto.RouteDTO;
import util.TablePrinter;

public class DriveService {
	private Scanner sc = new Scanner(System.in);
	private DriveDAO driveDao = new DriveDAO();
	private RouteDAO routeDao = new RouteDAO();
	private BusDAO busDao = new BusDAO();

	public List<DriveDTO> findAll() {
		return driveDao.findAll();
	}

	public List<RouteDTO> findRouteInfos() {
		return routeDao.findAll();
	}

	public List<BusDTO> findBusInfos() {
		return busDao.findAll();
	}

	public void insert() {
		System.out.println("==============�������� �߰�==============");
		System.out.println("==============�뼱 ����==============");
		// �뼱���� ��ȸ
		List<RouteDTO> routeDtos = findRouteInfos();
		for (RouteDTO dto : routeDtos) {
			System.out.println(dto);
		}
		System.out.println("==============���� ����==============");
		// �������� ��ȸ
		List<BusDTO> busDtos = findBusInfos();
		for (BusDTO dto : busDtos) {
			System.out.println(dto);
		}
		// �߰��� ������ �Է¹ޱ�
		DriveDTO driveDto = new DriveDTO();
		System.out.print("�����ȣ: ");
		driveDto.setDriveNumber(sc.nextLine());
		System.out.print("��߽ð�: ");
		driveDto.setDepartmentTime(sc.nextLine());
		System.out.print("�����ð�: ");
		driveDto.setArriveTime(sc.nextLine());
		System.out.print("�뼱��ȣ: ");
		driveDto.setRouteCode(sc.nextLine());
		System.out.print("������ȣ: ");
		driveDto.setBusCode(sc.nextLine());

		if (driveDao.insert(driveDto) > 0) {
			System.out.println("����ǥ ������ �����Ͽ����ϴ�.");
		} else {
			System.out.println("����ǥ ������ �����Ͽ����ϴ�.");
		}
		System.out.println(driveDto);
	}

	// ���൥���� ����
	// ���൥���͸� ������� �ϰ� ���߿� Ư�� ��ȣ�� �Է¹޾Ƽ�
	// 1.�ð�����, 2.��������, 3.�뼱����, 4.�������, 5.����
	// �ش��ϴ� Ư�� ���൥���͸� ������� �Ѵ�.
	public void update() {
		System.out.print("�����ȣ�� �Է��ϼ���: ");

		DriveDTO dto = driveDao.findById(sc.nextLine());
		List<DriveDTO> dtos = new ArrayList<>();
		dtos.add(dto);
		while (true) {
//			System.out.println(dto);
			TablePrinter<DriveDTO> tablePrinter = new TablePrinter<>(dtos);
			tablePrinter.printTable();
			StringBuffer sb = new StringBuffer();
			sb.append("--------------------------------------------------\n");
			sb.append("[1] �ð����� | [2] ������ü | [3] �뼱���� | [4] ������� | [5] ����\n");
			sb.append("--------------------------------------------------\n");
			System.out.println(sb);

			String select = sc.nextLine();
			if (select.equals("1")) {
				// �ð�����
				System.out.print("��߽ð�: ");
				dto.setDepartmentTime(sc.nextLine());
				System.out.print("�����ð�: ");
				dto.setArriveTime(sc.nextLine());
			} else if (select.equals("2")) {
				// ������ü
				System.out.print("������ȣ: ");
				dto.setBusCode(sc.nextLine());
			} else if (select.equals("3")) {
				// �뼱����
				System.out.print("�뼱��ȣ: ");
				dto.setRouteCode(sc.nextLine());
			} else if (select.equals("4")) {
				System.out.println("������ ��ҵǾ����ϴ�.");
				break;
			} else if (select.equals("5")) {
				int res = driveDao.update(dto);
				if (res > 0) {
					System.out.println("���� �Ϸ�Ǿ����ϴ�.");
				} else {
					System.out.println("���� �����Ͽ����ϴ�.");
				}
				break;
			} else {
				System.out.println("�߸��� �Է��Դϴ�.");
			}
		}
	}

	public void deleteById() {

		System.out.print("������ �����ڵ带 �Է��ϼ���: ");
		String driverNumber = sc.nextLine();

		int res = driveDao.deleteById(driverNumber);

		if (res > 0) {
			System.out.println("������ �Ϸ�Ǿ����ϴ�. �����մϴ�.");
		} else {
			System.out.println("���� �����Ͽ����ϴ�.");
		}
	}
}