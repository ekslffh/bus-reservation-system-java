package view;

public class MemberView {
	
	public void printDefault() {
		StringBuffer sb = new StringBuffer();

		sb.append("------------------------- ������ ���� --------------------------\n");
		sb.append("\n");
		sb.append("-------------------------------------------------------------\n");
		System.out.print(sb);
	}
	
	public void printMenu() {
		StringBuffer sb = new StringBuffer();
		
		printDefault();
		sb.append("1.�α��� | 2.ȸ������ | 3.ID/PW ã�� | 4.PW ���� | 5.ȸ��Ż�� | 6.����\n");
		sb.append("-------------------------------------------------------------\n");
		sb.append("���ǹ����� ���Ű� ȯ���մϴ� !\n");
		System.out.print(sb);
		System.out.print("���Ͻô� ��ȣ�� ������ �ּ��� 'u' : ");
	}
	
	public void printIDorPW() {
		StringBuffer sb = new StringBuffer();
		
		printDefault();
		sb.append("1.IDã�� | 2.PWã��\n");
		System.out.print(sb);
		System.out.print("���Ͻô� ��ȣ�� ������ �ּ��� 'u' : ");
	}
	
}
