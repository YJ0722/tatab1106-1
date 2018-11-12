package com.bit.tatab.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bit.tatab.board.dao.BoardDAO;
import com.bit.tatab.board.vo.BoardColVO;
import com.bit.tatab.board.vo.BoardTaskVO;
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
	
	// 작업 추가 전 인덱스 확인
	@Override
	public int checkTaskIndex(BoardTaskVO boardTaskVO) {
		return boardDao.checkTaskIndex(boardTaskVO);
	}
	
	// 작업 추가
	@Override
	public void insertBoardTask(BoardTaskVO boardTaskVO) {
		boardDao.insertBoardTask(boardTaskVO);
	}

	// 해당 프로젝트의 작업 조회
	@Override
	public List<BoardTaskVO> selectBoardTaskAll(int project_no) {
		return boardDao.selectBoardTaskAll(project_no);
	}
	
	// board에 col insert
	@Override
	public int insertCol(BoardColVO boardColVO) {
		
		return boardDao.insertCol(boardColVO);
	}
	
	// board에 컬럼 index 수정
	@Override
	public void colIndexUpdate(List<BoardColVO> colUpdateList) {
		boardDao.colIndexUpdate(colUpdateList);
	}

	// board에 작업 index 수정
	@Override
	public void taskIndexUpdate(List<BoardTaskVO> taskUpdaeteList) {
		boardDao.taskIndexUpdate(taskUpdaeteList);
	}

	// 컬럼 이름 변경
	@Override
	public void updateColName(String colId, String updateTitle) {
		boardDao.updateColName(colId, updateTitle);
	}
	
}
