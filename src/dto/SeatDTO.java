package dto;

public class SeatDTO {

	private String seatCode;
	private String driveNumber;

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

	@Override
	public String toString() {
		return "SeatDTO [seatCode=" + seatCode + ", driveNumber=" + driveNumber + "]";
	}

}
