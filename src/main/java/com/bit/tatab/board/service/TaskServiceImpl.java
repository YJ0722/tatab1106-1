package com.bit.tatab.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bit.tatab.board.dao.TaskDAO;
import com.bit.tatab.board.vo.BoardTaskVO;
import com.bit.tatab.board.vo.TaskCommentVO;
import com.bit.tatab.board.vo.TaskFileVO;

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

	// task data update
	@Override
	public void updateTask(BoardTaskVO taskVO) {
		taskDao.updateTask(taskVO);
	}
	
	// 댓글 추가
	@Override
	public void insertComment(TaskCommentVO commentVO) {
		taskDao.insertComment(commentVO);
	}
	

	// task 댓글 가져오기
	@Override
	public List<TaskCommentVO> selectAllComment(String task_no) {
		System.out.println("service : " + task_no);
		return taskDao.selectAllComment(task_no);
	}


	// 작업 상태 업데이트 ( o -> c )
	public void taskStatusComplete(String taskNo) {
		taskDao.taskStatusComplete(taskNo);
	}
	
	// task 댓글 삭제
	@Override
	public void deleteTaskComment(String commentNo) {
		taskDao.deleteTaskComment(commentNo);
	}

	// 테스크 파일 업로드
	@Override
	public void insertTaskFile(TaskFileVO taskFileVO) {
		taskDao.insertTaskFile(taskFileVO);
	}

	// 테스크 파일 가져오기
	@Override
	public TaskFileVO selectTaskFile(String task_no) {
		TaskFileVO taskFileVO = taskDao.selectTaskFile(task_no);
		return taskFileVO;
	}
	
	
}
