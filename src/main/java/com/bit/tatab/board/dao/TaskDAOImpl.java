package com.bit.tatab.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bit.tatab.board.vo.BoardTaskVO;
import com.bit.tatab.board.vo.TaskCommentVO;
import com.bit.tatab.board.vo.TaskFileVO;

@Repository
public class TaskDAOImpl implements TaskDAO {

	@Inject
	private SqlSession sqlSession;
	
	// task data 가져오기
	@Override
	public BoardTaskVO selectAllTask(String task_no) {
		BoardTaskVO taskVO = sqlSession.selectOne("selectAllTask", task_no);
		return taskVO;
	}

	// 댓글 추가
	@Override
	public void insertComment(TaskCommentVO commentVO) {
		sqlSession.insert("insertComment", commentVO);
	}

	// task 댓글 가져오기
	@Override
	public List<TaskCommentVO> selectAllComment(String task_no) {
		System.out.println("dao : " + task_no);
		return sqlSession.selectList("selectAllComment", task_no);
	}

	// task 댓글 삭제
	@Override
	public void deleteTaskComment(String commentNo) {
		sqlSession.delete("deleteTaskComment", commentNo);
	}
	
	// 작업 상태 업데이트 ( o -> c )
	public void taskStatusComplete(String taskNo) {
		sqlSession.update("taskStatusComplete", taskNo);
	}
	
	// task data update
	@Override
	public void updateTask(BoardTaskVO taskVO) {
		sqlSession.update("updateTask", taskVO);
	}

	// 테스크 파일 업로드
	@Override
	public void insertTaskFile(TaskFileVO taskFileVO) {
		sqlSession.insert("insertTaskFile", taskFileVO);
	}

	// 테스크 파일 불러오기
	@Override
	public TaskFileVO selectTaskFile(String task_no) {
		TaskFileVO taskFileVO = sqlSession.selectOne("selectTaskFile", task_no);
		return taskFileVO;
	}

	
}
