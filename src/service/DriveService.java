package service;

import java.util.List;
import java.util.Scanner;

import dao.DriveDAO;
import dto.BusDTO;
import dto.DriveDTO;
import dto.ScheduleDTO;


public class DriveService {
	private static Scanner sc = new Scanner(System.in);
	private static DriveDAO dao = new DriveDAO();
	
	public int getMenu() {
		int select = 0;
		StringBuffer sb = new StringBuffer();
		
		List<DriveDTO> list = dao.findAll();
		
		sb.append("1. ����");
		sb.append("2. ����");
		sb.append("3. ����");
		System.out.println(sb);
		System.out.println("���Ͻô� ����� �������ּ���");
		System.out.println("1. ����  2. ����  3. ���� ");
		select = Integer.parseInt(sc.nextLine());
		
		return select;
		
		
	}
	

	public void insert() { 
		//�������� ��������
		//�������� ��������
		//���� ���� ���� DTO �ʿ� o
		
		
		
		DriveDTO dto = new DriveDTO();
		
		dto.setBusCode(dto.getBusCode());
		dto.setScheduleCode(dto.getScheduleCode());
		
		if (dao.insert(dto)>0) {
			System.out.println("����ǥ ������ �����Ͽ����ϴ�.");
		}else {
			System.out.println("����ǥ ������ �����Ͽ����ϴ�.");
		}
		System.out.println(dto);
		
	}
	
	
	public void update() {
		
		DriveDTO dto = new DriveDTO();
		
		System.out.println("�����ڵ带 �Է��ϼ���");
		String driverNumber = sc.nextLine();
		
		System.out.println("1. ������ü  2. ��������  3. ��κ���");
		int change = Integer.parseInt(sc.nextLine());
		
		if(change==1) {
			System.out.println("��ü�� �����ڵ带 �Է��ϼ��� :");
			String busCode = sc.nextLine();
			dao.updateBus(dto);	
		}else if(change==2) {
			System.out.println("��ü�� ������ �Է��ϼ���");
			String scheduleCode = sc.nextLine();
			dao.updateSchedule(dto);
		}else if(change==3) {
			System.out.println("��ü�� �����ڵ带 �Է��ϼ��� :");
			String busCode = sc.nextLine();
			System.out.println("��ü�� ������ �Է��ϼ���");
			String scheduleCode = sc.nextLine();
			dao.update(dto);
			
		}
		
		System.out.println("��ü�� �Ϸ�Ǿ����ϴ�. �����մϴ�.");
	}
    public void deleteById() {
		  
		  DriveDTO dto = new DriveDTO();
		  
		  System.out.println("������ �����ڵ带 �Է��ϼ���");
		  String driverNumber = sc.nextLine();
		  
		  dao.deleteById(driverNumber);
		  
		  System.out.println("������ �Ϸ�Ǿ����ϴ�. �����մϴ�.");
	
		
		}
		
		
	}

