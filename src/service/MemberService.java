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
		
		System.out.println("로그인을 시작합니다.");
		System.out.print("아이디 : ");
		mid = sc.nextLine();
		MemberDTO dto = dao.findById(mid);
		dto.setM_id(mid);
		while(true){
			System.out.print("비밀번호 : ");
			mpw = sc.nextLine();
			if (mpw.equals(dto.getM_pw())) {
				System.out.println("로그인 되었습니다.");
				reservationController.reservationMenu();
			} else {
				System.out.println("비밀번호가 일치하지 않습니다.");
			}
		}	
	}
	
	public static void insert() {
		System.out.println("회원가입을 시작합니다.");
		System.out.print("아이디 : ");
		String mId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String mPw = sc.nextLine();
		System.out.print("이름 : ");
		String mName = sc.nextLine();
		System.out.print("전화번호 :");
		String mTelno = sc.nextLine();
		System.out.print("주소 : ");
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
		TempController tc = new TempController();
		Random random = new Random();
		String mpw = null;
		String mtelno = null;
		String ranNum = Integer.toString(random.nextInt(10000));
		String userCnum = null;

		while (true) {
			System.out.print("비밀번호 : ");
			mpw = sc.nextLine();
			System.out.print("전화번호 : ");
			mtelno = sc.nextLine();
			MemberDTO dto = dao.findByPw(mpw);
			if (mtelno.equals(dto.getM_telno())) {
				System.out.println("전화번호가 일치합니다.");
				setTelno(mtelno);
				tc.sendOne("루피의 버스\n비밀번호 찾기 인증번호 : " + ranNum);
				System.out.print("인증번호 : ");
				userCnum = sc.nextLine();
				if (userCnum.equals(ranNum)) {
					System.out.println("인증번호가 일치합니다.");
					System.out.println("아이디 : " + dto.getM_id());
					break;
				} else {
					System.out.println("전화번호가 일치하지 않습니다.");
					continue;
				}
			}
		}
		memberView.printMenu();
	}

	// 비밀번호 찾기
	public void findById() {
		TempController tc = new TempController();
		Random random = new Random();
		String mid = null;
		String mtelno = null;
		String ranNum =  Integer.toString(random.nextInt(10000));
		String userCnum = null;
		
		while(true){
			System.out.print("아이디 : ");
			mid = sc.nextLine();
			System.out.print("전화번호 : ");
			mtelno = sc.nextLine();
			MemberDTO dto = dao.findById(mid);
			if (mtelno.equals(dto.getM_telno())) {
				System.out.println("전화번호가 일치합니다.");
				setTelno(mtelno);
				tc.sendOne("[♡루피의 버스♡]\n비밀번호 찾기 인증번호 : " + ranNum + "를 입력해 주세뤂(˘ω˘)");
				System.out.print("인증번호 : ");
				userCnum = sc.nextLine();
				if(userCnum.equals(ranNum)) {
				System.out.println("인증번호가 일치합니다.");
				System.out.println("비밀번호 : " + dto.getM_pw());
				break;
				}else {
					System.out.println("인증번호가 일치하지 않습니다.");
					continue;
				}
			} else {
				System.out.println("전화번호가 일치하지 않습니다.");
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
		System.out.print("아이디 : ");
		mid = sc.nextLine();
		MemberDTO dto = dao.findById(mid);
		dto.setM_id(mid);
		if(dao.getCheck() == true) {
		while(true){
			System.out.print("비밀번호 : ");
			mpw = sc.nextLine();
			if (mpw.equals(dto.getM_pw())) {
				System.out.println("비밀번호가 일치합니다.");
				break;
			} else {
				System.out.println("비밀번호가 일치하지 않습니다.");
			}
		}	
	}else {
		update();
	}
		System.out.print("변경할 비밀번호 : ");
		mpw = sc.nextLine();
		dto.setM_pw(mpw);

		if(dao.update(dto) > 0) {
			System.out.println("비밀번호가 변경되었습니다.");
			memberView.printMenu();
		}else {
			System.out.println("비밀번호 변경을 실패하였습니다.");
		}
	}
	
	public static void deleteById() {
		String mid = null;
		String mpw = null;
		System.out.println("회월탈퇴");
		while(true){
			System.out.print("아이디 : ");
			mid = sc.nextLine();
			System.out.print("비밀번호 : ");
			mpw = sc.nextLine();
			MemberDTO dto = dao.findById(mid);
			if (mpw.equals(dto.getM_pw())) {
				System.out.println("비밀번호가 일치합니다.");
				break;
			} else {
				System.out.println("비밀번호가 일치하지 않습니다.");
			}
		}

		System.out.print("정말 삭제하시겠습니까? (Y / N) : ");
		char yn = sc.nextLine().charAt(0);

		if (yn == 'y') {
			if (dao.deleteById(mid) > 0) {
				System.out.println("탈퇴하셨습니다.");
				memberView.printMenu();
			} else {
				System.out.println("탈퇴실패.");
				memberView.printMenu();
			}
		} else {
			System.out.println("탈퇴를 취소하셨습니다.");
			memberView.printMenu();
		}
	}
}
