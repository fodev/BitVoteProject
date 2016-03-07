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
	List<Vote> voteList;//���� ����ڰ� ��ǥ�� �� �ִ� ������
	List<VoteChoice> choiceList;

	public ChatAgent(Socket socket) throws Exception {
		super(socket);
	}

	@Override
	protected void execute() throws Exception {
		String msg=readMsg();
		VoteMsg voteMsg= new VoteMsg(msg);//�޽����� �о �����Ѵ�.

		switch(voteMsg.id){
		case "F1":
			try{
				user=new Member(voteMsg.content1, voteMsg.content2);
				VoteService.INSTANCE.loginUser(user);
				writeMsg("�α����� �Ϸ�Ǿ����ϴ�");

				printVoteList();

			}catch(IdSearchFailException e1){//�α��ν��н� ���޽���
				writeMsg(e1.getMessage());
			}catch(NoMachingPwdException e2){
				writeMsg(e2.getMessage());
			}catch(NotFoundVoteException e3){
				writeMsg(e3.getMessage());
			}
			break;
		case "F2"://Ư�� ��ǥ�� �����Ѱ��
			//��������� �����ߴ��� �о�ͼ� �� �׸� �´� ����� �������.
			//��ǥ ������ �����Ҷ����� �� ��ǥ�� �ش��ϴ� �׸��� �ٲ�.
			choiceList=VoteService.INSTANCE.getChoiceList(voteList.get(Integer.parseInt(voteMsg.content1)));
			for (int i = 0; i < choiceList.size(); i++) {
				writeMsg(choiceList.get(i).toString());
			}
			break;
		case "F3"://��ǥ�� ������ �Ѱ��			
			System.out.println(choiceList.get(Integer.parseInt(voteMsg.content2)));
			VoteService.INSTANCE.inputVote(user,voteList.get(Integer.parseInt(voteMsg.content1)), choiceList.get(Integer.parseInt(voteMsg.content2)-1));
			printVoteList();//������ ��ǥ�� �ϰ� ���� ������ ����.
			break;

		}
	}

	private void printVoteList() throws Exception{
		try{
			voteList=VoteService.INSTANCE.getVoteList(user);
			String msg="";
			for (int i = 0; i < voteList.size(); i++) {
				msg+=i+"��° "+voteList.get(i)+"\n";
			}
			System.out.println(msg);
			writeMsg(msg);
		}catch(NotFoundVoteException e){
			writeMsg(e.getMessage());
		}

	}
}
