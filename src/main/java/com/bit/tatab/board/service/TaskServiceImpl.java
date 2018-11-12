package com.bit.tatab.board.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bit.tatab.board.dao.TaskDAO;
import com.bit.tatab.board.vo.BoardTaskVO;

@Service
public class TaskServiceImpl implements TaskService{

	@Inject
	private TaskDAO taskDao;
	
	// task data 가져오기
	@Override
	public BoardTaskVO selectAllTask(String task_no) {
		BoardTaskVO taskVO = taskDao.selectAllTask(task_no); 
		return taskVO;
	}
	
	
}
