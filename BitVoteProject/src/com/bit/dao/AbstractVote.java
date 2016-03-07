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
			connect();
			excute();
		}catch(Exception e){
			throw e;
		}finally{
			closeAll();
		}
	}

	protected abstract void excute() throws Exception;

	private void connect() throws Exception{
		con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.34:1521:xe", "user162", "user162");

	}

	private void closeAll(){
		if(rs!=null){try{rs.close();}catch(Exception e){}}
		if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
		if(con!=null){try{con.close();}catch(Exception e){}}
	}
}
