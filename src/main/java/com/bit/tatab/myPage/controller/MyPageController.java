package com.bit.tatab.myPage.controller;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bit.tatab.login.service.LoginService;
import com.bit.tatab.main.vo.MainBackgroundVO;
import com.bit.tatab.main.vo.MyPageVO;
import com.bit.tatab.myPage.service.MyPageService;
import com.bit.tatab.myPage.vo.ProfileImgVO;

@Controller
public class MyPageController {

	@Inject
	MyPageService myPageService;

	@Inject
	LoginService loginService;

	@Resource(name = "uploadPath")
	String uploadPath;

	// GET 방식으로 호출되는 myPage 페이지
	@RequestMapping(value = "/myPage.do", method = RequestMethod.GET)
	public ModelAndView userMain(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mav = new ModelAndView("myPage");

		// 세션객체 얻어오기 - 이미지
		HttpSession session = request.getSession();
		ProfileImgVO profileImgVO = (ProfileImgVO) session.getAttribute("profileImgVO");
		System.out.println("마이페이지 - 세션에서 가져온 프로필이미지 : " + profileImgVO);

		mav.addObject("profileImgVO", profileImgVO);

		return mav;

	}

	// 수정된 코멘트 info db에 업데이트
	@RequestMapping(value = "/modifyMyPage.do", method = RequestMethod.POST)
	public ModelAndView modifyMyPage(HttpServletRequest request, HttpServletResponse response, MultipartFile file)
			throws Exception {

		System.out.println("마이페이지 수정 POST로 넘기기 - controller 시작");

		// 세션객체 얻어오기
		HttpSession session = request.getSession();
		String login_email = session.getAttribute("login_email").toString();
		String login_name = session.getAttribute("login_name").toString();

		// 코멘트 프론트에서 가져오기 - 별명, 모토, 학과/부서, 생년월일, 집주소, 전화번호
		String nickname = request.getParameter("nickname");
		String dob = request.getParameter("dob");
		String motto = request.getParameter("motto");
		String department = request.getParameter("department");
		String gender = "성별";
		String address = request.getParameter("address");
		String phone_number = request.getParameter("phone_number");

		// 이미지 가져오기
		// 원본이름 저장
		String save_name = file.getOriginalFilename();
		System.out.println("save_name : " + save_name);

		// 파일명 랜덤생성 메소트 호출

		ProfileImgVO profileImgVO = null;
		if (save_name != null && save_name.length() > 0) {
			save_name = uploadFile(save_name, file.getBytes());
			profileImgVO = new ProfileImgVO();
			profileImgVO.setLogin_email(login_email);
			profileImgVO.setOri_name(file.getOriginalFilename());
			profileImgVO.setSave_name(save_name);
		}

		////////////////////////////////////////////////////////////

		MyPageVO myPageVO = new MyPageVO(login_email, login_name, nickname, dob, motto, department, gender, address,
				phone_number);

		// 코멘트 세션에 추가 - 이메일, 이름, 별명, 모토, 학과/부서, 생년월일, 집주소, 전화번호
		session.setAttribute("myPageVO", myPageVO);

		// 이미지 세션에 추가
		session.setAttribute("profileImgVO", profileImgVO);
		System.out.println("세션에 올린 프로필 이미지 : " + profileImgVO);

		// 코멘트 기입 내용 db에 추가
		myPageService.modifyMyPage(myPageVO);

		// 이미지 내용 db에 추가
		if(profileImgVO != null)
			myPageService.modifyProfileImg(profileImgVO);

		System.out.println("db 등록 완료!");

		ModelAndView mav = new ModelAndView("uploadCome"); // userMain 공유영역에 잘 안 올라가면 이 부분 "redirect:~~"로 바꿀 것!
		mav.addObject("myPageVO", myPageVO); // mav 형식으로 공유영역에 올리는 방법!
		mav.addObject("profileImgVO", profileImgVO);

		return mav;

	}

	// 파일명 랜덤생성 메서드 - 이미지 업로드 전용
	private String uploadFile(String originalName, byte[] fileData) throws Exception {
		// uuid 생성(Universal Unique IDentifier, 범용 고유 식별자)
		UUID uuid = UUID.randomUUID();
		// 랜덤생성+파일이름 저장
		String savedName = uuid.toString() + "_" + originalName;
		File target = new File(uploadPath, savedName);
		// 임시디렉토리에 저장된 업로드된 파일을 지정된 디렉토리로 복사
		// FileCopyUtils.copy(바이트배열, 파일객체)
		FileCopyUtils.copy(fileData, target);

		return savedName;
	}

	// 마이페이지 프로필 삭제
	@RequestMapping(value = "/profileImgDelete.do")
	public String fileDelete(MainBackgroundVO mainBackroundVO, HttpSession session) {

		// 세션객체 얻어오기
		String login_email = session.getAttribute("login_email").toString();
		ProfileImgVO profileImgVO = loginService.profileImgFind(login_email);

		String path = "";
		String profile = "/" + profileImgVO.getSave_name();
		path = uploadPath + profile;
		System.out.println("삭제할 파일 경로 : " + path);
		File file = new File(path);
		if (file.exists() == true) {
			file.delete();
		}

		// 배경이미지 삭제
		myPageService.deleteProfileImg(login_email);
		profileImgVO = loginService.profileImgFind(login_email);
		session.setAttribute("profileImgVO", profileImgVO);
		System.out.println("프로필이미지 삭제 확인 : " + profileImgVO);

		return "profileDelete";

	}

}
