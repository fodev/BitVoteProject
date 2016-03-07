package com.bit.ui;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;


public abstract class MainServer {

	protected ServerSocket serverSocket;
	protected List<AbstractAgent> agentList;//�����ϴ� ��ü�� ���� ����Ʈ�� ����.
	
	
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
			Socket socket = serverSocket.accept();//��� ������ ��ٸ��鼭 
			System.out.println("conntected..." );
			AbstractAgent agent= getAgent(socket);//���ٵ� Ŭ���̾�Ʈ�� ����� �� �ִ� agent��ü�� �����.
			agentList.add(agent);
			agent.start();
			
		}
	}
	
	protected abstract AbstractAgent getAgent(Socket socket)throws Exception;
}




