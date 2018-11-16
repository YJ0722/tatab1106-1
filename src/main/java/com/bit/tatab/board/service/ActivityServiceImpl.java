package com.bit.tatab.board.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bit.tatab.board.dao.ActivityDAO;

@Service
public class ActivityServiceImpl implements ActivityService{

	@Inject
	private ActivityDAO activityDAO;

	// 테스크 코멘트 입력
	@Override
	public void insertComment(String login_email, String login_name, String task_name, String alert_message, String user_img) {
		alert_message = " 님이 댓글을 입력했습니다 : '" + alert_message + "'"; 
		
		activityDAO.insertComment(login_email, login_name, task_name, alert_message, user_img);
		
	}
	// 테스크 이름 검색
	@Override
	public String selectTaskName(String task_no) {
		String task_name = activityDAO.selectTaskName(task_no);
		return task_name;
	}
	
}
