package dto;

public class BusCompanyDTO {
	
	private String code;
	private String name;
	private String address;
	private String tel;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Override
	public String toString() {
		return "BusCompanyDTO [code=" + code + ", name=" + name + ", address=" + address + ", tel=" + tel + "]";
	}
	
}
