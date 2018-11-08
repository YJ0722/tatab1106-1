package com.bit.tatab.main.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bit.tatab.board.service.BoardService;
import com.bit.tatab.login.service.LoginService;
import com.bit.tatab.login.vo.LoginVO;
import com.bit.tatab.main.service.MainService;
import com.bit.tatab.main.vo.CommentVO;
import com.bit.tatab.main.vo.MainBackgroundVO;
import com.bit.tatab.main.vo.ProjectVO;
import com.bit.tatab.myPage.vo.ProfileImgVO;


@Controller
public class MainController {

	@Inject
	MainService mainService;
	
	@Inject
	LoginService loginService;
	
	@Inject
	BoardService boardService;
	
	@Resource(name = "uploadPath")
	   String uploadPath;

	// GET 방식으로 호출되는 userMain 페이지
	@RequestMapping(value="/userMain.do", method=RequestMethod.GET)
	public ModelAndView userMain(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		
		//System.out.println("!!!!!");
		
		// 세션객체 얻어오기 - 이메일
        HttpSession session = request.getSession();
        String login_email = session.getAttribute("login_email").toString();
        
        // db에 있는 코멘트들 가져오기
 		String mainTitle = session.getAttribute("main_title").toString();
 		String subTitle = session.getAttribute("sub_title").toString();
 		String subComment = session.getAttribute("sub_comment").toString();
 		CommentVO commentVO = new CommentVO(login_email, mainTitle, subTitle, subComment);
        
		ModelAndView mav = new ModelAndView("userMain");
		
		// project list 불러오기
		List<ProjectVO> projectList = mainService.selectAllProject(login_email);
	
		// 세션객체 얻어오기 - 파일
		MainBackgroundVO mainBackgroundVO = (MainBackgroundVO) session.getAttribute("mainBackgroundVO");
		System.out.println("세션에서 가져온 배경이미지 : " + mainBackgroundVO);
		
		// 세션객체 얻어오기 - 이미지
		ProfileImgVO profileImgVO = (ProfileImgVO) session.getAttribute("profileImgVO");
		System.out.println("세션에서 가져온 프로필이미지 : " + profileImgVO);
		
		mav.addObject("projectList", projectList);
		mav.addObject("commentVO", commentVO);
		mav.addObject("mainBackgroundVO", mainBackgroundVO);
		mav.addObject("profileImgVO", profileImgVO);
		
		return mav;
		
	}
	
	
	// POST 방식으로 호출되는 userMain 페이지
	@RequestMapping(value="/userMain.do", method=RequestMethod.POST)
	public ModelAndView userMain(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginVO loginvo) throws Exception{
	
		//System.out.println("@@@@@");
		ModelAndView mav = new ModelAndView("userMain");
		return mav;
		
	}

	// 새 프로젝트 db에 추가
	@RequestMapping(value = "/register.do", method = RequestMethod.POST)
	public String register(HttpServletRequest request, HttpServletResponse resopnse, @Valid ProjectVO projectVO) {

		System.out.println("새 프로젝트 db에 추가------");
		HttpSession session = request.getSession();
		String login_email = session.getAttribute("login_email").toString();

		mainService.insert(projectVO, login_email);
		
		System.out.println("컨트롤러에서 project_no 확인 : " + String.valueOf(projectVO.getProject_no()));

		// 프로젝트 생성 시 자동으로 생성되는 컬럼 1개 생성
		//boardService.makeFirstCol(projectVO.getProject_no());
		
		return "redirect:/userMain.do";
	}

	
	// 수정된 코멘트 info db에 업데이트
	@RequestMapping(value="/modifyComment.do", method=RequestMethod.POST)
	public ModelAndView modifyComment(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//System.out.println("메인 코멘트 POST로 넘기기 - controller 시작");
		
		// 세션객체 얻어오기
        HttpSession session = request.getSession();
        String login_email = session.getAttribute("login_email").toString();
		
		// 코멘트 프론트에서 가져오기
		String mainTitle = request.getParameter("mainTitle");
		String subTitle = request.getParameter("subTitle");
		String subComment = request.getParameter("subComment");
		
		CommentVO commentVO = new CommentVO(login_email, mainTitle, subTitle, subComment);
		
		// 코멘트 세션에 추가
		session.setAttribute("main_title", commentVO.getMain_title());
		session.setAttribute("sub_title", commentVO.getSub_title());
		session.setAttribute("sub_comment", commentVO.getSub_comment());
		
		// 코멘트 기입 내용 db에 추가
		mainService.modifyComment(commentVO);
		
		//System.out.println("db 등록 완료!");
		
		ModelAndView mav = new ModelAndView("redirect:/userMain.do");
		mav.addObject("commentVO", commentVO); // mav 형식으로 공유영역에 올리는 방법!
		
		return mav;
		
	}
	
	// 수정된 배경이미지 db에 업데이트
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

	      }
		
		
}
