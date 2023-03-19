package dto;

public class ReservationDTO {
	private String num;
	private String memberId;
	private String seatCode;
	private String driveNum;
	private String reservationDate;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getSeatCode() {
		return seatCode;
	}

	public void setSeatCode(String seatCode) {
		this.seatCode = seatCode;
	}

	public String getDriveNum() {
		return driveNum;
	}

	public void setDriveNum(String driveNum) {
		this.driveNum = driveNum;
	}

	public String getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}

	@Override
	public String toString() {
		return "ReservationDTO [num=" + num + ", memberId=" + memberId + ", seatCode=" + seatCode + ", driveNum="
				+ driveNum + ", reservationDate=" + reservationDate + "]";
	}
}
