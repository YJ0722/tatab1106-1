package com.bit.tatab.login.service;

import java.util.List;

import com.bit.tatab.login.vo.LoginVO;
import com.bit.tatab.main.vo.CommentVO;
import com.bit.tatab.main.vo.MyPageVO;
import com.bit.tatab.myPage.vo.ProfileImgVO;

public interface LoginService {

	// 로그인 info db에 존재하는지 확인(검색)
	public List<LoginVO> memberInfoFind(LoginVO loginVO);
	
	// 로그인 info db에  추가
 	public void memberInfoInsert(LoginVO loginVO);
 	
 	// 코멘트 관련 info db에 존재하는지 확인(검색)
 	public CommentVO mainCommentFind(LoginVO loginVO);
 	
 	// 코멘트 관련 info db에 추가
 	public void mainCommentInsert(CommentVO commentVO);

 	// 마이페이지 관련 info db에 존재하는지 확인(검색)
	public MyPageVO myPageInfoFind(LoginVO loginVO);

	// 마이페이지 관련 info db에 추가
	public void myPageInfoInsert(MyPageVO myPageVO2);

	// 마이페이지 사진 indo db에 존재하는지 확인검색) - vo버전
	public ProfileImgVO profileImgFind(LoginVO loginVO);
	
	// 마이페이지 사진 indo db에 존재하는지 확인검색) - String버전
	public ProfileImgVO profileImgFind(String login_email);
}
