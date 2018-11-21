package com.bit.tatab.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bit.tatab.board.service.BoardService;
import com.bit.tatab.board.vo.MemberVO;
import com.bit.tatab.main.service.MainService;
import com.bit.tatab.main.vo.ActivityVO;
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
		System.out.println("ajax : "  + projectList);
		
		return  projectList;
		
	}
	
	@ResponseBody
	@RequestMapping(value="boardProjectManage.do", method=RequestMethod.POST)
	public ProjectVO projectManage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
//		String projectName = session.getAttribute("projectName").toString();
		String project_no = session.getAttribute("project_no").toString();
		
		
		ProjectVO projectVO = boardService.selectAllProjectManage(project_no); 
		
		return projectVO;
		
	}
	
	@RequestMapping(value="updateProjectVO.do", method = RequestMethod.POST)
	@ResponseBody
	public ProjectVO updateProjectVO(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String projectName = session.getAttribute("projectName").toString();
		String project_no = session.getAttribute("project_no").toString();
		
		String updateName = request.getParameter("projectName");
		String projectDescription = request.getParameter("projectDescription");
		
		ProjectVO projectVO = new ProjectVO(updateName, projectDescription);
		
		boardService.updateProjectVO(projectVO, project_no, projectName);
		
		session.setAttribute("projectName", updateName);
		
//		ModelAndView mav = new ModelAndView("board");
		
		return projectManage(request, response);
	}
	
	@RequestMapping(value="memberList.do", method = RequestMethod.POST)
	@ResponseBody
	public List<MemberVO> memberList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String project_no = session.getAttribute("project_no").toString();
		System.out.println("불러올 프로젝트 고유번호 : " + project_no);
		
		List<MemberVO> memberList = boardService.selectMemberList(project_no);
		
		// 보드 액티비티 위해 세션에 추가
		session.setAttribute("memberList", memberList);
		System.out.println("memberList : " + memberList);
		return memberList;
	}
	
	@RequestMapping(value="addUser.do", method = RequestMethod.POST) 
	@ResponseBody
	public String addUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String project_no = session.getAttribute("project_no").toString();
		
		String user = request.getParameter("user");
//		System.out.println("추가할 user : " + user);
		
		boolean bool = boardService.addUser(project_no, user); 
		
		if(bool == true ) {
			System.out.println("사용자 추가 완료");
		} else {
			System.out.println("없는 사용자입니다");
		}
		
		return bool+"";
				
	}
	
	// 액티비티 내용 가져오기(탑메뉴)
    @RequestMapping(value="topMenuActivity.do", method = RequestMethod.GET)
	@ResponseBody
	public List<ActivityVO> activityList(HttpServletRequest request) throws Exception {
		
    	// 세션에서 프로젝트 번호 가져오기
    	HttpSession session = request.getSession();
    	String project_no = session.getAttribute("project_no").toString();
    	
    	System.out.println("불러올 프로젝트 번호 : " + project_no);
		
		List<ActivityVO> activityList = boardService.selectActivityList(project_no);
		
		System.out.println("activityList : " + activityList);
		return activityList;
	}

    // project 삭제
    @RequestMapping(value="deleteProject.do", method=RequestMethod.GET) 
    public String deleteProject(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
    	
    	
    	String project_no = session.getAttribute("project_no").toString();
    	System.out.println("삭제할 프로젝트 no : " + project_no);
    	
    	boardService.deleteProject(project_no);
    	
    	return "redirect:userMain.do";
    }
}
















