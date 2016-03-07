package com.bit.ui;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;


public abstract class MainServer {

	protected ServerSocket serverSocket;
	protected List<AbstractAgent> agentList;//접속하는 객체에 대한 리스트를 관리.
	
	
	protected MainServer()throws Exception{
		this.agentList = new Vector<>();
		init();
	}
	
	public void init()throws Exception{
		
		this.serverSocket = new ServerSocket(5555);
		System.out.println( "server ready..." );
	}
	
	public void openServer()throws Exception{
		
		while(true){
			Socket socket = serverSocket.accept();//계속 접근을 기다리면서 
			System.out.println("conntected..." );
			AbstractAgent agent= getAgent(socket);//접근된 클라이언트와 통실할 수 있는 agent객체를 만든다.
			agentList.add(agent);
			agent.start();
			
		}
	}
	
	protected abstract AbstractAgent getAgent(Socket socket)throws Exception;
}




