package dto;

public class RouteDTO {
	private String code; // �뼱��ȣ
	private int distance; // �Ÿ�
	private int driveTime; // ���࿹��ð�
	private String department; // ��߿�
	private String arrive; // ������
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
		return "�뼱��ȣ: " + code + ", ����Ÿ�: " + distance + ", ����ð�: " + driveTime + ", ��߿�: "
				+ department + ", ������: " + arrive;
	}
}
