package com.bit.tatab.board.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bit.tatab.board.service.BoardService;
import com.bit.tatab.board.service.TaskService;
import com.bit.tatab.board.vo.BoardTaskVO;
import com.bit.tatab.board.vo.TaskCommentVO;
import com.bit.tatab.board.vo.TaskFileVO;
import com.bit.tatab.main.vo.MainBackgroundVO;
import com.bit.tatab.board.vo.DateVO;
import com.bit.tatab.board.vo.MemberVO;
import com.bit.tatab.myPage.service.MyPageService;

@Controller
public class TaskController {
	
	@Inject
	TaskService taskService;

	@Inject
	MyPageService myPageService;
	
	@Inject
	BoardService boardService;
	
	@Resource(name = "uploadPath")
	   String uploadPath;
	
	@ResponseBody
	@RequestMapping(value="selectAllTask.do", method=RequestMethod.POST)
	public Map<String, Object> selectAllTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String task_no = request.getParameter("task_no");
		System.out.println("불러올 task_no : " + task_no);
		
		BoardTaskVO taskVO = taskService.selectAllTask(task_no);
		System.out.println("불러온 taskVO : " + taskVO);
		
		HttpSession session = request.getSession();
		String login_email = session.getAttribute("login_email").toString();
		// 테스크 할당멤버 위해 task_no 세션에 추가
		session.setAttribute("task_no", task_no);
		
		String loginName = myPageService.getLoginName(login_email);
		System.out.println("nickname check!!! : " + loginName);
		
		List<TaskCommentVO> commentVOList = taskService.selectAllComment(task_no);
		for(int i=0; i<commentVOList.size(); i++) {
			System.out.println("comment [" + i + "] : " + commentVOList.get(i).toString());
		}
		
		///////////////
		DateVO dateVO = new DateVO();
		System.out.println("dday 날짜 : " + String.valueOf(taskVO.getD_day()));
		String ddayStr;// = null;
		
		
		
		if( String.valueOf(taskVO.getD_day()).equals("null") || String.valueOf(taskVO.getD_day()).equals("-")) {
			ddayStr = null;
		} else {

			int dday = dateVO.calDDay(taskVO.getD_day());
			ddayStr = String.valueOf(dday);
			if(dday > 0) {
				ddayStr = "+" + ddayStr;
			}
			System.out.println("dday 계산 : " + ddayStr);
			
		}
		
		
		////////////////// 파일 불러오기
		TaskFileVO taskFileVO = taskService.selectTaskFile(task_no);
		System.out.println("가져온 파일 vo : " + taskFileVO);
		
		////////////////// 멤버리스트 가져오기
		String project_no = session.getAttribute("project_no").toString();
		System.out.println("불러올 프로젝트 고유번호 : " + project_no);
//		List<MemberVO> memberList = boardService.selectMemberList(project_no);
		List<MemberVO> memberList = boardService.selectTaskMemberList(task_no);
		session.setAttribute("memberList", memberList);
		System.out.println("memberList : " + memberList);
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("loginName", loginName);
		map.put("taskVO", taskVO);
		map.put("commentList", commentVOList);
		map.put("myEmail", login_email);
		map.put("dday", ddayStr);
		map.put("taskFileVO", taskFileVO);
		map.put("memberList", memberList); // 배열임을 참고할 것!
		System.out.println("멤버리스트 : " + memberList);
		
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
		String nowTime = date.nowTime();
		String now = nowDate + " " + nowTime; 
		
		TaskCommentVO commentVO = new TaskCommentVO(task_no, login_email, loginName, comment, now);
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

	@ResponseBody
	@RequestMapping(value="taskStatusComplete.do", method=RequestMethod.GET)
	public String taskStatusComplete(HttpServletRequest request, @RequestParam(value="task_no") String taskNo) throws Exception {
		
		System.out.println("taskStatusComplete.do 실행");
		System.out.println("taskNo : " + taskNo);

		
		HttpSession session = request.getSession();
		BoardTaskVO taskVO = new BoardTaskVO();

		String task_no = taskNo;
		taskVO.setTask_no(Integer.parseInt(task_no));

		// 작업 상태 o -> c
		taskService.taskStatusComplete(task_no);
		
		String status = taskVO.getStatus();
		
		return status;
	}
	
	
	@RequestMapping(value="updateTask.do", method=RequestMethod.POST)
	public String updateTask(HttpServletRequest request, MultipartFile file, HttpSession session) throws Exception {
		
		ModelAndView mav = new ModelAndView("/board.do");
		
		String task_name= request.getParameter("task_name");
		String task_content = request.getParameter("task_content");
		String dday = request.getParameter("dday");
		String task_no = request.getParameter("task_no");
		System.out.println("task_no : " + task_no);
		
		DateVO date = new DateVO();
		String updateDate = date.nowDate();
		System.out.println("현재 날짜 : " + updateDate);
		
		BoardTaskVO taskVO = new BoardTaskVO(Integer.parseInt(task_no), task_name, task_content, dday, updateDate);
		System.out.println("업데이트할 taskVO : " + taskVO.toString());
		taskService.updateTask(taskVO); 
		
		// 파일 업로드 진행 : task_attachment_t
		TaskFileVO taskFileVO = new TaskFileVO();
		
		// 원본이름 저장
		String task_save_name = file.getOriginalFilename();
		System.out.println("task_save_name : " + task_save_name);
		
		// 파일명 랜덤생성 메소드 호출
		task_save_name = uploadFile(task_save_name, file.getBytes());
		taskFileVO.setTask_no(Integer.parseInt(task_no));
		taskFileVO.setTask_ori_name(file.getOriginalFilename());
		taskFileVO.setTask_save_name(task_save_name);
		
		// 업로드 파일 세션에 추가(필요 없으면 추후에 삭제)
		session.setAttribute("taskFileVO", taskFileVO);
		
		// 파일 db에 업로드
		taskService.insertTaskFile(taskFileVO, task_no);
		System.out.println("테스크 파일 DB 입력 완료 - 확인할 것!");
		
		return "redirect:board.do";
	}
	
