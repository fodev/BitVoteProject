package com.bit.dao;

import java.util.ArrayList;
import java.util.List;

import com.bit.domain.Member;
import com.bit.domain.Vote;
import com.bit.domain.VoteChoice;

public class VoteDao {
	public List<Vote> getVoteList(Member user) throws Exception{//���� �� �� �ִ� ��ǥ ����� ������ ���� �Լ�.
		StringBuilder str=new StringBuilder();
		str.append("select * from ");
		str.append("(");
		str.append("  select * from tbl_vote where roomid=?");
		str.append(") votelist2 ,");
		str.append("( ");
		str.append("  select * from  ");
		str.append("  ( ");
		str.append("    select voteid, count(*) cnt  ");
		str.append("    from tbl_vote_result  ");
		str.append("    where userid=?  ");
		str.append("    group by voteid ");
		str.append("  )  ");
		str.append(") votelist");
		str.append(" where (votelist.voteid(+)=votelist2.voteid) and (cnt<2 or cnt is NULL) ");

		List<Vote> list = new ArrayList<>();
		new AbstractVote() {
			
			@Override
			protected void excute() throws Exception {	
				//System.out.println(str.toString());
				pstmt=con.prepareStatement(str.toString());
				pstmt.setString(1, user.getRoom());
				pstmt.setString(2, user.getUid());
				
				rs=pstmt.executeQuery();
				
				while(rs.next()){
					list.add(new Vote(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
				}
				
			}
		}.excuteJob();
		return list;
	}
	
	public List<VoteChoice> getChoiceList(Vote vote) throws Exception{
		List<VoteChoice> list=new ArrayList<>();
		new AbstractVote() {
			
			@Override
			protected void excute() throws Exception {
				String sql = "select * from tbl_vote_choice	where voteid=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, vote.getVoidId());
				rs=pstmt.executeQuery();
				while(rs.next()){
					list.add(new VoteChoice(rs.getInt(1), rs.getString(3)));	
				}
				
			}
		}.excuteJob();
		return list;
	}
	
	public void excuteVote(Member user,Vote vote, VoteChoice choice) throws Exception{
		new AbstractVote() {
			
			@Override
			protected void excute() throws Exception {
				String sql= "insert into tbl_vote_result(result_num, userid, voteid, choice_num) values(seq_result_num.nextval, ?, ? ,?)";
				pstmt=con.prepareStatement(sql);//sql���� �غ��ϴ� �۾��� �Ѵ�.

				pstmt.setString(1,user.getUid());//������ 
				pstmt.setString(2,vote.getVoidId());//� ���׿� ���ؼ�
				pstmt.setInt(3,choice.getNum());//��� �亯�� �ߴ���
				int insertCnt=pstmt.executeUpdate();
				
				if(insertCnt!=1){//�μ�Ʈ �۾��� ���� ���Ѱ�� 
					throw new InsertFailException();
				}
	
			}
		}.excuteJob();
	}
	
	public class InsertFailException extends Exception{
		public InsertFailException() {
			super("Insert Fail...");
		}
	}
}
