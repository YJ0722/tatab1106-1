package com.bit.tatab.board.service;

public interface ActivityService {

	// 테스크 코멘트 입력
	public void insertComment(String login_email, String login_name, String task_name, String alert_message, String user_img, String project_no, String project_name);

	// 테스크 이름 검색
	public String selectTaskName(String task_no);

	// 테스크 마감기한 설정
	public void insertDeadline(String login_email, String login_name, String task_name, String alert_message,
			String user_img, String project_no, String project_name);

	// 멤버 초대 알림
	public void insertNewUser(String login_email, String login_name, String task_name, String alert_message,
			String user_img, String project_no, String project_name);

	// 테스크 추가 알림
	public void createNewTask(String login_email, String login_name, String task_name, String alert_message,
			String user_img, String project_no, String project_name);

	// 프로젝트 이름 가져오기
	public String selectProjectName(String project_no);

	// 멤버로 초대된 것 알림
	public void insertNewUser2(String login_email0, String login_name, String task_name, String alert_message,
			String user_img, String project_no, String project_name);

	// 작업완료 알림
	public void completeTask(String login_email, String login_name, String task_name, String alert_message,
			String user_img, String project_no, String project_name);

	// 과제 멤버할당 알림
	public void setAssignee(String login_email, String login_name, String task_name, String alert_message,
			String user_img, String project_no, String project_name);
	
}
