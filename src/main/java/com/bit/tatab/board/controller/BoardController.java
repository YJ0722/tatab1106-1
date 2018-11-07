package com.bit.tatab.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bit.tatab.board.service.BoardService;
import com.bit.tatab.board.vo.BoardColVO;

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
		session.setAttribute("projectName", projectName);
		session.setAttribute("project_no", project_no);

		System.out.println("board 프로젝트 이름 : " + projectName + ", 프로젝트 고유번호 : " + project_no);

		//////////////////////// 해당 프로젝트의 컬럼 불러오기 /////////////////////////
//		int prj_no = Integer.parseInt(project_no);
//		List<BoardColVO> boardColList = boardService.selectAllProjectCol(prj_no);
//		
//		for(int i=0; i<boardColList.size(); i++) {
//			System.out.println("[" + i + "] : " + boardColList.get(i));
//		}
		///////////////////////////////////////////////////////////////////////
		
		mav.addObject("projectName", projectName);
//		mav.addObject("colData", boardColList);

		return mav;
	}
}
