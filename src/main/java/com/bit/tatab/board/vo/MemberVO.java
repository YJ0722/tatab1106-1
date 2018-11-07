package com.bit.tatab.board.vo;

public class MemberVO {

	private String login_email;
	private String login_name;
	
	public MemberVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberVO(String login_email, String login_name) {
		super();
		this.login_email = login_email;
		this.login_name = login_name;
	}

	public String getLogin_email() {
		return login_email;
	}

	public void setLogin_email(String login_email) {
		this.login_email = login_email;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	@Override
	public String toString() {
		return "MemberVO [login_email=" + login_email + ", login_name=" + login_name + "]";
	}
	
	
	
	
}
