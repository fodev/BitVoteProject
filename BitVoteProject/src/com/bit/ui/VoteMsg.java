package com.bit.ui;

public class VoteMsg {		
	String id;
	String content1;
	String content2;
	
	public VoteMsg(String msg){
		String[] arr = msg.split(":");

		this.id = arr[0];
		this.content1 = arr[1];
		if(arr.length==3)
			this.content2 = arr[2];
	}

}
