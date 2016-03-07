package com.bit.domain;

public class Vote {
	String voteid;
	String content;
	String regdate;
	String roomid;
	String lastdate;
	
	public Vote(String voteid, String content, String regdate, String roomid, String lastdate) {
		super();
		this.voteid = voteid;
		this.content = content;
		this.regdate = regdate;
		this.roomid = roomid;
		this.lastdate = lastdate;
	}

	@Override
	public String toString() {
		return "Vote [voteid=" + voteid + ", content=" + content + ", regdate=" + regdate + ", roomid=" + roomid
				+ ", lastdate=" + lastdate + "]";
	}
	
	public String getVoidId(){
		return voteid;
	}
}
