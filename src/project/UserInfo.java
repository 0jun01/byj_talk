package project;

import java.io.Serializable;

public class UserInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String pwd;

	public UserInfo(String id, String pwd) {
		id = null;
		pwd = null;
		this.id = id;
		this.pwd = pwd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
