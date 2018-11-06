package com.bit.tatab.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bit.tatab.board.service.BoardService;
import com.bit.tatab.board.vo.MemberVO;
import com.bit.tatab.main.service.MainService;
import com.bit.tatab.main.vo.ProjectVO;


@Controller
public class TopMenuController {
	
	@Inject
	MainService mainService;
	
	@Inject
	BoardService boardService;
	
	// ajax 통신해서 list뽑아와 return
	@ResponseBody
	@RequestMapping(value="boardProjectList.do", method=RequestMethod.POST)
	public List<ProjectVO> ajaxView(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
        String login_email = session.getAttribute("login_email").toString();
		
        List<ProjectVO> projectList = mainService.selectAllProject(login_email);
//		System.out.println("ajax : "  + projectList);
		return  projectList;
		
	}
	
	@ResponseBody
	@RequestMapping(value="boardProjectManage.do", method=RequestMethod.POST)
	public ProjectVO projectManage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String projectName = session.getAttribute("projectName").toString();
		
		ProjectVO projectVO = boardService.selectAllProjectManage(projectName); 
		
		return projectVO;
		
	}
	
	@RequestMapping(value="updateProjectVO.do", method = RequestMethod.POST)
	public ModelAndView updateProjectVO(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String projectName = session.getAttribute("projectName").toString();
		
		String updateName = request.getParameter("projectName");
		String projectDescription = request.getParameter("projectDescription");
		
		ProjectVO projectVO = new ProjectVO(updateName, projectDescription);
		
		boardService.updateProjectVO(projectVO, projectName);
		
		session.setAttribute("projectName", updateName);
		
		ModelAndView mav = new ModelAndView("board");
		
		return mav;
	}
	
	@RequestMapping(value="memberList.do", method = RequestMethod.POST)
	public List<MemberVO> memberList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String project_no = session.getAttribute("project_no").toString();
		System.out.println("불러올 프로젝트 고유번호 : " + project_no);
		
		List<MemberVO> memberList = boardService.selectMemberList(project_no);
		
//		System.out.println("memberList : " + memberList);
		return memberList;
	}
	
	@RequestMapping(value="addUser.do", method = RequestMethod.POST) 
	public String addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String project_no = session.getAttribute("project_no").toString();
		
		String user = request.getParameter("user");
		System.out.println("추가할 user : " + user);
		
		boardService.addUser(project_no, user); 
		
		return "board";
				
	}

}
















