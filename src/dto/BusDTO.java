package dto;

public class BusDTO {
	private String buscode;
	private String companycode;
	private int capacity;
	private String busclass;
	
	public String getBuscode() {
		return buscode;
	}
	public void setBuscode(String buscode) {
		this.buscode = buscode;
	}
	public String getCompanycode() {
		return companycode;
	}
	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getBusclass() {
		return busclass;
	}
	public void setBusclass(String busclass) {
		this.busclass = busclass;
	}
	@Override
	public String toString() {
		return "버스번호: " + buscode + ", 회사코드: " + companycode + ", 수용인원" + capacity + ", 등급: "
				+ busclass;
	}
}
