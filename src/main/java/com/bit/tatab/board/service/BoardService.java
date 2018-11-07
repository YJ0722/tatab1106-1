package com.bit.tatab.board.service;

import java.util.List;

import com.bit.tatab.board.vo.MemberVO;
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
}
