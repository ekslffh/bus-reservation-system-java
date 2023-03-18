package service;

import java.util.Random;
import java.util.Scanner;

import controller.MemberController;
import controller.ReservationController;
import controller.TempController;
import dao.MemberDAO;
import dto.MemberDTO;
import view.MemberView;

public class MemberService {
	private static Scanner sc = new Scanner(System.in);
	private static MemberDAO dao = new MemberDAO();
	private static MemberView memberView = new MemberView();
	private static MemberController memberController = new MemberController();
	private static ReservationController reservationController = new ReservationController();
	private static String mtelno = null;

	public void login() {
		String mid = null;
		String mpw = null;
		
		System.out.println("�α����� �����մϴ�.");
		System.out.print("���̵� : ");
		mid = sc.nextLine();
		MemberDTO dto = dao.findById(mid);
		dto.setM_id(mid);
		while(true){
			System.out.print("��й�ȣ : ");
			mpw = sc.nextLine();
			if (mpw.equals(dto.getM_pw())) {
				System.out.println("�α��� �Ǿ����ϴ�.");
				reservationController.reservationMenu();
			} else {
				System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}
		}	
	}
	
	public static void insert() {
		System.out.println("ȸ�������� �����մϴ�.");
		System.out.print("���̵� : ");
		String mId = sc.nextLine();
		System.out.print("��й�ȣ : ");
		String mPw = sc.nextLine();
		System.out.print("�̸� : ");
		String mName = sc.nextLine();
		System.out.print("��ȭ��ȣ :");
		String mTelno = sc.nextLine();
		System.out.print("�ּ� : ");
		String mAdd = sc.nextLine();

		MemberDTO dto = new MemberDTO(mId, mPw, mName, mTelno, mAdd);

		if (dao.insert(dto) > 0) {
			System.out.println("ȸ�����Կ� �����Ͽ����ϴ�.");
			memberController.run();
		} else {
			System.out.println("ȸ�����Կ� �����Ͽ����ϴ�.");
		}
		System.out.println(dto);
	}

	// ���̵� ã��
	public void findByPw() {
		TempController tc = new TempController();
		Random random = new Random();
		String mpw = null;
		String mtelno = null;
		String ranNum = Integer.toString(random.nextInt(10000));
		String userCnum = null;

		while (true) {
			System.out.print("��й�ȣ : ");
			mpw = sc.nextLine();
			System.out.print("��ȭ��ȣ : ");
			mtelno = sc.nextLine();
			MemberDTO dto = dao.findByPw(mpw);
			if (mtelno.equals(dto.getM_telno())) {
				System.out.println("��ȭ��ȣ�� ��ġ�մϴ�.");
				setTelno(mtelno);
				tc.sendOne("������ ����\n��й�ȣ ã�� ������ȣ : " + ranNum);
				System.out.print("������ȣ : ");
				userCnum = sc.nextLine();
				if (userCnum.equals(ranNum)) {
					System.out.println("������ȣ�� ��ġ�մϴ�.");
					System.out.println("���̵� : " + dto.getM_id());
					break;
				} else {
					System.out.println("��ȭ��ȣ�� ��ġ���� �ʽ��ϴ�.");
					continue;
				}
			}
		}
		memberView.printMenu();
	}

	// ��й�ȣ ã��
	public void findById() {
		TempController tc = new TempController();
		Random random = new Random();
		String mid = null;
		String mtelno = null;
		String ranNum =  Integer.toString(random.nextInt(10000));
		String userCnum = null;
		
		while(true){
			System.out.print("���̵� : ");
			mid = sc.nextLine();
			System.out.print("��ȭ��ȣ : ");
			mtelno = sc.nextLine();
			MemberDTO dto = dao.findById(mid);
			if (mtelno.equals(dto.getM_telno())) {
				System.out.println("��ȭ��ȣ�� ��ġ�մϴ�.");
				setTelno(mtelno);
				tc.sendOne("[�������� ������]\n��й�ȣ ã�� ������ȣ : " + ranNum + "�� �Է��� �ּ���(������)");
				System.out.print("������ȣ : ");
				userCnum = sc.nextLine();
				if(userCnum.equals(ranNum)) {
				System.out.println("������ȣ�� ��ġ�մϴ�.");
				System.out.println("��й�ȣ : " + dto.getM_pw());
				break;
				}else {
					System.out.println("������ȣ�� ��ġ���� �ʽ��ϴ�.");
					continue;
				}
			} else {
				System.out.println("��ȭ��ȣ�� ��ġ���� �ʽ��ϴ�.");
				continue;
			}
		}
		
		memberView.printMenu();
	}
	
	public void setTelno(String mtelno) {
		this.mtelno = mtelno;
	}
	
	public String getTelno() {
		return mtelno;
	}

	public static void update() {
		String mid = null;
		String mpw = null;
		System.out.print("���̵� : ");
		mid = sc.nextLine();
		MemberDTO dto = dao.findById(mid);
		dto.setM_id(mid);
		if(dao.getCheck() == true) {
		while(true){
			System.out.print("��й�ȣ : ");
			mpw = sc.nextLine();
			if (mpw.equals(dto.getM_pw())) {
				System.out.println("��й�ȣ�� ��ġ�մϴ�.");
				break;
			} else {
				System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}
		}	
	}else {
		update();
	}
		System.out.print("������ ��й�ȣ : ");
		mpw = sc.nextLine();
		dto.setM_pw(mpw);

		if(dao.update(dto) > 0) {
			System.out.println("��й�ȣ�� ����Ǿ����ϴ�.");
			memberView.printMenu();
		}else {
			System.out.println("��й�ȣ ������ �����Ͽ����ϴ�.");
		}
	}
	
	public static void deleteById() {
		String mid = null;
		String mpw = null;
		System.out.println("ȸ��Ż��");
		while(true){
			System.out.print("���̵� : ");
			mid = sc.nextLine();
			System.out.print("��й�ȣ : ");
			mpw = sc.nextLine();
			MemberDTO dto = dao.findById(mid);
			if (mpw.equals(dto.getM_pw())) {
				System.out.println("��й�ȣ�� ��ġ�մϴ�.");
				break;
			} else {
				System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}
		}

		System.out.print("���� �����Ͻðڽ��ϱ�? (Y / N) : ");
		char yn = sc.nextLine().charAt(0);

		if (yn == 'y') {
			if (dao.deleteById(mid) > 0) {
				System.out.println("Ż���ϼ̽��ϴ�.");
				memberView.printMenu();
			} else {
				System.out.println("Ż�����.");
				memberView.printMenu();
			}
		} else {
			System.out.println("Ż�� ����ϼ̽��ϴ�.");
			memberView.printMenu();
		}
	}
}
