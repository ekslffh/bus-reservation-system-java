package service;

import java.util.Random;
import java.util.Scanner;

import controller.MemberController;
import controller.ReservationController;
import dao.MemberDAO;
import dto.MemberDTO;
import util.MessageTemplate;
import view.MemberView;

public class MemberService {
	private Scanner sc = new Scanner(System.in);
	private MemberDAO dao = new MemberDAO();
	private MemberView memberView = new MemberView();
	private MemberController memberController = new MemberController();
	private ReservationController reservationController = new ReservationController();
	private String mtelno = null;

	public boolean login() {
		String mid = null;
		String mpw = null;

		System.out.println("�α����� �����մϴ�.");
		System.out.print("���̵�: ");
		mid = sc.nextLine();
		MemberDTO dto = dao.findById(mid);
		while (true) {
			System.out.print("��й�ȣ: ");
			mpw = sc.nextLine();
			if (mpw.equals(dto.getM_pw())) {
				System.out.println("�α��� �Ǿ����ϴ�.");
				// ���� �α��� ������
				MemberController.member = dto;
				return true;
			} else {
				System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				return false;
			}
		}
	}

	public void insert() {
		System.out.println("ȸ�������� �����մϴ�.");
		System.out.print("���̵�: ");
		String mId = sc.nextLine();
		System.out.print("��й�ȣ: ");
		String mPw = sc.nextLine();
		System.out.print("�̸�: ");
		String mName = sc.nextLine();
		System.out.print("��ȭ��ȣ:");
		String mTelno = sc.nextLine();
		System.out.print("�ּ�: ");
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
		MessageTemplate tc = new MessageTemplate();
		Random random = new Random();
		String mpw = null;
		String mtelno = null;
		String ranNum = Integer.toString(random.nextInt(10000));
		String userCnum = null;

		while (true) {
			System.out.print("��й�ȣ: ");
			mpw = sc.nextLine();
			System.out.print("��ȭ��ȣ: ");
			mtelno = sc.nextLine();
			MemberDTO dto = dao.findByPw(mpw);
			if (mtelno.equals(dto.getM_telno())) {
				System.out.println("��ȭ��ȣ�� ��ġ�մϴ�.");
				tc.setPhoneNumber(mtelno);
				tc.sendOne("������ ����\n��й�ȣ ã�� ������ȣ: " + ranNum);
				System.out.print("������ȣ: ");
				userCnum = sc.nextLine();
				if (userCnum.equals(ranNum)) {
					System.out.println("������ȣ�� ��ġ�մϴ�.");
					System.out.println("���̵�: " + dto.getM_id());
					break;
				} else {
					System.out.println("��ȭ��ȣ ������ �����Ͽ����ϴ�.");
					break;
				}
			}
		}
		memberView.authMenu();
	}

	// ��й�ȣ ã��
	public void findById() {
		MessageTemplate tc = new MessageTemplate();
		Random random = new Random();
		String mid = null;
		String mtelno = null;
		String ranNum = Integer.toString(random.nextInt(10000));
		String userCnum = null;

		while (true) {
			System.out.print("���̵�: ");
			mid = sc.nextLine();
			System.out.print("��ȭ��ȣ: ");
			mtelno = sc.nextLine();
			MemberDTO dto = dao.findById(mid);
			if (mtelno.equals(dto.getM_telno())) {
				System.out.println("��ȭ��ȣ�� ��ġ�մϴ�.");
				tc.setPhoneNumber(mtelno);
				tc.sendOne("[������ ����]\n ��й�ȣ ã�� ������ȣ : " + ranNum + "�� �Է����ּ���.");
				System.out.print("������ȣ: ");
				userCnum = sc.nextLine();
				if (userCnum.equals(ranNum)) {
					System.out.println("������ȣ�� ��ġ�մϴ�.");
					System.out.println("��й�ȣ: " + dto.getM_pw());
					break;
				} else {
					System.out.println("������ �����Ͽ����ϴ�.");
					break;
				}
			} else {
				System.out.println("��ȭ��ȣ�� ��ġ���� �ʽ��ϴ�.");
				break;
			}
		}
		memberView.authMenu();
	}

	public void setTelno(String mtelno) {
		this.mtelno = mtelno;
	}

	public String getTelno() {
		return mtelno;
	}

	public void update() {
		String mid = null;
		String mpw = null;
		System.out.print("���̵�: ");
		mid = sc.nextLine();
		MemberDTO dto = dao.findById(mid);
		dto.setM_id(mid);
		if (dao.getCheck() == true) {
			while (true) {
				System.out.print("��й�ȣ: ");
				mpw = sc.nextLine();
				if (mpw.equals(dto.getM_pw())) {
					System.out.println("��й�ȣ�� ��ġ�մϴ�.");
					break;
				} else {
					System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				}
			}
		} else {
			update();
		}
		System.out.print("������ ��й�ȣ: ");
		mpw = sc.nextLine();
		dto.setM_pw(mpw);

		if (dao.update(dto) > 0) {
			System.out.println("��й�ȣ�� ����Ǿ����ϴ�.");
			memberView.authMenu();
		} else {
			System.out.println("��й�ȣ ���� �����Ͽ����ϴ�.");
		}
	}

	public void deleteById() {
		String mid = null;
		String mpw = null;
		System.out.println("ȸ��Ż��");
		while (true) {
			System.out.print("���̵�: ");
			mid = sc.nextLine();
			System.out.print("��й�ȣ: ");
			mpw = sc.nextLine();
			MemberDTO dto = dao.findById(mid);
			if (mpw.equals(dto.getM_pw())) {
				System.out.println("��й�ȣ�� ��ġ�մϴ�.");
				break;
			} else {
				System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}
		}

		System.out.print("���� �����Ͻðڽ��ϱ�? (Y / N): ");
		char yn = sc.nextLine().charAt(0);

		if (yn == 'y') {
			if (dao.deleteById(mid) > 0) {
				System.out.println("Ż�𼺰�");
				memberView.authMenu();
			} else {
				System.out.println("Ż�����");
				memberView.authMenu();
			}
		} else {
			System.out.println("Ż�� ����Ͽ����ϴ�.");
			memberView.authMenu();
		}
	}
}
