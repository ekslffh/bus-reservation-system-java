package dto;

public class MemberDTO {
	public static boolean check = false;
	private String m_id;
	private String m_pw;
	private String m_name;
	private String m_telno;
	private String m_add;

	public MemberDTO() {}
	public MemberDTO(String m_id, String m_pw, String m_name, String m_telno, String m_add) {
		this.m_id = m_id;
		this.m_pw = m_pw;
		this.m_name = m_name;
		this.m_telno = m_telno;
		this.m_add = m_add;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public boolean getCheck(boolean check) {
		return check;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_pw(String m_pw) {
		this.m_pw = m_pw;
	}

	public String getM_pw() {
		return m_pw;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_telno(String m_telno) {
		this.m_telno = m_telno;
	}

	public String getM_telno() {
		return m_telno;
	}

	public void setM_add(String m_add) {
		this.m_add = m_add;
	}

	public String getM_add() {
		return m_add;
	}

	@Override
	public String toString() {
		return "MemberDTO [m_id=" + m_id + ", m_pw=" + m_pw + ", m_name=" + m_name + ", m_telno=" + m_telno + ", m_add="
				+ m_add + "]";
	}
}
