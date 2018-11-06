package com.bit.tatab.board.dao;

import java.util.List;

import com.bit.tatab.board.vo.MemberVO;
import com.bit.tatab.main.vo.ProjectVO;

public interface BoardDAO {

	// board - projectManage 데이터 가져오기
	public ProjectVO selectAllProjectManage(String projectName);
	
	// projectManage data update
	public void updateProjectVO(ProjectVO projectVO, String projectName);
	
	// projectManage memberList 조회
	public List<MemberVO> selectMemberList(String project_no);

	// projectManage addUser
	public void addUser(String project_no, String user);
}
