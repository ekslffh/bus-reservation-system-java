package dto;

public class DriveDTO {
	
	private String driveNumber;
	private String departmentTime;
	private String arriveTime;
	private String busCode;
	private String routeCode;
	
	public String getDriveNumber() {
		return driveNumber;
	}
	public void setDriveNumber(String driveNumber) {
		this.driveNumber = driveNumber;
	}
	public String getDepartmentTime() {
		return departmentTime;
	}
	public void setDepartmentTime(String departmentTime) {
		this.departmentTime = departmentTime;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getBusCode() {
		return busCode;
	}
	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}
	public String getRouteCode() {
		return routeCode;
	}
	public void setRouteCode(String routeCode) {
		this.routeCode = routeCode;
	}
	@Override
	public String toString() {
		return "�����ȣ: " + driveNumber + ", ��߽ð�: " + departmentTime + ", �����ð�: "
				+ arriveTime + ", ������ȣ: " + busCode + ", �뼱��ȣ: " + routeCode;
	}
	
}
