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
					throw new IdSearchFailException();//아이디가 존재하지 않는경우
				}
				
				pw=rs.getString(1);
				if(!user.getUpw().equals(pw)){//아이디는 존재하는데 패스워드가 일치 하지 않는 경우
					throw new NoMachingPwdException();
				}
				
				user.setRoom(rs.getString(2));
			}
		}.excuteJob();
	}
	
	public class NoMachingPwdException extends Exception{
		public NoMachingPwdException() {
			// TODO Auto-generated constructor stub
			super("패스워드 일치하지 않음");
		}
	}
	
	public class IdSearchFailException extends Exception{
		public IdSearchFailException() {
			// TODO Auto-generated constructor stub
			super("아이디가 존재하지 않음");
		}
	}
}
