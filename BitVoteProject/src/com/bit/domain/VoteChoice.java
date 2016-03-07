package com.bit.domain;

public class VoteChoice {
	int num;
	//String voteid;
	String content;
	
	public VoteChoice(int num, String content) {
		super();
		this.num = num;
		this.content = content;
	}

	@Override
	public String toString() {
		return "VoteChoice [num=" + num + ", content=" + content + "]";
	}
	
	public int getNum(){
		return num;
	}
	
}
