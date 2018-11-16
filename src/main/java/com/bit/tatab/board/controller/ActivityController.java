package com.bit.tatab.board.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.tatab.board.service.ActivityService;
import com.bit.tatab.board.service.BoardService;
import com.bit.tatab.login.service.LoginService;
import com.bit.tatab.login.vo.LoginVO;
import com.bit.tatab.myPage.vo.ProfileImgVO;

@Controller
public class ActivityController {
	
	@Inject
	BoardService boardService;
	
	@Inject
	ActivityService activityService;
	
	@Inject
	LoginService loginService;
	
	// 댓글 입력 액티비티에 업데이트
	@ResponseBody
	@RequestMapping(value="commentInsert.do", method=RequestMethod.GET)
	public void insertComment(HttpServletRequest request,
			@RequestParam String login_name, @RequestParam String task_no, @RequestParam String alert_message) throws Exception {
		
		System.out.println("commentInsert.do 시작!!!!!");
		System.out.println("사용자 이름 : " + login_name + ", 테스크 번호 : " + task_no + ", 댓글 내용 : " + alert_message);
		String user_img = null;

		// 세션으로 이메일, 프로젝트 번호, 프로필사진 가져오기
		HttpSession session = request.getSession();
		String login_email = session.getAttribute("login_email").toString();
		String project_no = session.getAttribute("project_no").toString();
		ProfileImgVO profileImgVO = loginService.profileImgFind(login_email);
		System.out.println("프로필사진 잘 가져왔니?" + profileImgVO);
		if(profileImgVO == null) {
			user_img = "default.jpg"; 
		} else {
			user_img = profileImgVO.getSave_name();
		}
		System.out.println("가져온 유저 프로필 save_name : " + user_img);
		
		// task_no로 task_name 가져오기
		String task_name = activityService.selectTaskName(task_no);
		System.out.println("테스크 이름 잘 가져왔니? " + task_name);

		activityService.insertComment(login_email, login_name, task_name, alert_message, user_img, project_no);
		System.out.println("테스크 코멘트 db에 삽입 완료!!! 확인해보자!");
	}
	
	// 마감기한 설정 액티비티에 업데이트
	@ResponseBody
	@RequestMapping(value="deadlineInsert.do", method=RequestMethod.GET)
	public void insertDeadline(HttpServletRequest request,
			@RequestParam String task_name, @RequestParam String alert_message) throws Exception {
		
		System.out.println("deadlineInsert.do 시작!!!");
		System.out.println("테스크 이름 : " + task_name + ", 마감기한 : " + alert_message);
		String user_img = null;
		String login_name = null;

		// 세션으로 사용자 이름, 프로젝트 번호, 프로필사진 가져오기
		HttpSession session = request.getSession();
		String login_email = session.getAttribute("login_email").toString();
		String project_no = session.getAttribute("project_no").toString();
		LoginVO loginVO = new LoginVO();
		loginVO.setLoginEmail(login_email);
		List<LoginVO> loginVOList = loginService.memberInfoFind(loginVO);
		if(loginVOList.size() != 0) {
			login_name = loginVOList.get(0).getLoginName();
			System.out.println("이메일로 사용자 이름 잘 가져왔니? : " + login_name);
		}
		
		ProfileImgVO profileImgVO = loginService.profileImgFind(login_email);
		System.out.println("프로필사진 잘 가져왔니?" + profileImgVO);
		if(profileImgVO == null) {
			user_img = "default.jpg"; 
		} else {
			user_img = profileImgVO.getSave_name();
		}
		System.out.println("가져온 유저 프로필 save_name : " + user_img);
		
		activityService.insertDeadline(login_email, login_name, task_name, alert_message, user_img, project_no);
		System.out.println("테스크 마감기한 db에 삽입 완료!!! 확인해보자!");
	}
	
	// 멤버초대 액티비티에 업데이트
	@ResponseBody
	@RequestMapping(value="addUserActivity.do", method=RequestMethod.GET)
	public void insertNewUser(HttpServletRequest request, @RequestParam String login_email0) throws Exception {
		
		System.out.println("addUserActivity.do 시작!!!");
		System.out.println("초대된 멤버 아이디 : " + login_email0);
		String user_img = null;
		String task_name = ".";
		String alert_message = null;
		String login_name = null;
		
		// 세션으로 사용자 이름, 프로필사진 가져오기
		HttpSession session = request.getSession();
		String login_email = session.getAttribute("login_email").toString();
		String project_no = session.getAttribute("project_no").toString();
		LoginVO loginVO = new LoginVO();
		loginVO.setLoginEmail(login_email0);
		List<LoginVO> loginVOList = loginService.memberInfoFind(loginVO);
		if(loginVOList.size() != 0) {
			login_name = loginVOList.get(0).getLoginName();
			System.out.println("이메일로 사용자 이름 잘 가져왔니? : " + login_name);
		}
		ProfileImgVO profileImgVO = loginService.profileImgFind(login_email0);
		System.out.println("초대된 사람 프로필사진 잘 가져왔니?" + profileImgVO);
		if(profileImgVO == null) {
			user_img = "default.jpg"; 
		} else {
			user_img = profileImgVO.getSave_name();
		}
		System.out.println("가져온 초대유저 프로필 save_name : " + user_img);
		
		activityService.insertNewUser(login_email, login_name, task_name, alert_message, user_img, project_no);
		
	}
	
	// 테스크 추가 액티비티에 업데이트
	@ResponseBody
	@RequestMapping(value="createNewTask.do", method=RequestMethod.GET)
	public void createNewTask(HttpServletRequest request, @RequestParam String task_name) throws Exception {
		
		System.out.println("createNewTask.do 시작!!!");
		System.out.println("생성될 테스크 제목 : " + task_name);
		String user_img = null;
		String login_name = null;
		String alert_message = null;
		
		// 세션으로 이메일, 프로필사진 가져오기
		HttpSession session = request.getSession();
		String login_email = session.getAttribute("login_email").toString();
		String project_no = session.getAttribute("project_no").toString();
		LoginVO loginVO = new LoginVO();
		loginVO.setLoginEmail(login_email);
		List<LoginVO> loginVOList = loginService.memberInfoFind(loginVO);
		if(loginVOList.size() != 0) {
			login_name = loginVOList.get(0).getLoginName();
			System.out.println("이메일로 사용자 이름 잘 가져왔니? : " + login_name);
		}
		ProfileImgVO profileImgVO = loginService.profileImgFind(login_email);
		System.out.println("프로필사진 잘 가져왔니?" + profileImgVO);
		if(profileImgVO == null) {
			user_img = "default.jpg"; 
		} else {
			user_img = profileImgVO.getSave_name();
		}
		System.out.println("가져온 유저 프로필 save_name : " + user_img);
		
		activityService.createNewTask(login_email, login_name, task_name, alert_message, user_img, project_no);
		
	}
	
	
}






