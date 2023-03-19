package dto;

import java.sql.Date;

public class DriveInfoDTO {
	
		// 해당 데이터를 담을만한 dto 필요
		// 담을 정보: 운행번호, 운행일자, 출발시간, 도착시간, 버스등급, 가격, 잔여석
		private String driveNum; // 운행번호
		private Date driveDate; // 운행일자
		private String departTime; // 출발시간
		private String arriveTime; // 도착시간
		private String busGrade; // 버스등급
		private int price; // 가격
		private int numOfRemainSeats; // 잔여석
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
			return "운행번호: " + driveNum + ", 운행일자: " + driveDate + ", 출발시간: " + departTime
					+ ", 도착시간: " + arriveTime + ", 버스등급: " + busGrade + ", 가격: " + price
					+ ", 잔여석: " + numOfRemainSeats;
		}
}
