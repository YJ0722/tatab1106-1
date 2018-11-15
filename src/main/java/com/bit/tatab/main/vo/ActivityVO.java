package com.bit.tatab.main.vo;

import java.util.Date;

public class ActivityVO {
	
	// 여기서 save_name은 프로필 사진 파일의 save_name이다!
	private String login_email;
	private String login_name;
	private String save_name;
	private String alert_message;
	private Date alert_time;
	private String project_name;
	private long diffMin;
	public ActivityVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ActivityVO(String login_email, String login_name, String save_name, String alert_message, Date alert_time,
			String project_name) {
		super();
		this.login_email = login_email;
		this.login_name = login_name;
		this.save_name = save_name;
		this.alert_message = alert_message;
		this.alert_time = alert_time;
		this.project_name = project_name;
	}
	
	public long getDiffMin() {
		return diffMin;
	}
	public void setDiffMin(long diffMin) {
		this.diffMin = diffMin;
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
	public String getSave_name() {
		return save_name;
	}
	public void setSave_name(String save_name) {
		this.save_name = save_name;
	}
	public String getAlert_message() {
		return alert_message;
	}
	public void setAlert_message(String alert_message) {
		this.alert_message = alert_message;
	}
	public Date getAlert_time() {
		return alert_time;
	}
	public void setAlert_time(Date alert_time) {
		this.alert_time = alert_time;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	@Override
	public String toString() {
		return "ActivityVO [login_email=" + login_email + ", login_name=" + login_name + ", save_name=" + save_name
				+ ", alert_message=" + alert_message + ", alert_time=" + alert_time + ", project_name=" + project_name
				+ ", diffMin=" + diffMin + "]";
	}	
	
}
