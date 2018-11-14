package com.bit.tatab.board.dao;

import com.bit.tatab.board.vo.BoardTaskVO;

public interface TaskDAO {

	// task data 가져오기
	public BoardTaskVO selectAllTask(String task_no);

	// task data update
	public void updateTask(BoardTaskVO taskVO);
}
