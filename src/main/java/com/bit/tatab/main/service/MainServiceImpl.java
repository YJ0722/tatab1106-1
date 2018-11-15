package com.bit.tatab.main.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bit.tatab.board.dao.BoardDAO;
import com.bit.tatab.main.dao.MainDAO;
import com.bit.tatab.main.vo.ActivityVO;
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
	public void deleteBackgroundImage(String login_email) {
		mainDao.deleteBackgroundImage(login_email);
	}

	// 액티비티 리스트 불러오기(유저메인)
	@Override
	public List<ActivityVO> selectActivityList(String login_email) {
		List<ActivityVO> activityList = mainDao.selectActivityList(login_email);
		for(ActivityVO a: activityList) {
//			String begin = a.getAlert_time()+" "+a.getAlert_time().getHours()+":"+a.getAlert_time().getMinutes();
			a.setDiffMin(diffOfMin(a.getAlert_time(),new Date()));
		}
		return activityList;
	}
	
	public static long diffOfMin(Date begin, Date end) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		long result=0;
		try {
			System.out.println("begin:"+begin);
			System.out.println("end:"+end);
			Date dateBegin = formatter.parse(formatter.format(begin));
			Date dateEnd = formatter.parse(formatter.format(end));
			
			result = dateEnd.getTime() - dateBegin.getTime();
			result = result/(1000*60);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;		
	}
	
	
}
