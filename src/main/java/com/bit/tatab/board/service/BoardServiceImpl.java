package com.bit.tatab.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bit.tatab.board.dao.BoardDAO;
import com.bit.tatab.board.vo.BoardColVO;
import com.bit.tatab.board.vo.MemberVO;
import com.bit.tatab.main.vo.ProjectVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Inject
	private BoardDAO boardDao;

	@Override
	public ProjectVO selectAllProjectManage(String projectName) {
		ProjectVO projectManage = boardDao.selectAllProjectManage(projectName);
		return projectManage;
	}

	@Override
	public void updateProjectVO(ProjectVO projectVO, String projectName) {
		boardDao.updateProjectVO(projectVO, projectName);
	}
	@Override
	public List<MemberVO> selectMemberList(String project_no) {
		List<MemberVO> memberList = boardDao.selectMemberList(project_no);
		return memberList;
	}

	@Override
	public void addUser(String project_no, String user) {
		boardDao.addUser(project_no, user);
	}
	
	// 프로젝트 생성 시 자동으로 생성되는 컬럼 1개 생성
	@Override
	public void makeFirstCol(ProjectVO project) {
		boardDao.makeFirstCol(project);
	}
	

	// 프로젝트 해당하는 컬럼 정보 조회 
	@Override
	public List<BoardColVO> selectAllProjectCol(int project_no) {
		return boardDao.selectAllProjectCol(project_no);
	}
}
