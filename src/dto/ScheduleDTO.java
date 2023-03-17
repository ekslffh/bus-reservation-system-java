package dto;

public class ScheduleDTO {
	
	private String scheduleCode;
	private String drpartTime;
	private String arriveTime;
	private String drpart;
	private String arrive;
	public String getScheduleCode() {
		return scheduleCode;
	}
	public void setScheduleCode(String scheduleCode) {
		this.scheduleCode = scheduleCode;
	}
	public String getDrpartTime() {
		return drpartTime;
	}
	public void setDrpartTime(String drpartTime) {
		this.drpartTime = drpartTime;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getDrpart() {
		return drpart;
	}
	public void setDrpart(String drpart) {
		this.drpart = drpart;
	}
	public String getArrive() {
		return arrive;
	}
	public void setArrive(String arrive) {
		this.arrive = arrive;
	}
	@Override
	public String toString() {
		return "ScheduleDTO [scheduleCode=" + scheduleCode + ", drpartTime=" + drpartTime + ", arriveTime=" + arriveTime
				+ ", drpart=" + drpart + ", arrive=" + arrive + "]";
	}
	
	

}
