package com.bit.tatab.login.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.tatab.login.dao.LoginDAO;
import com.bit.tatab.login.vo.LoginVO;
import com.bit.tatab.main.vo.CommentVO;
import com.bit.tatab.main.vo.MyPageVO;
import com.bit.tatab.myPage.vo.ProfileImgVO;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDAO loginDAO;

	// 로그인 info db에 존재하는지 확인(검색)
	@Override
	public List<LoginVO> memberInfoFind(LoginVO loginVO) {
		System.out.println("memberInfoFind 성공");
		return loginDAO.memberInfoFind(loginVO);
	}
	
	// 로그인 info db에  추가
	@Override
	public void memberInfoInsert(LoginVO loginVO) {
		loginDAO.memberInfoInsert(loginVO);
	}

	// 코멘트 관련 info db에 존재하는지 확인(검색)
	@Override
	public CommentVO mainCommentFind(LoginVO loginVO) {
		CommentVO comment = loginDAO.mainCommentFind(loginVO);
		return comment; 
	}

	// 코멘트 관련 info db에 추가
	@Override
	public void mainCommentInsert(CommentVO commentVO) {
		loginDAO.mainCommentInsert(commentVO);
	}

	// 마이페이지 관련 info db에 존재하는지 확인(검색)
	@Override
	public MyPageVO myPageInfoFind(LoginVO loginVO) {
		MyPageVO myPage = loginDAO.myPageInfoFind(loginVO);
		return myPage;
	}

	// 마이페이지 관련 info db에 추가
	@Override
	public void myPageInfoInsert(MyPageVO myPageVO) {
		loginDAO.myPageInfoInsert(myPageVO);
	}

	// 마이페이지 사진 info db에 존재하는지 확인(검색)
	@Override
	public ProfileImgVO profileImgFind(LoginVO loginVO) {
		ProfileImgVO profileImgVO = loginDAO.profileImgFind(loginVO);
		return profileImgVO;
	}

	@Override
	public ProfileImgVO profileImgFind(String login_email) {
		ProfileImgVO profileImgVO = loginDAO.profileImgFind(login_email);
		return profileImgVO;
	}

	// 마이페이지 사진 indo db에 존재하는지 확인검색) - String버전 + login_name
	@Override
	public ProfileImgVO profileImgFind2(String login_name) {
		ProfileImgVO profileImgVO = loginDAO.profileImgFind2(login_name);
		return profileImgVO;
	}
	
	
	

}
