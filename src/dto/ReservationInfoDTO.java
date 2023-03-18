package dto;

public class ReservationInfoDTO {
	private static String departTerminal;
	private static String arriveTerminal;
	private static String departTime;
	private static String arriveTime;
	private static String reserveDate;
	private static String seatNum;
	
	public String getDepartTerminal() {
		return departTerminal;
	}
	public void setDepartTerminal(String departTerminal) {
		this.departTerminal = departTerminal;
	}
	public String getArriveTerminal() {
		return arriveTerminal;
	}
	public void setArriveTerminal(String arriveTerminal) {
		this.arriveTerminal = arriveTerminal;
	}
	public String getDepartTime() {
		return departTime;
	}
	public void setDepartTime(String departTime) {
		this.departTime = departTime;
	}
	public String getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getReserveDate() {
		return reserveDate;
	}
	public void setReserveDate(String reserveDate) {
		this.reserveDate = reserveDate;
	}
	public String getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(String seatNum) {
		this.seatNum = seatNum;
	}
}
