package dto;

public class ReservationDTO {

	private String tName;
	private String reservationNum;
	private String memId;
	private String driveNum;
	private String scheduleCode;
	private String busCode;
	//select문에 필요한 필드들 상속받아서 가지고오기 
	
	public String getTName() {
		return tName;
	}
	public void setTName(String tName) {
		this.tName = tName;
	}
	
	public String getReservationNum() {
		return reservationNum;
	}
	public void setReservationNum(String reservationNum) {
		this.reservationNum = reservationNum;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getDriveNum() {
		return driveNum;
	}
	public void setDriveNum(String driveNum) {
		this.driveNum = driveNum;
	}
	public String getScheduleCode() {
		return scheduleCode;
	}
	public void setScheduleCode(String scheduleCode) {
		this.scheduleCode = scheduleCode;
	}
	public String getBusCode() {
		return busCode;
	}
	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}
	
	@Override
	public String toString() {
		return "ReservationDTO [reservationNum=" + reservationNum + ", memId=" + memId + ", driveNum=" + driveNum
				+ ", scheduleCode=" + scheduleCode + ", busCode=" + busCode + "]";
	}
	
	
	
	
	
	
	
	
	

}
