package com.bit.tatab.board.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.tatab.board.service.TaskService;
import com.bit.tatab.board.vo.BoardTaskVO;

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
}
