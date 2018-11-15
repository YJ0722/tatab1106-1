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
	// 닉네임 정보 가져오기
	public String getLoginName(String login_email) {
		return sqlSession.selectOne("getLoginName", login_email);
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
