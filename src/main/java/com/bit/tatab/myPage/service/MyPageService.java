package com.bit.tatab.myPage.service;

import com.bit.tatab.main.vo.MyPageVO;
import com.bit.tatab.myPage.vo.ProfileImgVO;

public interface MyPageService {
	
	// 새 코멘트 info db에서 수정
	public void modifyMyPage(MyPageVO myPageVO);
	
	// 이름 정보 가져오기
	public String getLoginName(String login_email);

	// 프로필 이미지 info db에서 수정
	public void modifyProfileImg(ProfileImgVO profileImgVO);

	// 프로필 이미지 삭제
	public void deleteProfileImg(String login_email);
	
}
