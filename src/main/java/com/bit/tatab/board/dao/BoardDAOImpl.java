package com.bit.tatab.board.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bit.tatab.board.vo.BoardColVO;
import com.bit.tatab.board.vo.MemberVO;
import com.bit.tatab.main.vo.ProjectVO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Inject
	private SqlSession sqlSession;

	@Override
	public ProjectVO selectAllProjectManage(String project_no) {
		
		ProjectVO projectVO = sqlSession.selectOne("selectAllProjectManage", project_no);
		System.out.println(projectVO);
		
		return projectVO;
	}

	@Override
	public void updateProjectVO(ProjectVO projectVO, String project_no, String projectName) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("updateName", projectVO.getProject_name());
		param.put("updateDescription",projectVO.getProject_comment());
		param.put("projectName", projectName);
		param.put("project_no", Integer.parseInt(project_no));
		
		sqlSession.update("updatePRJ_T", param);
//		sqlSession.update("updateProject_List", param);
	}

	@Override
	public List<MemberVO> selectMemberList(String project_no) {
		
		int input = Integer.parseInt(project_no);		

		List<MemberVO> list = sqlSession.selectList("selectMemberList", input);
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		
		for(int i=0; i<list.size(); i++) {
			memberList.add(list.get(i));
		}
		return memberList;
	}

	@Override
	public boolean addUser(String project_no, String user) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("project_no", project_no);
		param.put("user", user);
		
		String checkUser = sqlSession.selectOne("checkUser", user);

		if (checkUser != null) {
			sqlSession.insert("addUser", param);
			return true;
		} else {
			return false;
		}
	}
	
	
	// 프로젝트 생성 시 자동으로 생성되는 컬럼 1개 생성
	@Override
	public void makeFirstCol(ProjectVO project) {
		
		System.out.println("makeFirstCol : 컬럼 생성할 프로젝트 번호 확인 : " + String.valueOf(project));
		
		sqlSession.insert("makeFirstCol", project);
		
	}
	

	// 프로젝트 해당하는 컬럼 정보 조회
	@Override
	public List<BoardColVO> selectAllProjectCol(int project_no) {

		System.out.println("selectAllProjectCol : 컬럼 조회할 프로젝트 번호 확인 : " + String.valueOf(project_no));
		
		List<BoardColVO> boardColList = sqlSession.selectList("selectAllProjectCol", project_no);
		
		System.out.println("----------- check-------------------");
		System.out.println("size: " + String.valueOf(boardColList.size()));
		return boardColList;
	}
}
