package com.bit.tatab.board.service;

import java.util.List;

import com.bit.tatab.board.vo.MemberVO;
import com.bit.tatab.board.vo.BoardColVO;
import com.bit.tatab.board.vo.BoardTaskVO;
import com.bit.tatab.main.vo.ProjectVO;

public interface ActivityService {

	// 테스크 코멘트 입력
	public void insertComment(String login_email, String login_name, String task_name, String alert_message, String user_img);

	// 테스크 이름 검색
	public String selectTaskName(String task_no);
	
}
