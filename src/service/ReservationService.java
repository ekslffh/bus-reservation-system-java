package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import controller.MemberController;
import dao.DriveDAO;
import dao.ReservationDAO;
import dao.SeatDAO;
import dao.TerminalDAO;
import dto.DriveInfoDTO;
import dto.MemberReservationDTO;
import dto.ReservationDTO;
import dto.SeatDTO;
import dto.TerminalDTO;
import util.TablePrinter;
import view.MemberView;

public class ReservationService {
	// view
	private static MemberView view = new MemberView();
	// dto
	private static ReservationDTO reservationDto = new ReservationDTO();
	// dao
	private static ReservationDAO reservationDao = new ReservationDAO();
	private static TerminalDAO terminalDAO = new TerminalDAO();
	private static DriveDAO driveDao = new DriveDAO();
	private static SeatDAO seatDao = new SeatDAO();

	private static Scanner sc = new Scanner(System.in);
	private static Scanner s = new Scanner(System.in);

	public String depart;
	public String arrive;
	public String date;
	public String row;
	public String column;
	public static String departTerminal;
	public static String arriveTerminal;

	// 예약 로직
	public void reservate() {
		boolean isPayment = false;
		while (true) {
			// 결제완료했으면 메인화면으로 이동
			if (isPayment)
				break;
			// 터미널 정보 보여주기
			int num = 1;
			List<TerminalDTO> terminalDtos = terminalDAO.findAll();
			for (TerminalDTO dto : terminalDtos) {
				System.out.println(num + ") " + dto.getName());
				num++;
			}
//			TablePrinter<TerminalDTO> terminalTable = new TablePrinter<>(terminalDtos);
//			terminalTable.printTable();
			// 출발지, 목적지, 가는날 선택 + 몇시이후
			System.out.print("출발역: ");
			String department = sc.nextLine();
			System.out.print("도착역: ");
			String arrive = sc.nextLine();
			System.out.print("가는날(YYYY-MM-dd): ");
			String date = sc.nextLine();

			// 운행표 조회
			List<DriveInfoDTO> driveInfoDtos = driveDao.getDriveInfos(department, arrive, date);
//			for (DriveInfoDTO dto : driveInfoDtos) {
//				System.out.println(dto);
//			}
			TablePrinter<DriveInfoDTO> driveInfos = new TablePrinter<>(driveInfoDtos);
			driveInfos.printTable();
			System.out.print("운행번호: ");
			String driveNum = sc.nextLine();

			// 좌석선택
			// 운행번호와 날짜를 가지고 좌석예약테이블에서 좌석정보를 가져와야 한다.
			// 좌석정보를 가지고 예약이 되어있는 좌석과 되어있지 않은 좌석을 구분해서 표시해야 한다.
			List<String> seatInfos = seatDao.findAllByDriveAndDate(date, driveNum);
			for (String seatNum : seatInfos) {
				System.out.println(seatNum);
			}
			// 이쪽에 가져온 좌석정보로 빈좌석과 아닌 좌석을 구분해서 출력해야함
			List<String> selectSeatNum = new ArrayList<>();
			boolean completeSelct = false;
			while (true) {
				System.out.print("예약좌석: ");
				for (String seatNum : selectSeatNum) {
					System.out.print(seatNum + " ");
				}
				System.out.println();
				System.out.print("좌석번호: ");
				selectSeatNum.add(sc.nextLine());
				System.out.print("1.추가선택, 2.선택완료, 3.좌석취소 4.예약취소: ");
				String input = sc.nextLine();
				if (input.equals("1")) { // 좌석추가선택
					continue;
				} else if (input.equals("2")) { // 선택완료
					System.out.println("좌석선택완료");
					completeSelct = true;
					break;
				} else if (input.equals("3")) { // 좌석하나취소
					// 가장 최근에 추가한 내용 삭제
					selectSeatNum.remove(terminalDtos.size() - 1);
				} else if (input.equals("4")) { // 예약취소
					// 다시 터미널 고르는 화면으로 이동
					break;
				} else {
					System.out.println("잘못된 입력입니다.");
				}
			}
			// 좌석선택 완료인경우
			if (completeSelct) {
				// ##결제로직##

				// 마일리지 잔액 보여주고 마일리지 사용할지 물어보고
				// 결제진행 -> 결제완료 (좌석예약테이블에 해당 내용 저장해야 함!)
				// 고객 시작화면으로 돌아가기

				// 승차권 형식으로 출발역, 도착역, 좌석, 가격 등의 정보 출력
				int price = driveInfoDtos.stream().filter(d -> d.getDriveNum().equals(driveNum)).findAny().get()
						.getPrice();
				// 출발시간, 도착시간 추가
				String payInfo = department + " ==> " + arrive + ", 좌석: " + selectSeatNum.toString()
						+ ", 총결제금액: " + (price * selectSeatNum.size());
				while (true) {
					System.out.println(payInfo);
					// 결제하기 누르고
					System.out.println("1.결제하기 2.결제취소");
					System.out.print("입력: ");
					String input = sc.nextLine();
					// 결제
					if (input.equals("1")) {
						// 마일리지 로직 추가

						// 좌석테이블도 추가
						List<SeatDTO> seatDtos = new ArrayList<>();
						for (String seatNum : selectSeatNum) {
							SeatDTO dto = new SeatDTO();
							dto.setSeatCode(seatNum);
							dto.setDriveNumber(driveNum);
							dto.setReservationDate(date);
							seatDtos.add(dto);
						}
						TablePrinter<SeatDTO> seatTable = new TablePrinter<>(seatDtos);
						seatTable.printTable();
						if (seatDao.insertAll(seatDtos) < 0) {
							System.out.println("좌석예약생성 실패하였습니다 검색화면으로 이동합니다.");
							break;
						}

						// 예약테이블에 저장
						List<ReservationDTO> reservationDtos = new ArrayList<>();
						for (String seatNum : selectSeatNum) {
							ReservationDTO reservationDto = new ReservationDTO();
							String rNum = date + "/" + MemberController.member.getM_id()
									+ UUID.randomUUID().toString().substring(0, 4);
							reservationDto.setNum(rNum);
							reservationDto.setMemberId(MemberController.member.getM_id());
							reservationDto.setSeatCode(seatNum);
							reservationDto.setDriveNum(driveNum);
							reservationDto.setReservationDate(date);
							reservationDtos.add(reservationDto);
						}
						if (reservationDao.insertAll(reservationDtos) < 0) {
							System.out.println("예매생성 실패하였습니다 검색화면으로 이동합니다.");
							break;
						}

						System.out.println("결제가 정상적으로 완료되었습니다. 메인화면으로 이동합니다.");
						isPayment = true;
					} else if (input.equals("2")) {
						System.out.println("결제가 취소되었습니다. 검색화면으로 이동합니다.");
					} else {
						System.out.println("잘못 입력하셨습니다.");
						continue;
					}
					break;
				}
			}
		}
	}

