package com.bit.tatab.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bit.tatab.board.service.TaskService;
import com.bit.tatab.board.vo.BoardTaskVO;
import com.bit.tatab.board.vo.TaskCommentVO;
import com.bit.tatab.board.vo.DateVO;
import com.bit.tatab.myPage.service.MyPageService;

@Controller
public class TaskController {
	
	@Inject
	TaskService taskService;

	@Inject
	MyPageService myPageService;
	
	@ResponseBody
	@RequestMapping(value="selectAllTask.do", method=RequestMethod.POST)
	public Map<String, Object> selectAllTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String task_no = request.getParameter("task_no");
		System.out.println("불러올 task_no : " + task_no);
		
		BoardTaskVO taskVO = taskService.selectAllTask(task_no);
		System.out.println("불러온 taskVO : " + taskVO);
		
		HttpSession session = request.getSession();
		String login_email = session.getAttribute("login_email").toString();
		
		String loginName = myPageService.getLoginName(login_email);
		System.out.println("nickname check!!! : " + loginName);
		
		List<TaskCommentVO> commentVOList = taskService.selectAllComment(task_no);
		for(int i=0; i<commentVOList.size(); i++) {
			System.out.println("comment [" + i + "] : " + commentVOList.get(i).toString());
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loginName", loginName);
		map.put("taskVO", taskVO);
		map.put("commentList", commentVOList);
		map.put("myEmail", login_email);
		
		return map;
	}
	

	@ResponseBody
	@RequestMapping(value="insertComment.do", method=RequestMethod.POST)
	public TaskCommentVO insertComment(HttpServletRequest request, @RequestParam(value="taskNo") String taskNo,
			@RequestParam(value="loginName")String loginName, @RequestParam(value="comment")String comment) throws Exception {
		
		System.out.println("insertComment.do 실행");
		System.out.println("taskNo : " + taskNo);
		System.out.println("loginName : " + loginName);
		System.out.println("comment : " + comment);

		
		HttpSession session = request.getSession();
		int task_no = Integer.parseInt(taskNo);
		String login_email = session.getAttribute("login_email").toString();
		System.out.println("taskNo 확인 : " + String.valueOf(task_no));

		DateVO date = new DateVO();
		String nowDate = date.nowDate();
		
		TaskCommentVO commentVO = new TaskCommentVO(task_no, login_email, loginName, comment, nowDate);
		System.out.println(commentVO.toString());
		
		
		// 댓글 추가
		taskService.insertComment(commentVO);
		
		String cNo = String.valueOf(commentVO.getComment_no());
		System.out.println("결과 comment_Seq 확인 : " + cNo);
		
		
		return commentVO;
	}
	

	@ResponseBody
	@RequestMapping(value="deleteComment.do", method=RequestMethod.GET)
	public void deleteTaskComment(HttpServletRequest request, @RequestParam(value="commentNo") String commentNo) throws Exception {
		
		System.out.println("deleteComment.do 실행");
		System.out.println("commentNo : " + commentNo);

		
		HttpSession session = request.getSession();
		String comment_no = commentNo;
		System.out.println("commentNo 확인1!!! " + commentNo);
		
		// 댓글 삭제
		taskService.deleteTaskComment(commentNo);
		
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
