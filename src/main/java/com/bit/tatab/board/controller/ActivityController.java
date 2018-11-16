package com.bit.tatab.board.controller;

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
import com.bit.tatab.board.service.TaskService;
import com.bit.tatab.board.vo.BoardTaskVO;
import com.bit.tatab.login.service.LoginService;
import com.bit.tatab.myPage.vo.ProfileImgVO;

@Controller
public class ActivityController {
	
	@Inject
	BoardService boardService;
	
	@Inject
	ActivityService activityService;
	
	@Inject
	LoginService loginService;
	
	// board 컬럼 순서 변경 업데이트
	@ResponseBody
	@RequestMapping(value="commentInsert.do", method=RequestMethod.GET)
	public void insertComment(HttpServletRequest request,
			@RequestParam String login_name, @RequestParam String task_no, @RequestParam String alert_message) throws Exception {
		
		System.out.println("commentInsert.do 시작!!!!!");
		System.out.println("사용자 이름 : " + login_name + ", 테스크 번호 : " + task_no + ", 댓글 내용 : " + alert_message);
		String user_img = null;

		// 세션으로 이메일, 프로필사진 가져오기
		HttpSession session = request.getSession();
		String login_email = session.getAttribute("login_email").toString();
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

		activityService.insertComment(login_email, login_name, task_name, alert_message, user_img);
		System.out.println("테스크 코멘트 db에 삽입 완료!!! 확인해보자!");
	}
	
/*	@ResponseBody
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
*/	
}






