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
		return "力格 : " + content + "  累己老 : "  + regdate + "   付皑老 : " + lastdate;
	}
	
	public String getVoidId(){
		return voteid;
	}
}
