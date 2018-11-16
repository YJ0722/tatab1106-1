package com.bit.tatab.board.dao;

public interface ActivityDAO {

	// 테스크 코멘트 입력
	public void insertIntoActivity(String login_email, String login_name, String task_name, String alert_message, String user_img, String project_no, String project_name);

	// 테스크 이름 검색
	public String selectTaskName(String task_no);


	// 프로젝트 이름 가져오기
	public String selectProjectName(String project_no);

}
