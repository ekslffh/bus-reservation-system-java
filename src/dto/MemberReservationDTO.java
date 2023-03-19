package dto;

public class MemberReservationDTO {
	private String dateTime;
	private String department;
	private String arrive;
	private String seatCode;
	private int driveTime;
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
		return "MemberReservationDTO [department=" + department + ", arrive=" + arrive + ", dateTime=" + dateTime
				+ ", seatCode=" + seatCode + ", driveTime=" + driveTime + "]";
	}
}
