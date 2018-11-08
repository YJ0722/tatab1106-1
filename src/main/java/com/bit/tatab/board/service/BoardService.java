package com.bit.tatab.board.service;

import java.util.List;

import com.bit.tatab.board.vo.MemberVO;
import com.bit.tatab.board.vo.BoardColVO;
import com.bit.tatab.main.vo.ProjectVO;

public interface BoardService {

	// board - ProjectMange 데이터 가져오기
	public ProjectVO selectAllProjectManage(String project_no);
	
	// projectManage data update
	public void updateProjectVO(ProjectVO projectVO, String project_no, String projectName);
	
	// projectManage memberlist 조회
	public List<MemberVO> selectMemberList(String project_no);
	
	// projectManage adduser
	public boolean addUser(String project_no, String user);
	// 프로젝트 생성 시 자동으로 생성되는 컬럼 1개 생성
	public void makeFirstCol(ProjectVO project);
	
	// 프로젝트 해당하는 컬럼 정보 조회 
	public List<BoardColVO> selectAllProjectCol(int project_no);
	
	// board에 컬럼 추가
	public void insertCol(String project_no, String colName);
}
