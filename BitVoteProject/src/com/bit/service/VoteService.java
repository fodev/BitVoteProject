package com.bit.service;

import java.util.List;

import com.bit.dao.UserDao;
import com.bit.dao.VoteDao;
import com.bit.domain.Member;
import com.bit.domain.Vote;
import com.bit.domain.VoteChoice;

public enum VoteService {
	INSTANCE();
	private VoteService(){
		
	}
	
	public void loginUser(Member user) throws Exception{
		UserDao userDao=new UserDao();
		userDao.loginUser(user);//로그인이 정상적으로 종료가 되면
	}
	
	public List<Vote> getVoteList(Member user) throws Exception{//유저가 투표할 수 있는 리스트를 알려줌.
		VoteDao voteDao= new VoteDao();
		List<Vote> list=voteDao.getVoteList(user);
		return list;
	}
	
	public List<VoteChoice> getChoiceList(Vote vote) throws Exception{
		VoteDao voteDao= new VoteDao();
		return voteDao.getChoiceList(vote);
	}
	
	public void inputVote(Member user, Vote vote, VoteChoice choice) throws Exception{
		VoteDao voteDao=new VoteDao();
		voteDao.excuteVote(user, vote, choice);
	}
}
