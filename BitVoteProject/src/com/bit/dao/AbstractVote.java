package com.bit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractVote {
	static{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");

		}catch(Exception e){	
		}
	}//static

	protected Connection con;
	protected PreparedStatement pstmt;
	protected ResultSet rs;

	public void excuteJob() throws Exception{
		try{
			connect();//오라클과 연결을 하고
			excute();//작업을 수행한 다음에 
		}catch(Exception e){
			throw e;
		}finally{
			closeAll();//종료한다.
		}
	}

	protected abstract void excute() throws Exception;

	private void connect() throws Exception{
		con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.4:1521:xe", "Hyun", "inside501463");

	}

	private void closeAll(){
		if(rs!=null){try{rs.close();}catch(Exception e){}}
		if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
		if(con!=null){try{con.close();}catch(Exception e){}}
	}
}
