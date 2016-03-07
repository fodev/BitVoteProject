package com.bit.dao;

import java.sql.ResultSet;

import com.bit.domain.Member;

public class UserDao {
	public void loginUser(Member user) throws Exception{
		new AbstractVote() {
			@Override
			protected void excute() throws Exception{
				// TODO Auto-generated method stub
				String sql = "select userpw, roomid from tbl_member where userid=?";

						
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, user.getUid());
				
				rs=pstmt.executeQuery();
								
				String pw="";
				if(!rs.next()){
					throw new IdSearchFailException();//���̵� �������� �ʴ°��
				}
				
				pw=rs.getString(1);
				if(!user.getUpw().equals(pw)){//���̵�� �����ϴµ� �н����尡 ��ġ ���� �ʴ� ���
					throw new NoMachingPwdException();
				}
				
				user.setRoom(rs.getString(2));
			}
		}.excuteJob();
	}
	
	public class NoMachingPwdException extends Exception{
		public NoMachingPwdException() {
			// TODO Auto-generated constructor stub
			super("�н����� ��ġ���� ����");
		}
	}
	
	public class IdSearchFailException extends Exception{
		public IdSearchFailException() {
			// TODO Auto-generated constructor stub
			super("���̵� �������� ����");
		}
	}
}
