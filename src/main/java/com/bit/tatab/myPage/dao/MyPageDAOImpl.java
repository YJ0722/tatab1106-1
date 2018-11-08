package com.bit.tatab.myPage.dao;


import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bit.tatab.main.vo.MyPageVO;
import com.bit.tatab.myPage.vo.ProfileImgVO;


@Repository
public class MyPageDAOImpl implements MyPageDAO {

	@Inject
	private SqlSession sqlSession;

	@Override
	public void modifyMyPage(MyPageVO myPageVO) {
		sqlSession.update("modifyMyPage", myPageVO);
	}

	@Override
	public void modifyProfileImg(ProfileImgVO profileImgVO) {
		sqlSession.update("modifyProfileImg", profileImgVO);
	}

	@Override
	public void deleteProfileImg(String login_email) {
		sqlSession.delete("deleteProfileImg", login_email);
	}
	
	
	
	

}