	// 테스크 멤버 추가
	@RequestMapping(value="addAssignee.do", method = RequestMethod.GET) 
	@ResponseBody
	public String addAssignee(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String task_no = session.getAttribute("task_no").toString();
		String project_no = session.getAttribute("project_no").toString();
		
		String assignee = request.getParameter("assignee");
		System.out.println("추가할 할당멤버 : " + assignee);
		
		boolean bool = boardService.addAssignee(task_no, assignee, project_no); 
		
		if(bool == true ) {
			System.out.println("사용자 추가 완료");
		} else {
			System.out.println("없는 사용자입니다");
		}
		
		return bool+"";
				
	}
	

    // task 삭제
    @RequestMapping(value="deleteTask.do", method=RequestMethod.GET)
    public String deleteTask(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
    	
    	String task_no = session.getAttribute("task_no").toString();
    	System.out.println("삭제할 task_no : " + task_no);
    	
    	taskService.deleteTask(task_no);
    	
    	return "redirect:board.do";
    }
	
	

	
	
	
	
	/*// 수정된 배경이미지 db에 업데이트
			@RequestMapping(value="/modifyBackgroundImage.do", method=RequestMethod.POST)
			public ModelAndView modifyBackgroundImage(MultipartFile file, HttpSession session) throws Exception {
				
				//System.out.println("배경이미지 POST로 넘기기 - controller 시작");
				
				MainBackgroundVO mainBackgroundVO = new MainBackgroundVO();
				
				// 세션객체 얻어오기
		        String login_email = session.getAttribute("login_email").toString();
		        
		        // 원본이름 저장
				String save_name = file.getOriginalFilename();
				System.out.println("save name : " + save_name);
				
				// 랜덤생성 + 파일이름 저장
				// 파일병 랜덤생성 메소드 호출
				save_name = uploadFile(save_name, file.getBytes());
				mainBackgroundVO.setLogin_email(login_email);
				mainBackgroundVO.setOri_name(file.getOriginalFilename());
				mainBackgroundVO.setSave_name(save_name);
				
				// 코멘트 세션에 추가 - 필요 시 추가
				session.setAttribute("mainBackgroundVO", mainBackgroundVO);
				System.out.println("세션에 올린 배경이미지 : " + mainBackgroundVO);
				
				// 배경이미지 내용 db에 추가
				mainService.modifyBackgroundImage(mainBackgroundVO);
				
				ModelAndView mav = new ModelAndView("uploadCome");
				
				return mav;
				
			}
			
			 // 파일명 랜덤생성 메서드 - 이미지 업로드 전용
		    private String uploadFile(String originalName, byte[] fileData) throws Exception{
		        // uuid 생성(Universal Unique IDentifier, 범용 고유 식별자)
		        UUID uuid = UUID.randomUUID();
		        // 랜덤생성+파일이름 저장
		        String savedName = uuid.toString()+"_"+originalName;
		        File target = new File(uploadPath, savedName);
		        // 임시디렉토리에 저장된 업로드된 파일을 지정된 디렉토리로 복사
		        // FileCopyUtils.copy(바이트배열, 파일객체)
		        FileCopyUtils.copy(fileData, target);
		        
		        return savedName;
		    }
			
		 // 회원 마이프로필 삭제
		    @RequestMapping(value="/backgroundDelete.do")
		    public String fileDelete(MainBackgroundVO mainBackroundVO, HttpSession session) {
		       
		    	// 세션객체 얻어오기
		       String login_email = session.getAttribute("login_email").toString();
		       MainBackgroundVO mainBackgroundVO = mainService.findBackgroundImage(login_email);
		       
		       String path = "";
		       String profile = "/" + mainBackgroundVO.getSave_name();
		       path = uploadPath + profile;
		       System.out.println("삭제할 파일 경로 : " + path);
		       File file = new File(path);
		          if(file.exists() == true){
		             file.delete();
		          }
		          
		       // 배경이미지 삭제
		       mainService.deleteBackgroundImage(login_email);
		       mainBackgroundVO = mainService.findBackgroundImage(login_email);
		       session.setAttribute("mainBackgroundVO", mainBackgroundVO);
		       System.out.println("배경이미지 삭제 확인 : " + mainBackgroundVO);

		       return "backgroundDelete";

		      }*/
	
	
	
	 // 파일명 랜덤생성 메서드 - 업로드 전용
    private String uploadFile(String originalName, byte[] fileData) throws Exception{
        // uuid 생성(Universal Unique IDentifier, 범용 고유 식별자)
        UUID uuid = UUID.randomUUID();
        // 랜덤생성+파일이름 저장
        String savedName = uuid.toString()+"_"+originalName;
        File target = new File(uploadPath, savedName);
        // 임시디렉토리에 저장된 업로드된 파일을 지정된 디렉토리로 복사
        // FileCopyUtils.copy(바이트배열, 파일객체)
        FileCopyUtils.copy(fileData, target);
        
        return savedName;
    }
	
}
	
	
	
