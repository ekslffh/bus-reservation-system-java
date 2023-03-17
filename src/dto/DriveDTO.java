package dto;

public class DriveDTO {
	
	private String driveNumber;
	private String busCode;
	private String scheduleCode;
	
	
	public String getDriveNumber() {
		return driveNumber;
	}
	public void setDriveNumber(String driveNumber) {
		this.driveNumber = driveNumber;
	}
	public String getBusCode() {
		return busCode;
	}
	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}
	public String getScheduleCode() {
		return scheduleCode;
	}
	public void setScheduleCode(String scheduleCode) {
		this.scheduleCode = scheduleCode;
	}
	
	
	@Override
	public String toString() {
		return "DriveDTO [driveNumber=" + driveNumber + ", busCode=" + busCode + ", scheduleCode=" + scheduleCode + "]";
	}
	
	
	
	

}
