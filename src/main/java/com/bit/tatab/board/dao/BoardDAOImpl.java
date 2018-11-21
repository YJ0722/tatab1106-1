package com.bit.tatab.board.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bit.tatab.board.vo.BoardColVO;
import com.bit.tatab.board.vo.BoardTaskVO;
import com.bit.tatab.board.vo.MemberVO;
import com.bit.tatab.main.vo.ActivityVO;
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
//	프로젝트 멤버 조회
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
	
	// 프로젝트 멤버 추가
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
	
	// 테스크 할당멤버 추가
	@Override
	public boolean addAssignee(String task_no, String assignee, String project_no) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("task_no", task_no);
		param.put("assignee", assignee);
		param.put("project_no", project_no);
		
		String checkUser = sqlSession.selectOne("checkAssignee", param);

		if (checkUser != null) {
			sqlSession.insert("addAssignee", param);
			return true;
		} else {
			return false;
		}
	}
	
	
	// 테스크 멤버 조회
	@Override
	public List<MemberVO> selectTaskMemberList(String task_no) {

		int input = Integer.parseInt(task_no);		

		List<MemberVO> list = sqlSession.selectList("selectTaskMemberList", input);
		List<MemberVO> memberList = new ArrayList<MemberVO>();
		
		for(int i=0; i<list.size(); i++) {
			memberList.add(list.get(i));
		}
		return memberList;
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

	// 작업 추가 전 인덱스 확인
	@Override
	public int checkTaskIndex(BoardTaskVO boardTaskVO) {
		
		String index;
		int indexNum = 0;
		
		try {
				index = sqlSession.selectOne("checkTaskIndex", boardTaskVO);
				if(index == null) {
				} else {
					indexNum = Integer.parseInt(index);
				}
		} catch(NullPointerException e) {

			indexNum = 0;
		}

		return indexNum;
		
	}
	
	// 작업 추가
	@Override
	public void insertBoardTask(BoardTaskVO boardTaskVO) {
		System.out.println("작업 추가!");
		
		System.out.println("boardTaskVO 정보 : " + boardTaskVO.toString());
		
		sqlSession.insert("insertBoardTask", boardTaskVO);
	}

	// 해당 프로젝트의 작업 조회
	@Override
	public List<BoardTaskVO> selectBoardTaskAll(int project_no) {
		System.out.println("전체 작업 조회");
		
		return sqlSession.selectList("selectBoardTaskAll", project_no);
	}

	// 해당 프로젝트의 작업 조회(mobile)
	@Override
	public List<BoardTaskVO> mSelectBoardTaskAll(BoardTaskVO paramTaskVO) {
		return sqlSession.selectList("mSelectBoardTaskAll", paramTaskVO);
	}
	
	// board에 col insert
	@Override
	public int insertCol(BoardColVO boardColVO) {
		
		int index = sqlSession.selectOne("selectColIndex", boardColVO.getProject_no());
		System.out.println("마지막 col index : " + index);
		
		index += 1;
		
		boardColVO.setCol_index(index);
		
		System.out.println("### string : " + boardColVO.toString());
		
		sqlSession.insert("insertCol", boardColVO);
		
		int colNo = boardColVO.getCol_no(); 
				
		System.out.println("colNo : " + String.valueOf(colNo));
		return colNo;
	}

	// board에 컬럼 index 수정
	public void colIndexUpdate(List<BoardColVO> colUpdateList) {
		
		System.out.println("colIndexUpdate dao 시작");
		
		for(int i=0; i<colUpdateList.size(); i++) {
			sqlSession.update("colIndexUpdate", colUpdateList.get(i));
		}
	}
	

	// board에 작업 index 수정
	public void taskIndexUpdate(List<BoardTaskVO> taskUpdateList) {

		System.out.println("taskIndexUpdate dao 시작");
		
		for(int i=0; i<taskUpdateList.size(); i++) {
			sqlSession.update("taskIndexUpdate", taskUpdateList.get(i));
		}
	}

	// 컬럼 이름 변경
	@Override
	public void updateColName(String colId, String updateTitle) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("colId", colId);
		param.put("updateTitle", updateTitle);
		
		sqlSession.update("updateColName", param);
	}

	// 액티비티 리스트 불러오기
	@Override
	public List<ActivityVO> selectActivityList(String project_no) {
		
		List<ActivityVO> activityList = sqlSession.selectList("selectActivityList2", project_no); 
		return activityList;
	}
	
	
	
	
}
