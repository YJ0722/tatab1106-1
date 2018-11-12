package com.bit.tatab.board.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bit.tatab.board.vo.BoardTaskVO;

@Repository
public class TaskDAOImpl implements TaskDAO {

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public BoardTaskVO selectAllTask(String task_no) {
		BoardTaskVO taskVO = sqlSession.selectOne("selectAllTask", task_no);
		return taskVO;
	}

}
