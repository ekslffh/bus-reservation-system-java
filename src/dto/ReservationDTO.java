package dto;

import java.sql.Date;

public class ReservationDTO {

	private String tName;
	private String reservationNum;
	private String memId;
	private String driveNum;
	private String scheduleCode;
	private String busCode;
	//select���� �ʿ��� �ʵ�� ��ӹ޾Ƽ� ������� 
	
	public String getTName() {
		return tName;
	}
	public void setTName(String tName) {
		this.tName = tName;
	}
	
	public String getReservationNum() {
		return reservationNum;
	private String num;
	private String memberId;
	private String seatCode;
	private String driveNum;
	private Date reservationDate;
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getSeatCode() {
		return seatCode;
	}
	public void setSeatCode(String seatCode) {
		this.seatCode = seatCode;
	}
	public String getDriveNum() {
		return driveNum;
	}
	public void setDriveNum(String driveNum) {
		this.driveNum = driveNum;
	}
	public Date getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	
	@Override
	public String toString() {
		return "ReservationDTO [num=" + num + ", memberId=" + memberId + ", seatCode=" + seatCode + ", driveNum="
				+ driveNum + ", reservationDate=" + reservationDate + "]";
	}	

}
