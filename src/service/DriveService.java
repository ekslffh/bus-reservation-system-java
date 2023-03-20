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
	
    // 파트별 시작선
    public static void bar() {
       System.out.print("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
    }

    // 시작선 밑에 여백
    public static void barMiddle() {
       System.out.println();
       System.out.println();
       System.out.println();
    }

    // 파트별 끝선
    public static void barEnd() {
       System.out.println();
       System.out.println();
       System.out.print("▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃▃");
       System.out.println();
       System.out.println();
    }

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
		barMiddle();
		bar();
		System.out.print(" 운행정보 추가 ");
		bar();
		barMiddle();

		System.out.println("――――――――――――――――――――――――― 노선 정보 ―――――――――――――――――――――――――");
		System.out.println();
		// 노선정보 조회
		List<RouteDTO> routeDtos = findRouteInfos();
		for (RouteDTO dto : routeDtos) {
			System.out.println(dto);
		}
		System.out.println();
		System.out.println("――――――――――――――――――――――――― 버스 정보 ―――――――――――――――――――――――――");
		System.out.println();

		// 버스정보 조회
		List<BusDTO> busDtos = findBusInfos();
		for (BusDTO dto : busDtos) {
			System.out.println(dto);
		}
		// 추가할 데이터 입력받기
		DriveDTO driveDto = new DriveDTO();
		System.out.println();

		System.out.print("운행번호: ");
		driveDto.setDriveNumber(sc.nextLine());
		System.out.print("출발시간: ");
		driveDto.setDepartmentTime(sc.nextLine());
		System.out.print("도착시간: ");
		driveDto.setArriveTime(sc.nextLine());
		System.out.print("노선번호: ");
		driveDto.setRouteCode(sc.nextLine());
		System.out.print("버스번호: ");
		driveDto.setBusCode(sc.nextLine());

		if (driveDao.insert(driveDto) > 0) {
			System.out.println("운행표 생성에 성공하였습니다.");
		} else {
			System.out.println("운행표 생성에 실패하였습니다.");
		}
		System.out.println(driveDto);
	}

	// 운행데이터 수정
	// 운행데이터를 보여줘야 하고 그중에 특정 번호를 입력받아서
	// 1.시간변경, 2.버스변경, 3.노선변경, 4.수정취소, 5.저장
	// 해당하는 특정 운행데이터를 보여줘야 한다.
	public void update() {
		System.out.print("운행번호를 입력하세요: ");

		DriveDTO dto = driveDao.findById(sc.nextLine());
		List<DriveDTO> dtos = new ArrayList<>();
		dtos.add(dto);
		while (true) {
//			System.out.println(dto);
			TablePrinter<DriveDTO> tablePrinter = new TablePrinter<>(dtos);
			tablePrinter.printTable();
			StringBuffer sb = new StringBuffer();
			sb.append("-----------------------------------------------------------\n");
	         sb.append(" 1. 시간변경🚌 | 2. 버스교체🚌 | 3. 노선변경🚌 | 4. 수정취소🚌 | 5. 저장🚌\n");
	         sb.append("-----------------------------------------------------------\n");

			System.out.println(sb);

			String select = sc.nextLine();
			if (select.equals("1")) {
				// 시간변경
				System.out.print("출발시간: ");
				dto.setDepartmentTime(sc.nextLine());
				System.out.print("도착시간: ");
				dto.setArriveTime(sc.nextLine());
			} else if (select.equals("2")) {
				// 버스교체
				System.out.print("버스번호: ");
				dto.setBusCode(sc.nextLine());
			} else if (select.equals("3")) {
				// 노선변경
				System.out.print("노선번호: ");
				dto.setRouteCode(sc.nextLine());
			} else if (select.equals("4")) {
				System.out.println("수정이 취소되었습니다.");
				break;
			} else if (select.equals("5")) {
				int res = driveDao.update(dto);
				if (res > 0) {
					System.out.println("수정 완료되었습니다.");
				} else {
					System.out.println("수정 실패하였습니다.");
				}
				break;
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
	}

	public void deleteById() {

		System.out.print("삭제할 운행코드를 입력하세요: ");
		String driverNumber = sc.nextLine();

		int res = driveDao.deleteById(driverNumber);

		if (res > 0) {
			System.out.println("삭제가 완료되었습니다. 감사합니다.");
		} else {
			System.out.println("삭제 실패하였습니다.");
		}
	}
}