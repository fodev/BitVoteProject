package com.bit.ui;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public abstract class AbstractAgent extends Thread {

	private Socket socket;
	private DataInputStream din;
	private DataOutputStream dos;
	
	public AbstractAgent(Socket socket)throws Exception{
		this.socket = socket;
		this.din = new DataInputStream(socket.getInputStream());
		this.dos = new DataOutputStream(socket.getOutputStream());
	}
	
	@Override
	public void run(){
		try{
		while(true){
			execute();
		}
		}catch(Exception e){
			
		}finally{
			closeAll();
		}
	}
	
	protected abstract void execute()throws Exception;
	
	public String readMsg()throws Exception{
		return din.readUTF();
	}
	
	public void writeMsg(String msg)throws Exception{
		dos.writeUTF(msg);
	}
	
	private void closeAll(){
		if(din != null){ try { din.close(); }catch(Exception e){}}
		if(dos != null){ try { dos.close(); }catch(Exception e){}}
		if(socket != null) { try { socket.close(); }catch(Exception e){}}
	}
	
}





