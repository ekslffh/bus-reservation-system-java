package dto;

public class WorkDTO {
	private String workCode;
	private String workDate;
	private String id;
	private String busCode;
	
	public String getWorkCode() {
		return workCode;
	}
	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusCode() {
		return busCode;
	}
	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}
	@Override
	public String toString() {
		return "WorkDTO [workCode=" + workCode + ", workDate=" + workDate + ", id=" + id + ", busCode=" + busCode + "]";
	}
}
