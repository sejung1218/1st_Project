package com.tj.ex.dto;

import java.sql.Date;

public class PeopleDto {

	private int pNum;
	private String mId;
	private String pTitle;
	private String pContent;
	private String pFilename;
	private int pReadcount;
	private String pPw;
	private Date pDate;

	public PeopleDto() {}

	public PeopleDto(int pNum, String mId, String pTitle, String pContent, String pFilename, int pReadcount, String pPw,
			Date pDate) {
		this.pNum = pNum;
		this.mId = mId;
		this.pTitle = pTitle;
		this.pContent = pContent;
		this.pFilename = pFilename;
		this.pReadcount = pReadcount;
		this.pPw = pPw;
		this.pDate = pDate;
	}

	public int getpNum() {
		return pNum;
	}

	public void setpNum(int pNum) {
		this.pNum = pNum;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getpTitle() {
		return pTitle;
	}

	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}

	public String getpContent() {
		return pContent;
	}

	public void setpContent(String pContent) {
		this.pContent = pContent;
	}

	public String getpFilename() {
		return pFilename;
	}

	public void setpFilename(String pFilename) {
		this.pFilename = pFilename;
	}

	public int getpReadcount() {
		return pReadcount;
	}

	public void setpReadcount(int pReadcount) {
		this.pReadcount = pReadcount;
	}

	public String getpPw() {
		return pPw;
	}

	public void setpPw(String pPw) {
		this.pPw = pPw;
	}

	public Date getpDate() {
		return pDate;
	}

	public void setpDate(Date pDate) {
		this.pDate = pDate;
	}

	@Override
	public String toString() {
		return "PeopleDto [pNum=" + pNum + ", mId=" + mId + ", pTitle=" + pTitle + ", pContent=" + pContent
				+ ", pFilename=" + pFilename + ", pReadcount=" + pReadcount + ", pPw=" + pPw + ", pDate=" + pDate + "]";
	}

}
