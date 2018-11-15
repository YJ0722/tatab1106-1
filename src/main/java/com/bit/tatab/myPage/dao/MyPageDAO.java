package com.bit.tatab.myPage.dao;

import com.bit.tatab.main.vo.MyPageVO;
import com.bit.tatab.myPage.vo.ProfileImgVO;

public interface MyPageDAO {
	
	// 새 코멘트 info db에서 수정
	public void modifyMyPage(MyPageVO myPageVO);

	// 닉네임 정보 가져오기
	public String getLoginName(String login_email);
	
	// 프로필 이미지 info db에서 수정
	public void modifyProfileImg(ProfileImgVO profileImgVO);

	// 프로필 이미지 db에서 삭제
	public void deleteProfileImg(String login_email);
	
}
