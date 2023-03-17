package view;

public class MemberView {
	
	public void printDefault() {
		StringBuffer sb = new StringBuffer();

		sb.append("------------------------- 루피의 버스 --------------------------\n");
		sb.append("\n");
		sb.append("-------------------------------------------------------------\n");
		System.out.print(sb);
	}
	
	public void printMenu() {
		StringBuffer sb = new StringBuffer();
		
		printDefault();
		sb.append("1.로그인 | 2.회원가입 | 3.ID/PW 찾기 | 4.PW 변경 | 5.회원탈퇴 | 6.종료\n");
		sb.append("-------------------------------------------------------------\n");
		sb.append("루피버스에 오신걸 환영합니다 !\n");
		System.out.print(sb);
		System.out.print("원하시는 번호를 선택해 주세요 'u' : ");
	}
	
	public void printIDorPW() {
		StringBuffer sb = new StringBuffer();
		
		printDefault();
		sb.append("1.ID찾기 | 2.PW찾기\n");
		System.out.print(sb);
		System.out.print("원하시는 번호를 선택해 주세요 'u' : ");
	}
	
}
