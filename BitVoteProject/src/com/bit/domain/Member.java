package com.bit.domain;

public class Member {
	String uid;
	String upw;
	String room;
	
	
	public Member(String uid, String upw) {
		super();
		this.uid = uid;
		this.upw = upw;
	}

	public String getUpw(){
		return upw;
	}
	
	public String getUid(){
		return uid;
	}
	
	public String getRoom(){
		return room;
	}
	public void setRoom(String room){
		this.room=room;
	}
}
