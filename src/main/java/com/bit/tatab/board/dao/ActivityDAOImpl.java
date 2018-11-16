package com.bit.tatab.board.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class ActivityDAOImpl implements ActivityDAO{

	@Inject
	private SqlSession sqlSession;

	@Override
	public void insertComment(String login_email, String login_name, String task_name, String alert_message, String user_img, String project_no) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("login_email", login_email);
		param.put("login_name", login_name);
		param.put("task_name", task_name);
		param.put("alert_message", alert_message);
		param.put("user_img", user_img);
		param.put("project_no", project_no);
		
		sqlSession.insert("insertIntoActivity", param);
		
	}
	
	// 테스크 마감기한 설정
	@Override
	public void insertDeadline(String login_email, String login_name, String task_name, String alert_message,
			String user_img, String project_no) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("login_email", login_email);
		param.put("login_name", login_name);
		param.put("task_name", task_name);
		param.put("alert_message", alert_message);
		param.put("user_img", user_img);
		param.put("project_no", project_no);
		
		sqlSession.insert("insertIntoActivity", param);
		
	}

	// 멤버 초대 알림
	@Override
	public void insertNewUser(String login_email, String login_name, String task_name, String alert_message,
			String user_img, String project_no) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("login_email", login_email);
		param.put("login_name", login_name);
		param.put("task_name", task_name);
		param.put("alert_message", alert_message);
		param.put("user_img", user_img);
		param.put("project_no", project_no);
		
		sqlSession.insert("insertIntoActivity", param);
	}

	// 테스크 생성 알림
	@Override
	public void createNewTask(String login_email, String login_name, String task_name, String alert_message,
			String user_img, String project_no) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("login_email", login_email);
		param.put("login_name", login_name);
		param.put("task_name", task_name);
		param.put("alert_message", alert_message);
		param.put("user_img", user_img);
		param.put("project_no", project_no);
		
		sqlSession.insert("insertIntoActivity", param);
	}
	
	// 테스크 이름 검색
		@Override
		public String selectTaskName(String task_no) {
			String task_name = sqlSession.selectOne("selectTaskName", task_no);
			return task_name;
		}
	

	
	
}
