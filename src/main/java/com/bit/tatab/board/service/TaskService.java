package com.bit.tatab.board.service;

import com.bit.tatab.board.vo.BoardTaskVO;

public interface TaskService {

	// task data 가져오기
	public BoardTaskVO selectAllTask(String task_no);
	
	// task data update
	public void updateTask(BoardTaskVO taskVO);
}
