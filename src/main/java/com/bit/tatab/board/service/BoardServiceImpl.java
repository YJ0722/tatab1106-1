package com.bit.tatab.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bit.tatab.board.dao.BoardDAO;
import com.bit.tatab.board.vo.MemberVO;
import com.bit.tatab.main.vo.ProjectVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Inject
	private BoardDAO boardDao;

	@Override
	public ProjectVO selectAllProjectManage(String project_no) {
		ProjectVO projectManage = boardDao.selectAllProjectManage(project_no);
		return projectManage;
	}

	@Override
	public void updateProjectVO(ProjectVO projectVO, String project_no, String projectName) {
		boardDao.updateProjectVO(projectVO, project_no, projectName);
	}

	@Override
	public List<MemberVO> selectMemberList(String project_no) {
		List<MemberVO> memberList = boardDao.selectMemberList(project_no);
		return memberList;
	}

	@Override
	public boolean addUser(String project_no, String user) {
		return boardDao.addUser(project_no, user);
	}
	
	
}
