package com.bit.tatab.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bit.tatab.board.service.BoardService;
import com.bit.tatab.board.vo.BoardColVO;
import com.bit.tatab.board.vo.BoardTaskVO;
import com.bit.tatab.main.vo.MainBackgroundVO;

@Controller
public class BoardController {
	
	@Inject
	BoardService boardService;

	@ResponseBody
	@RequestMapping(value = "/board.do")
	public ModelAndView board(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView("board");

		String projectName = request.getParameter("projectName");
		String project_no = request.getParameter("project_no");

		HttpSession session = request.getSession();
		
		if (project_no==null) {
			String no = session.getAttribute("project_no").toString();
			project_no = no;
		}
		
		session.setAttribute("projectName", projectName);
		session.setAttribute("project_no", project_no);
		
		// 배경이미지 가져오기(빈 값 포함)
		MainBackgroundVO mainBackgroundVO = (MainBackgroundVO) session.getAttribute("mainBackgroundVO");

		System.out.println("board 프로젝트 이름 : " + projectName + ", 프로젝트 고유번호 : " + project_no);

		// 해당 프로젝트의 컬럼 불러오기
		int prj_no = Integer.parseInt(project_no);
		List<BoardColVO> boardColList = boardService.selectAllProjectCol(prj_no);
		
		for(int i=0; i<boardColList.size(); i++) {
			System.out.println("[" + i + "] : " + boardColList.get(i).toString());
		}
		
		// 해당 프로젝트의 작업 불러오기 
		List<BoardTaskVO> boardTaskList = boardService.selectBoardTaskAll(prj_no);

		for(int i=0; i<boardTaskList.size(); i++) {
			System.out.println("[" + i + "] : " + boardTaskList.get(i).toString());
		}
		
		mav.addObject("projectName", projectName);
		mav.addObject("colData", boardColList);
		mav.addObject("taskData", boardTaskList);
		mav.addObject("mainBackgroundVO", mainBackgroundVO);
		
		return mav;
	}
	
	
	// Board 에 작업 추가
	@ResponseBody
	@RequestMapping(value = "/insertBoardTask.do", method = RequestMethod.POST)
	public void insertBoardTask(HttpServletRequest request, String task_name, @RequestParam("col_no")int no) throws Exception {

		HttpSession session = request.getSession();
		int project_no = Integer.parseInt((String) session.getAttribute("project_no"));
		
		System.out.println("파라미터 확인  \n" + "col_no : " + no + "\ntask_name : " + String.valueOf(task_name));

		BoardTaskVO boardTaskVO = new BoardTaskVO(project_no, no, task_name, "", "O", "-", "-");
		System.out.println("^^^" + boardTaskVO.toString());
		
		int index = boardService.checkTaskIndex(boardTaskVO);
		System.out.println("------------------" + String.valueOf(index));
		
		if(index == 0) {
			boardTaskVO.setTask_index(0);
			System.out.println("insert 전 index 확인 : " + String.valueOf(boardTaskVO.getTask_index()));
			boardService.insertBoardTask(boardTaskVO);
		} else {
			boardTaskVO.setTask_index(index);
			System.out.println("insert 전 index 확인 : " + String.valueOf(boardTaskVO.getTask_index()));
			boardService.insertBoardTask(boardTaskVO);
		}
		
		
	}

	@RequestMapping(value="insertCol.do", method=RequestMethod.POST)
	public int insertCol(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView("board");
		
		HttpSession session = request.getSession();
		String project_no = session.getAttribute("project_no").toString();
		String colName = request.getParameter("ColName");
		
		System.out.println("추가될 프로젝트 번호 : " + project_no + " 추가될 컬럼 이름 : " + colName); 
		
		int colNo = boardService.insertCol(project_no, colName);
		 
		return colNo;
//		return "redirect:/board.do";
	}

	// board 컬럼 순서 변경 업데이트
	@ResponseBody
	@RequestMapping(value="updateBoardCol.do", method=RequestMethod.POST)
	public void updateBoardCol(HttpServletRequest request,
			@RequestParam("colIndexArr")ArrayList<String> colNoArr) throws Exception {
		
		System.out.println("updateBoardCol.do 시작!!!!!");

		HttpSession session = request.getSession();
		int project_no = Integer.parseInt((String) session.getAttribute("project_no"));

		List<BoardColVO> colUpdateList = new ArrayList();
		for(int index=0; index<colNoArr.size(); index++) {
			BoardColVO boardColVO = new BoardColVO(project_no, Integer.parseInt(colNoArr.get(index)), index);		
			colUpdateList.add(boardColVO);
		}

		boardService.colIndexUpdate(colUpdateList);
	}
	
	// board 작업 순서 변경 업데이트
	@ResponseBody
	@RequestMapping(value="updateBoardTask.do", method=RequestMethod.GET)
	public void updateBoardTask(HttpServletRequest request, int colIndex,
			@RequestParam("taskNoArr")ArrayList<String> taskNoArr) throws Exception {

		System.out.println("updateBoardTask.do 시작!!!!!");

		HttpSession session = request.getSession();
		int project_no = Integer.parseInt((String) session.getAttribute("project_no"));

		List<BoardTaskVO> taskUpdateList = new ArrayList();
		if(taskNoArr != null) {
			System.out.println("null 아님...");
			// todo : 작업 인덱스 업데이트
			for(int index=0; index<taskNoArr.size(); index++) {
				BoardTaskVO boardTaskVO = new BoardTaskVO(project_no, colIndex, 
						Integer.parseInt(taskNoArr.get(index)), index);		
				System.out.println(taskNoArr.get(index).toString());
				taskUpdateList.add(boardTaskVO);
			}
			
		} else {
			System.out.println("null 이야....");
		}

		boardService.taskIndexUpdate(taskUpdateList);
	}
	
}
