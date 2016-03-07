package com.bit.ui;

import java.net.Socket;


public class ChatServer extends MainServer {
	
	private static ChatServer instance;
	
	static{
		try {
			instance = new ChatServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static final ChatServer getInstance(){
		return instance;
	}

	protected ChatServer() throws Exception {
		super();
	}

	@Override
	protected AbstractAgent getAgent(Socket socket) throws Exception{
		// TODO Auto-generated method stub
		return new ChatAgent(socket);
	}
	
}
