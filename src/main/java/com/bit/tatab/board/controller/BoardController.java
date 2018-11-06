package com.bit.tatab.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BoardController {

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

		mav.addObject("projectName", projectName);

		return mav;
	}
}
