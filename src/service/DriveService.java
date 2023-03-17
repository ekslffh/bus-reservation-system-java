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
		
		sb.append("1. 생성");
		sb.append("2. 수정");
		sb.append("3. 삭제");
		System.out.println(sb);
		System.out.println("원하시는 기능을 선택해주세요");
		System.out.println("1. 생성  2. 수정  3. 삭제 ");
		select = Integer.parseInt(sc.nextLine());
		
		return select;
		
		
	}
	

	public void insert() { 
		//버스정보 가져오기
		//일정정보 가져오기
		//버스 일정 각각 DTO 필요 o
		
		
		
		DriveDTO dto = new DriveDTO();
		
		dto.setBusCode(dto.getBusCode());
		dto.setScheduleCode(dto.getScheduleCode());
		
		if (dao.insert(dto)>0) {
			System.out.println("운행표 생성에 성공하였습니다.");
		}else {
			System.out.println("운행표 생성에 실패하였습니다.");
		}
		System.out.println(dto);
		
	}
	
	
	public void update() {
		
		DriveDTO dto = new DriveDTO();
		
		System.out.println("운행코드를 입력하세요");
		String driverNumber = sc.nextLine();
		
		System.out.println("1. 버스교체  2. 일정변경  3. 모두변경");
		int change = Integer.parseInt(sc.nextLine());
		
		if(change==1) {
			System.out.println("교체할 버스코드를 입력하세요 :");
			String busCode = sc.nextLine();
			dao.updateBus(dto);	
		}else if(change==2) {
			System.out.println("교체할 일정을 입력하세요");
			String scheduleCode = sc.nextLine();
			dao.updateSchedule(dto);
		}else if(change==3) {
			System.out.println("교체할 버스코드를 입력하세요 :");
			String busCode = sc.nextLine();
			System.out.println("교체할 일정을 입력하세요");
			String scheduleCode = sc.nextLine();
			dao.update(dto);
			
		}
		
		System.out.println("교체가 완료되었습니다. 감사합니다.");
	}
    public void deleteById() {
		  
		  DriveDTO dto = new DriveDTO();
		  
		  System.out.println("삭제할 운행코드를 입력하세요");
		  String driverNumber = sc.nextLine();
		  
		  dao.deleteById(driverNumber);
		  
		  System.out.println("삭제가 완료되었습니다. 감사합니다.");
	
		
		}
		
		
	}

