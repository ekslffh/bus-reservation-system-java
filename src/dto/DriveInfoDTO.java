package dto;

import java.sql.Date;

public class DriveInfoDTO {
	
		// �ش� �����͸� �������� dto �ʿ�
		// ���� ����: �����ȣ, ��������, ��߽ð�, �����ð�, �������, ����, �ܿ���
		private String driveNum; // �����ȣ
		private Date driveDate; // ��������
		private String departTime; // ��߽ð�
		private String arriveTime; // �����ð�
		private String busGrade; // �������
		private int price; // ����
		private int numOfRemainSeats; // �ܿ���
		public String getDriveNum() {
			return driveNum;
		}
		public void setDriveNum(String driveNum) {
			this.driveNum = driveNum;
		}
		public Date getDriveDate() {
			return driveDate;
		}
		public void setDriveDate(Date driveDate) {
			this.driveDate = driveDate;
		}
		public String getDepartTime() {
			return departTime;
		}
		public void setDepartTime(String departTime) {
			this.departTime = departTime;
		}
		public String getArriveTime() {
			return arriveTime;
		}
		public void setArriveTime(String arriveTime) {
			this.arriveTime = arriveTime;
		}
		public String getBusGrade() {
			return busGrade;
		}
		public void setBusGrade(String busGrade) {
			this.busGrade = busGrade;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public int getNumOfRemainSeats() {
			return numOfRemainSeats;
		}
		public void setNumOfRemainSeats(int numOfRemainSeats) {
			this.numOfRemainSeats = numOfRemainSeats;
		}
		@Override
		public String toString() {
			return "�����ȣ: " + driveNum + ", ��������: " + driveDate + ", ��߽ð�: " + departTime
					+ ", �����ð�: " + arriveTime + ", �������: " + busGrade + ", ����: " + price
					+ ", �ܿ���: " + numOfRemainSeats;
		}
}
