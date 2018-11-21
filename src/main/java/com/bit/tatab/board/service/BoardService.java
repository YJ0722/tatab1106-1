package com.bit.tatab.board.service;

import java.util.List;

import com.bit.tatab.board.vo.MemberVO;
import com.bit.tatab.board.vo.BoardColVO;
import com.bit.tatab.board.vo.BoardTaskVO;
import com.bit.tatab.main.vo.ActivityVO;
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

	// 작업 추가 전 인덱스 확인
	public int checkTaskIndex(BoardTaskVO boardTaskVO);
	
	// 작업 추가
	public void insertBoardTask(BoardTaskVO boardTaskVO);

	// 해당 프로젝트의 작업 조회
	public List<BoardTaskVO> selectBoardTaskAll(int project_no);
	
	// 해당 프로젝트의 작업 조회(mobile)
	public List<BoardTaskVO> mSelectBoardTaskAll(BoardTaskVO paramTaskVO);
	
	// board에 컬럼 추가
	public int insertCol(BoardColVO boardColVO);
	
	// board에 컬럼 index 수정
	public void colIndexUpdate(List<BoardColVO> colUpdateList);
	
	// board에 작업 index 수정
	public void taskIndexUpdate(List<BoardTaskVO> taskUpdaeteList);
	
	// 컬럼 이름 변경
	public void updateColName(String colId, String updateTitle);

	// 액티비티 리스트 불러오기
	public List<ActivityVO> selectActivityList(String project_no);
}
