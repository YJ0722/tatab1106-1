package com.bit.tatab.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.tatab.board.service.BoardService;
import com.bit.tatab.board.service.TaskService;
import com.bit.tatab.board.vo.BoardColVO;
import com.bit.tatab.board.vo.BoardTaskVO;
import com.bit.tatab.board.vo.DateVO;
import com.bit.tatab.board.vo.TaskCommentVO;
import com.bit.tatab.main.service.MainService;
import com.bit.tatab.main.vo.ProjectVO;
import com.bit.tatab.myPage.service.MyPageService;

@Controller
public class MBoardController {

	@Inject
	BoardService boardService;
	@Inject
	MainService mainService;
	@Inject
	TaskService taskService;

	@Inject
	MyPageService myPageService;
	
	// 로그인 아이디 세션에 등록 & 프로젝트 리스트 조회 
	@RequestMapping(value="mProjectList.do", method=RequestMethod.POST)
	@ResponseBody
	public List<ProjectVO> mProjectList(HttpSession session, @RequestParam("login_email") String login_email) {
		
//		HttpSession session = request.getSession();
		System.out.println("yyj mProjectList session/"+session.getId());

		System.out.println("HELLO");
		
//		String login_email = session.getAttribute("login_email").toString();
        System.out.println("login_email : " + login_email);
        
        session.setAttribute("login_email", login_email);
		 
        List<ProjectVO> projectList = mainService.selectAllProject(login_email);
		System.out.println("ajax : "  + projectList);
		
		return  projectList;
	}

	// 프로젝트의 컬럼 정보 조회 
	@RequestMapping(value="mBoardColList.do", method=RequestMethod.POST)
	@ResponseBody
	public List<BoardColVO> mBoardColList(HttpSession session, @RequestParam("project_no") String project_no) {
		
		System.out.println("yyj mProjectList session/"+session.getId());

		System.out.println("프로젝트 고유번호 : " + project_no);

		// 해당 프로젝트의 컬럼 불러오기
		int prj_no = Integer.parseInt(project_no);
		List<BoardColVO> boardColList = boardService.selectAllProjectCol(prj_no);
		
		for(int i=0; i<boardColList.size(); i++) {
			System.out.println("[" + i + "] : " + boardColList.get(i).toString());
		}
		
		return boardColList;
	}
	
	// 프로젝트의 작업 정보 조회
	@RequestMapping(value="mBoardTaskList.do", method=RequestMethod.POST)
	@ResponseBody
	public List<BoardTaskVO> mBoardTaskList(HttpSession session,
			@RequestParam("project_no") String project_no, @RequestParam("col_no") String col_no) {
		
		System.out.println("yyj mProjectList session/"+session.getId());

		System.out.println("컬럼 고유번호 : " + col_no);

		// 해당 컬럼의 task 불러오기
		int project_no_int = Integer.parseInt(project_no);
		int col_no_int = Integer.parseInt(col_no);
		
		BoardTaskVO paramTaksVO = new BoardTaskVO(project_no_int, col_no_int);
	
		List<BoardTaskVO> boardTaskList = boardService.mSelectBoardTaskAll(paramTaksVO);
		
		for(int i=0; i<boardTaskList.size(); i++) {
			System.out.println("[" + i + "] : " + boardTaskList.get(i).toString());
		}
		
		return boardTaskList;
	}
	
	// task 해당하는 comment 리스트 불러오기
	@ResponseBody
	@RequestMapping(value="mTaskCommentList.do", method=RequestMethod.POST)
	public List<TaskCommentVO> mSelectTask(HttpSession session, @RequestParam("task_no") String task_no) throws Exception {
		
		System.out.println("불러올 task_no : " + task_no);
		
		List<TaskCommentVO> commentVOList = taskService.selectAllComment(task_no);
		for(int i=0; i<commentVOList.size(); i++) {
			System.out.println("comment [" + i + "] : " + commentVOList.get(i).toString());
		}
		
		return commentVOList;
	}
	
}
