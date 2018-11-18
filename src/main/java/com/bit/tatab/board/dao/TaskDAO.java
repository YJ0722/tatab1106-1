package com.bit.tatab.board.dao;

import java.util.List;

import com.bit.tatab.board.vo.BoardTaskVO;
import com.bit.tatab.board.vo.TaskCommentVO;

public interface TaskDAO {

	// task data 가져오기
	public BoardTaskVO selectAllTask(String task_no);

	// 댓글 추가
	public void insertComment(TaskCommentVO commentVO);
	
	// task 댓글 가져오기
	public List<TaskCommentVO> selectAllComment(String task_no);
	
	// task 댓글 삭제
	public void deleteTaskComment(String commentNo);

	// 작업 상태 업데이트 ( o -> c )
	public void taskStatusComplete(String taskNo);
	
	// task data update
	public void updateTask(BoardTaskVO taskVO);
}
