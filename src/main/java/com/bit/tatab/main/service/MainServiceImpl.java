package com.bit.tatab.main.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bit.tatab.board.dao.BoardDAO;
import com.bit.tatab.main.dao.MainDAO;
import com.bit.tatab.main.vo.CommentVO;
import com.bit.tatab.main.vo.MainBackgroundVO;
import com.bit.tatab.main.vo.ProjectVO;



@Service
public class MainServiceImpl implements MainService{

	@Inject
	private MainDAO mainDao;

	@Inject
	private BoardDAO boardDao;
	
	// 프로젝트 생성
	@Override
	public void insert(ProjectVO project, String login_email) {
		mainDao.insert(project);
		mainDao.insertMember(project, login_email);
		System.out.println("before col:"+project.getProject_no());
		mainDao.makeFirstCol1(project);
		System.out.println("after col:"+project.getProject_no());
	}

	// 해당 id가 속한 프로젝트 리스트 불러오기
	@Override
	public List<ProjectVO> selectAllProject(String login_email) {
		List<ProjectVO> projectList = mainDao.selectAllProject(login_email);
		return projectList;
	}

	@Override
	public void modifyComment(CommentVO commentVO) {
		// TODO Auto-generated method stub
		mainDao.modifyComment(commentVO);
	}

	@Override
	public void modifyBackgroundImage(MainBackgroundVO mainBackgroundVO) {
		mainDao.modifyBackgroundImage(mainBackgroundVO);
		
	}

	@Override
	public MainBackgroundVO findBackgroundImage(String login_email) {
		MainBackgroundVO backgroundImage = mainDao.findBackgroundImage(login_email);
		return backgroundImage;
	}

	@Override
	public void deleteBackroundImage(String login_email) {
		mainDao.deleteBackroundImage(login_email);
	}
	
	
	
	
 
	

 
	
	
}