	public void checkMyReservation(String id) {
		while (true) {
			List<MemberReservationDTO> reservationDtos = reservationDao.findByMemberId(id);
			System.out.println(reservationDtos);
//			for (ReservationDTO dto : reservationDtos) {
//				System.out.println(dto);
//			}
			TablePrinter<MemberReservationDTO> tablePrinter = new TablePrinter<>(reservationDtos);
			tablePrinter.printTable();
			System.out.println("1.예매 취소하기, 2.메인메뉴로 돌아가기");
			String select = sc.nextLine();
			// 취소하기
			if (select.equals("1")) {
				// 예매표 취소하기
				System.out.print("예매번호 입력: ");
				String number = sc.nextLine();
				if (reservationDao.deleteById(number) > 0) {
					System.out.println("예매취소되었습니다.");
				}
			} else if (select.equals("2")) { // 메인메뉴로 돌아가기
				System.out.println("메인메뉴로 돌아갑니다.");
				break;
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
	}

	public void checkAllReservation() {
		while (true) {
			List<ReservationDTO> reservationDtos = reservationDao.findAll();
//			for (ReservationDTO dto : reservationDtos) {
//				System.out.println(dto);
//			}
			TablePrinter<ReservationDTO> tablePrinter = new TablePrinter<>(reservationDtos);
			tablePrinter.printTable();
			System.out.println("1.예매 삭제하기, 2.메인메뉴로 돌아가기");
			String select = sc.nextLine();
			// 취소하기
			if (select.equals("1")) {
				// 예매표 삭제
				System.out.print("예매번호 입력: ");
				String number = sc.nextLine();
				if (reservationDao.deleteById(number) > 0) {
					System.out.println("예매취소되었습니다.");
				}
			} else if (select.equals("2")) { // 메인메뉴로 돌아가기
				System.out.println("메인메뉴로 돌아갑니다.");
				break;
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
	}
}
