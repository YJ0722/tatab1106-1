package com.bit.tatab.board.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bit.tatab.board.service.TaskService;
import com.bit.tatab.board.vo.BoardTaskVO;
import com.bit.tatab.board.vo.DateVO;

@Controller
public class TaskController {
	
	@Inject
	TaskService taskService;
	
	@ResponseBody
	@RequestMapping(value="selectAllTask.do", method=RequestMethod.POST)
	public BoardTaskVO selectAllTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String task_no = request.getParameter("task_no");
		System.out.println("불러올 task_no : " + task_no);
		
		BoardTaskVO taskVO = taskService.selectAllTask(task_no);
		System.out.println("불러온 taskVO : " + taskVO);
		return taskVO;
	}
	
	@RequestMapping(value="updateTask.do", method=RequestMethod.POST)
	public String updateTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView("/board.do");
		
		String task_name= request.getParameter("task_name");
		String task_content = request.getParameter("task_content");
		String dday = request.getParameter("dday");
		String task_no = request.getParameter("task_no");
		  
		DateVO date = new DateVO();
		String updateDate = date.nowDate();
		System.out.println("현재 날짜 : " + updateDate);
		
		BoardTaskVO taskVO = new BoardTaskVO(Integer.parseInt(task_no), task_name, task_content, dday, updateDate);
		System.out.println("업데이트할 taskVO : " + taskVO);
		taskService.updateTask(taskVO); 
		
		return "redirect:board.do";
	}
}
