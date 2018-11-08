package com.bit.tatab.myPage.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bit.tatab.main.dao.MainDAO;
import com.bit.tatab.main.vo.MyPageVO;
import com.bit.tatab.myPage.dao.MyPageDAO;
import com.bit.tatab.myPage.vo.ProfileImgVO;



@Service
public class MyPageServiceImpl implements MyPageService{

	@Inject
	private MyPageDAO myPageDAO;

	@Override
	public void modifyMyPage(MyPageVO myPageVO) {
		myPageDAO.modifyMyPage(myPageVO);
	}

	@Override
	public void modifyProfileImg(ProfileImgVO profileImgVO) {
		myPageDAO.modifyProfileImg(profileImgVO);
	}

	@Override
	public void deleteProfileImg(String login_email) {
		myPageDAO.deleteProfileImg(login_email);
	}
	
	

	
	
 
	

 
	
	
}
