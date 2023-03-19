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

	// ���� ����
	public void reservate() {
		boolean isPayment = false;
		while (true) {
			// �����Ϸ������� ����ȭ������ �̵�
			if (isPayment)
				break;
			// �͹̳� ���� �����ֱ�
			int num = 1;
			List<TerminalDTO> terminalDtos = terminalDAO.findAll();
			for (TerminalDTO dto : terminalDtos) {
				System.out.println(num + ") " + dto.getName());
				num++;
			}
//			TablePrinter<TerminalDTO> terminalTable = new TablePrinter<>(terminalDtos);
//			terminalTable.printTable();
			// �����, ������, ���³� ���� + �������
			System.out.print("��߿�: ");
			String department = sc.nextLine();
			System.out.print("������: ");
			String arrive = sc.nextLine();
			System.out.print("���³�(YYYY-MM-dd): ");
			String date = sc.nextLine();

			// ����ǥ ��ȸ
			List<DriveInfoDTO> driveInfoDtos = driveDao.getDriveInfos(department, arrive, date);
//			for (DriveInfoDTO dto : driveInfoDtos) {
//				System.out.println(dto);
//			}
			TablePrinter<DriveInfoDTO> driveInfos = new TablePrinter<>(driveInfoDtos);
			driveInfos.printTable();
			System.out.print("�����ȣ: ");
			String driveNum = sc.nextLine();

			// �¼�����
			// �����ȣ�� ��¥�� ������ �¼��������̺��� �¼������� �����;� �Ѵ�.
			// �¼������� ������ ������ �Ǿ��ִ� �¼��� �Ǿ����� ���� �¼��� �����ؼ� ǥ���ؾ� �Ѵ�.
			List<String> seatInfos = seatDao.findAllByDriveAndDate(date, driveNum);
			for (String seatNum : seatInfos) {
				System.out.println(seatNum);
			}
			// ���ʿ� ������ �¼������� ���¼��� �ƴ� �¼��� �����ؼ� ����ؾ���
			List<String> selectSeatNum = new ArrayList<>();
			boolean completeSelct = false;
			while (true) {
				System.out.print("�����¼�: ");
				for (String seatNum : selectSeatNum) {
					System.out.print(seatNum + " ");
				}
				System.out.println();
				System.out.print("�¼���ȣ: ");
				selectSeatNum.add(sc.nextLine());
				System.out.print("1.�߰�����, 2.���ÿϷ�, 3.�¼���� 4.�������: ");
				String input = sc.nextLine();
				if (input.equals("1")) { // �¼��߰�����
					continue;
				} else if (input.equals("2")) { // ���ÿϷ�
					System.out.println("�¼����ÿϷ�");
					completeSelct = true;
					break;
				} else if (input.equals("3")) { // �¼��ϳ����
					// ���� �ֱٿ� �߰��� ���� ����
					selectSeatNum.remove(terminalDtos.size() - 1);
				} else if (input.equals("4")) { // �������
					// �ٽ� �͹̳� ���� ȭ������ �̵�
					break;
				} else {
					System.out.println("�߸��� �Է��Դϴ�.");
				}
			}
			// �¼����� �Ϸ��ΰ��
			if (completeSelct) {
				// ##��������##

				// ���ϸ��� �ܾ� �����ְ� ���ϸ��� ������� �����
				// �������� -> �����Ϸ� (�¼��������̺� �ش� ���� �����ؾ� ��!)
				// �� ����ȭ������ ���ư���

				// ������ �������� ��߿�, ������, �¼�, ���� ���� ���� ���
				int price = driveInfoDtos.stream().filter(d -> d.getDriveNum().equals(driveNum)).findAny().get()
						.getPrice();
				// ��߽ð�, �����ð� �߰�
				String payInfo = department + " ==> " + arrive + ", �¼�: " + selectSeatNum.toString()
						+ ", �Ѱ����ݾ�: " + (price * selectSeatNum.size());
				while (true) {
					System.out.println(payInfo);
					// �����ϱ� ������
					System.out.println("1.�����ϱ� 2.�������");
					System.out.print("�Է�: ");
					String input = sc.nextLine();
					// ����
					if (input.equals("1")) {
						// ���ϸ��� ���� �߰�

						// �¼����̺� �߰�
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
							System.out.println("�¼�������� �����Ͽ����ϴ� �˻�ȭ������ �̵��մϴ�.");
							break;
						}

						// �������̺� ����
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
							System.out.println("���Ż��� �����Ͽ����ϴ� �˻�ȭ������ �̵��մϴ�.");
							break;
						}

						System.out.println("������ ���������� �Ϸ�Ǿ����ϴ�. ����ȭ������ �̵��մϴ�.");
						isPayment = true;
					} else if (input.equals("2")) {
						System.out.println("������ ��ҵǾ����ϴ�. �˻�ȭ������ �̵��մϴ�.");
					} else {
						System.out.println("�߸� �Է��ϼ̽��ϴ�.");
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
			System.out.println("1.���� ����ϱ�, 2.���θ޴��� ���ư���");
			String select = sc.nextLine();
			// ����ϱ�
			if (select.equals("1")) {
				// ����ǥ ����ϱ�
				System.out.print("���Ź�ȣ �Է�: ");
				String number = sc.nextLine();
				if (reservationDao.deleteById(number) > 0) {
					System.out.println("������ҵǾ����ϴ�.");
				}
			} else if (select.equals("2")) { // ���θ޴��� ���ư���
				System.out.println("���θ޴��� ���ư��ϴ�.");
				break;
			} else {
				System.out.println("�߸��� �Է��Դϴ�.");
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
			System.out.println("1.���� �����ϱ�, 2.���θ޴��� ���ư���");
			String select = sc.nextLine();
			// ����ϱ�
			if (select.equals("1")) {
				// ����ǥ ����
				System.out.print("���Ź�ȣ �Է�: ");
				String number = sc.nextLine();
				if (reservationDao.deleteById(number) > 0) {
					System.out.println("������ҵǾ����ϴ�.");
				}
			} else if (select.equals("2")) { // ���θ޴��� ���ư���
				System.out.println("���θ޴��� ���ư��ϴ�.");
				break;
			} else {
				System.out.println("�߸��� �Է��Դϴ�.");
			}
		}
	}
}
