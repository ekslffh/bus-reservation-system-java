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
		return "운행번호: " + driveNumber + ", 출발시간: " + departmentTime + ", 도착시간: "
				+ arriveTime + ", 버스번호: " + busCode + ", 노선번호: " + routeCode;
	}
	
}
