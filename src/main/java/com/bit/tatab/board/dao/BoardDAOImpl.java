package com.bit.tatab.board.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bit.tatab.board.vo.MemberVO;
import com.bit.tatab.main.vo.ProjectVO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Inject
	private SqlSession sqlSession;

	@Override
	public ProjectVO selectAllProjectManage(String projectName) {
		
		ProjectVO projectVO = sqlSession.selectOne("selectAllProjectManage", projectName);
		System.out.println(projectVO);
		
		return projectVO;
	}

	@Override
	public void updateProjectVO(ProjectVO projectVO, String projectName) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("updateName", projectVO.getProject_name());
		param.put("updateDescription",projectVO.getProject_comment());
		param.put("projectName", projectName);
		
		sqlSession.update("updatePRJ_T", param);
		sqlSession.update("updateProject_List", param);
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
	public void addUser(String project_no, String user) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("project_no", project_no);
		param.put("user", user);
		
		sqlSession.insert("addUser", param);
	}
	
	
	
}
