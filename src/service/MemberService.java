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

		System.out.println("로그인을 시작합니다.");
		System.out.print("아이디: ");
		mid = sc.nextLine();
		MemberDTO dto = dao.findById(mid);
		while (true) {
			System.out.print("비밀번호: ");
			mpw = sc.nextLine();
			if (mpw.equals(dto.getM_pw())) {
				System.out.println("로그인 되었습니다.");
				// 현재 로그인 고객저장
				MemberController.member = dto;
				return true;
			} else {
				System.out.println("비밀번호가 일치하지 않습니다.");
				return false;
			}
		}
	}

	public void insert() {
		System.out.println("회원가입을 시작합니다.");
		System.out.print("아이디: ");
		String mId = sc.nextLine();
		System.out.print("비밀번호: ");
		String mPw = sc.nextLine();
		System.out.print("이름: ");
		String mName = sc.nextLine();
		System.out.print("전화번호:");
		String mTelno = sc.nextLine();
		System.out.print("주소: ");
		String mAdd = sc.nextLine();

		MemberDTO dto = new MemberDTO(mId, mPw, mName, mTelno, mAdd);

		if (dao.insert(dto) > 0) {
			System.out.println("회원가입에 성공하였습니다.");
			memberController.run();
		} else {
			System.out.println("회원가입에 실패하였습니다.");
		}
		System.out.println(dto);
	}

	// 아이디 찾기
	public void findByPw() {
		MessageTemplate tc = new MessageTemplate();
		Random random = new Random();
		String mpw = null;
		String mtelno = null;
		String ranNum = Integer.toString(random.nextInt(10000));
		String userCnum = null;

		while (true) {
			System.out.print("비밀번호: ");
			mpw = sc.nextLine();
			System.out.print("전화번호: ");
			mtelno = sc.nextLine();
			MemberDTO dto = dao.findByPw(mpw);
			if (mtelno.equals(dto.getM_telno())) {
				System.out.println("전화번호가 일치합니다.");
				tc.setPhoneNumber(mtelno);
				tc.sendOne("루피의 버스\n비밀번호 찾기 인증번호: " + ranNum);
				System.out.print("인증번호: ");
				userCnum = sc.nextLine();
				if (userCnum.equals(ranNum)) {
					System.out.println("인증번호가 일치합니다.");
					System.out.println("아이디: " + dto.getM_id());
					break;
				} else {
					System.out.println("전화번호 인증에 실패하였습니다.");
					break;
				}
			}
		}
		memberView.authMenu();
	}

	// 비밀번호 찾기
	public void findById() {
		MessageTemplate tc = new MessageTemplate();
		Random random = new Random();
		String mid = null;
		String mtelno = null;
		String ranNum = Integer.toString(random.nextInt(10000));
		String userCnum = null;

		while (true) {
			System.out.print("아이디: ");
			mid = sc.nextLine();
			System.out.print("전화번호: ");
			mtelno = sc.nextLine();
			MemberDTO dto = dao.findById(mid);
			if (mtelno.equals(dto.getM_telno())) {
				System.out.println("전화번호가 일치합니다.");
				tc.setPhoneNumber(mtelno);
				tc.sendOne("[루피의 버스]\n 비밀번호 찾기 인증번호 : " + ranNum + "를 입력해주세요.");
				System.out.print("인증번호: ");
				userCnum = sc.nextLine();
				if (userCnum.equals(ranNum)) {
					System.out.println("인증번호가 일치합니다.");
					System.out.println("비밀번호: " + dto.getM_pw());
					break;
				} else {
					System.out.println("인증에 실패하였습니다.");
					break;
				}
			} else {
				System.out.println("전화번호가 일치하지 않습니다.");
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
		System.out.print("아이디: ");
		mid = sc.nextLine();
		MemberDTO dto = dao.findById(mid);
		dto.setM_id(mid);
		if (dao.getCheck() == true) {
			while (true) {
				System.out.print("비밀번호: ");
				mpw = sc.nextLine();
				if (mpw.equals(dto.getM_pw())) {
					System.out.println("비밀번호가 일치합니다.");
					break;
				} else {
					System.out.println("비밀번호가 일치하지 않습니다.");
				}
			}
		} else {
			update();
		}
		System.out.print("변경할 비밀번호: ");
		mpw = sc.nextLine();
		dto.setM_pw(mpw);

		if (dao.update(dto) > 0) {
			System.out.println("비밀번호가 변경되었습니다.");
			memberView.authMenu();
		} else {
			System.out.println("비밀번호 변경 실패하였습니다.");
		}
	}

	public void deleteById() {
		String mid = null;
		String mpw = null;
		System.out.println("회원탈퇴");
		while (true) {
			System.out.print("아이디: ");
			mid = sc.nextLine();
			System.out.print("비밀번호: ");
			mpw = sc.nextLine();
			MemberDTO dto = dao.findById(mid);
			if (mpw.equals(dto.getM_pw())) {
				System.out.println("비밀번호가 일치합니다.");
				break;
			} else {
				System.out.println("비밀번호가 일치하지 않습니다.");
			}
		}

		System.out.print("정말 삭제하시겠습니까? (Y / N): ");
		char yn = sc.nextLine().charAt(0);

		if (yn == 'y') {
			if (dao.deleteById(mid) > 0) {
				System.out.println("탈퇴성공");
				memberView.authMenu();
			} else {
				System.out.println("탈퇴실패");
				memberView.authMenu();
			}
		} else {
			System.out.println("탈퇴를 취소하였습니다.");
			memberView.authMenu();
		}
	}
}
