package com.bit.tatab.board.dao;

public interface ActivityDAO {

	// 테스크 코멘트 입력
	public void insertComment(String login_email, String login_name, String task_name, String alert_message, String user_img);

	// 테스크 이름 검색
	public String selectTaskName(String task_no);

	// 테스크 마감기한 설정
	public void insertDeadline(String login_email, String login_name, String task_name, String alert_message,
			String user_img);

	// 멤버 초대 알림
	public void insertNewUser(String login_email, String login_name, String task_name, String alert_message,
			String user_img);

	// 테스크 생성 알림
	public void createNewTask(String login_email, String login_name, String task_name, String alert_message,
			String user_img);
	
}
