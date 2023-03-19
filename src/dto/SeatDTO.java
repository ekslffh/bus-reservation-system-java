package dto;

public class SeatDTO {

	private String seatCode;
	private String driveNumber;
	private String reservationDate;
	
	public String getSeatCode() {
		return seatCode;
	}
	public void setSeatCode(String seatCode) {
		this.seatCode = seatCode;
	}
	public String getDriveNumber() {
		return driveNumber;
	}
	public void setDriveNumber(String driveNumber) {
		this.driveNumber = driveNumber;
	}
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	@Override
	public String toString() {
		return "SeatDTO [seatCode=" + seatCode + ", driveNumber=" + driveNumber + ", reservationDate=" + reservationDate
				+ "]";
	}

}
