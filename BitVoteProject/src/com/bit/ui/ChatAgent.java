package com.bit.ui;

import java.net.Socket;
import java.util.List;

import com.bit.dao.UserDao.IdSearchFailException;
import com.bit.dao.UserDao.NoMachingPwdException;
import com.bit.dao.VoteDao.NotFoundVoteException;
import com.bit.domain.Member;
import com.bit.domain.Vote;
import com.bit.domain.VoteChoice;
import com.bit.service.VoteService;
public class ChatAgent extends AbstractAgent {
	Member user;
	List<Vote> voteList;//현재 사용자가 투표할 수 있는 정보들
	List<VoteChoice> choiceList;

	public ChatAgent(Socket socket) throws Exception {
		super(socket);
	}

	@Override
	protected void execute() throws Exception {
		String msg=readMsg();
		VoteMsg voteMsg= new VoteMsg(msg);//메시지를 읽어서 분할한다.

		switch(voteMsg.id){
		case "F1":
			try{
				user=new Member(voteMsg.content1, voteMsg.content2);
				VoteService.INSTANCE.loginUser(user);
				writeMsg("로그인이 완료되었습니다");

				printVoteList();

			}catch(IdSearchFailException e1){//로그인실패시 경고메시지
				writeMsg(e1.getMessage());
			}catch(NoMachingPwdException e2){
				writeMsg(e2.getMessage());
			}catch(NotFoundVoteException e3){
				writeMsg(e3.getMessage());
			}
			break;
		case "F2"://특정 투표를 선택한경우
			//몇번문항을 선택했는지 읽어와서 그 항목에 맞는 목록을 가지고옴.
			//투표 내용을 선택할때마다 그 투표에 해당하는 항목이 바뀜.
			choiceList=VoteService.INSTANCE.getChoiceList(voteList.get(Integer.parseInt(voteMsg.content1)));
			for (int i = 0; i < choiceList.size(); i++) {
				writeMsg(choiceList.get(i).toString());
			}
			break;
		case "F3"://투표에 응답을 한경우			
			System.out.println(choiceList.get(Integer.parseInt(voteMsg.content2)));
			VoteService.INSTANCE.inputVote(user,voteList.get(Integer.parseInt(voteMsg.content1)), choiceList.get(Integer.parseInt(voteMsg.content2)-1));
			printVoteList();//유저가 투표를 하고 나면 갱신을 해줌.
			break;

		}
	}

	private void printVoteList() throws Exception{
		try{
			voteList=VoteService.INSTANCE.getVoteList(user);
			String msg="";
			for (int i = 0; i < voteList.size(); i++) {
				msg+=i+"번째 "+voteList.get(i)+"\n";
			}
			System.out.println(msg);
			writeMsg(msg);
		}catch(NotFoundVoteException e){
			writeMsg(e.getMessage());
		}

	}
}
