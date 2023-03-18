package dto;

public class RouteDTO {
	private String code; // 노선번호
	private int distance; // 거리
	private int driveTime; // 운행예상시간
	private String department; // 출발역
	private String arrive; // 도착역
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getDriveTime() {
		return driveTime;
	}
	public void setDriveTime(int driveTime) {
		this.driveTime = driveTime;
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
	@Override
	public String toString() {
		return "노선번호: " + code + ", 운행거리: " + distance + ", 운행시간: " + driveTime + ", 출발역: "
				+ department + ", 도착역: " + arrive;
	}
}
