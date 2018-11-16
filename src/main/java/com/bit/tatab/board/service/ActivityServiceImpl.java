package com.bit.tatab.board.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bit.tatab.board.dao.ActivityDAO;

@Service
public class ActivityServiceImpl implements ActivityService{

	@Inject
	private ActivityDAO activityDAO;

	// 테스크 이름 검색
	@Override
	public String selectTaskName(String task_no) {
		String task_name = activityDAO.selectTaskName(task_no);
		return task_name;
	}
	
	// 테스크 코멘트 입력
	@Override
	public void insertComment(String login_email, String login_name, String task_name, String alert_message, String user_img, String project_no) {
		alert_message = " 님이 " + task_name + " 과제에 댓글을 입력했습니다 : '" + alert_message + "'"; 
		activityDAO.insertComment(login_email, login_name, task_name, alert_message, user_img, project_no);
	}
	
	// 테스크 마감기한 설정
	@Override
	public void insertDeadline(String login_email, String login_name, String task_name, String alert_message,
			String user_img, String project_no) {
		alert_message = " 님이 " + task_name + "의 마감기한을 설정했습니다.";
		activityDAO.insertDeadline(login_email, login_name, task_name, alert_message, user_img, project_no);
	}
	
	// 멤버 초대 알림
	@Override
	public void insertNewUser(String login_email, String login_name, String task_name, String alert_message,
			String user_img, String project_no) {
		alert_message = " 님이 프로젝트에 초대되었습니다."; 
		activityDAO.insertNewUser(login_email, login_name, task_name, alert_message, user_img, project_no);
	}

	// 테스크 추가 알림
	@Override
	public void createNewTask(String login_email, String login_name, String task_name, String alert_message,
			String user_img, String project_no) {
		alert_message = " 님이 새로운 과제를 만들었습니다 : '" + task_name + "'";
		activityDAO.createNewTask(login_email, login_name, task_name, alert_message, user_img, project_no);
	}
	
	
	
}
