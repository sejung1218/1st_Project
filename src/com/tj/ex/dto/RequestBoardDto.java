package com.tj.ex.dto;

import java.sql.Date;

public class RequestBoardDto {

	private int rNum;
	private String mId;
	private String rTitle;
	private String rContent;
	private int rReadcount;
	private String rPw;
	private int rGroup;
	private int rStep;
	private int rIndent;
	private Date rDate;

	public RequestBoardDto() {}

	public RequestBoardDto(int rNum, String mId, String rTitle, String rContent, int rReadcount, String rPw, int rGroup,
			int rStep, int rIndent, Date rDate) {
		this.rNum = rNum;
		this.mId = mId;
		this.rTitle = rTitle;
		this.rContent = rContent;
		this.rReadcount = rReadcount;
		this.rPw = rPw;
		this.rGroup = rGroup;
		this.rStep = rStep;
		this.rIndent = rIndent;
		this.rDate = rDate;
	}

	public int getrNum() {
		return rNum;
	}

	public void setrNum(int rNum) {
		this.rNum = rNum;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getrTitle() {
		return rTitle;
	}

	public void setrTitle(String rTitle) {
		this.rTitle = rTitle;
	}

	public String getrContent() {
		return rContent;
	}

	public void setrContent(String rContent) {
		this.rContent = rContent;
	}

	public int getrReadcount() {
		return rReadcount;
	}

	public void setrReadcount(int rReadcount) {
		this.rReadcount = rReadcount;
	}

	public String getrPw() {
		return rPw;
	}

	public void setrPw(String rPw) {
		this.rPw = rPw;
	}

	public int getrGroup() {
		return rGroup;
	}

	public void setrGroup(int rGroup) {
		this.rGroup = rGroup;
	}

	public int getrStep() {
		return rStep;
	}

	public void setrStep(int rStep) {
		this.rStep = rStep;
	}

	public int getrIndent() {
		return rIndent;
	}

	public void setrIndent(int rIndent) {
		this.rIndent = rIndent;
	}

	public Date getrDate() {
		return rDate;
	}

	public void setrDate(Date rDate) {
		this.rDate = rDate;
	}

	@Override
	public String toString() {
		return "RequestBoardDto [rNum=" + rNum + ", mId=" + mId + ", rTitle=" + rTitle + ", rContent=" + rContent
				+ ", rReadcount=" + rReadcount + ", rPw=" + rPw + ", rGroup=" + rGroup + ", rStep=" + rStep
				+ ", rIndent=" + rIndent + ", rDate=" + rDate + "]";
	}

}
