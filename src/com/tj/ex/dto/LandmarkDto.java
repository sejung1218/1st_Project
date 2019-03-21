package com.tj.ex.dto;

import java.sql.Date;

public class LandmarkDto {

	private int lNum;
	private String mId;
	private String lTitle;
	private String lContent;
	private String lFilename;
	private int lReadcount;
	private String lPw;
	private Date lDate;

	public LandmarkDto() {}

	public LandmarkDto(int lNum, String mId, String lTitle, String lContent, String lFilename, int lReadcount,
			String lPw, Date lDate) {
		this.lNum = lNum;
		this.mId = mId;
		this.lTitle = lTitle;
		this.lContent = lContent;
		this.lFilename = lFilename;
		this.lReadcount = lReadcount;
		this.lPw = lPw;
		this.lDate = lDate;
	}

	public int getlNum() {
		return lNum;
	}

	public void setlNum(int lNum) {
		this.lNum = lNum;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getlTitle() {
		return lTitle;
	}

	public void setlTitle(String lTitle) {
		this.lTitle = lTitle;
	}

	public String getlContent() {
		return lContent;
	}

	public void setlContent(String lContent) {
		this.lContent = lContent;
	}

	public String getlFilename() {
		return lFilename;
	}

	public void setlFilename(String lFilename) {
		this.lFilename = lFilename;
	}

	public int getlReadcount() {
		return lReadcount;
	}

	public void setlReadcount(int lReadcount) {
		this.lReadcount = lReadcount;
	}

	public String getlPw() {
		return lPw;
	}

	public void setlPw(String lPw) {
		this.lPw = lPw;
	}

	public Date getlDate() {
		return lDate;
	}

	public void setlDate(Date lDate) {
		this.lDate = lDate;
	}

	@Override
	public String toString() {
		return "LandmarkDto [lNum=" + lNum + ", mId=" + mId + ", lTitle=" + lTitle + ", lContent=" + lContent
				+ ", lFilename=" + lFilename + ", lReadcount=" + lReadcount + ", lPw=" + lPw + ", lDate=" + lDate + "]";
	}

}
