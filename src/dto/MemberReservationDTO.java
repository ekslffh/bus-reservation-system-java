package dto;

public class MemberReservationDTO {
	private String reservationDate;
	private String dateTime;
	private String department;
	private String arrive;
	private String seatCode;
	private int driveTime;
	private String busGrade;
	private String reservationNum;
	
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	public String getBusGrade() {
		return busGrade;
	}
	public void setBusGrade(String busGrade) {
		this.busGrade = busGrade;
	}
	public String getReservationNum() {
		return reservationNum;
	}
	public void setReservationNum(String reservationNum) {
		this.reservationNum = reservationNum;
	}
	public String getrDate() {
		return reservationDate;
	}
	public void setrDate(String rDate) {
		this.reservationDate = rDate;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getArrive() {
		return arrive;
	}
	public void setArrive(String arrive) {
		this.arrive = arrive;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getSeatCode() {
		return seatCode;
	}
	public void setSeatCode(String seatCode) {
		this.seatCode = seatCode;
	}
	public int getDriveTime() {
		return driveTime;
	}
	public void setDriveTime(int driveTime) {
		this.driveTime = driveTime;
	}
	@Override
	public String toString() {
		return "MemberReservationDTO [rDate=" + reservationDate + ", dateTime=" + dateTime + ", department=" + department
				+ ", arrive=" + arrive + ", seatCode=" + seatCode + ", driveTime=" + driveTime + "]";
	}
	
}
