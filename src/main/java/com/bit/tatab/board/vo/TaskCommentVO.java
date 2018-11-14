package com.bit.tatab.board.vo;

public class TaskCommentVO {

	private int comment_no;		// 시퀀스
	private int task_no;
	private String login_email;
	private String nickname;
	private String task_comment;
	private String reg_date;

	public TaskCommentVO() {
		super();
	}

	public TaskCommentVO(int task_no, String login_email, String nickname, String task_comment, String reg_date) {
		super();
		this.task_no = task_no;
		this.login_email = login_email;
		this.nickname = nickname;
		this.task_comment = task_comment;
		this.reg_date = reg_date;
	}

	public TaskCommentVO(int comment_no, int task_no, String login_email, String nickname, String task_comment, String reg_date) {
		super();
		this.comment_no = comment_no;
		this.task_no = task_no;
		this.login_email = login_email;
		this.nickname = nickname;
		this.task_comment = task_comment;
		this.reg_date = reg_date;
	}

	public int getComment_no() {
		return comment_no;
	}

	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}

	public int getTask_no() {
		return task_no;
	}

	public void setTask_no(int task_no) {
		this.task_no = task_no;
	}

	public String getLogin_email() {
		return login_email;
	}

	public void setLogin_email(String login_email) {
		this.login_email = login_email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTask_comment() {
		return task_comment;
	}

	public void setTask_comment(String task_comment) {
		this.task_comment = task_comment;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	@Override
	public String toString() {
		return "CommentVO [comment_no=" + comment_no + ", task_no=" + task_no + ", login_email=" + login_email
				+ ", nickname=" + nickname + ", task_comment=" + task_comment + ", reg_date=" + reg_date + "]";
	}

	
	
}
