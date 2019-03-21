package com.tj.ex.dto;

import java.sql.Date;

public class IllustDto {

	private int iNum;
	private String mId;
	private String iTitle;
	private String iContent;
	private String iFilename;
	private int iReadcount;
	private String iPw;
	private Date iDate;

	public IllustDto() {}

	public IllustDto(int iNum, String mId, String iTitle, String iContent, String iFilename, int iReadcount, String iPw,
			Date iDate) {
		this.iNum = iNum;
		this.mId = mId;
		this.iTitle = iTitle;
		this.iContent = iContent;
		this.iFilename = iFilename;
		this.iReadcount = iReadcount;
		this.iPw = iPw;
		this.iDate = iDate;
	}

	public int getiNum() {
		return iNum;
	}

	public void setiNum(int iNum) {
		this.iNum = iNum;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getiTitle() {
		return iTitle;
	}

	public void setiTitle(String iTitle) {
		this.iTitle = iTitle;
	}

	public String getiContent() {
		return iContent;
	}

	public void setiContent(String iContent) {
		this.iContent = iContent;
	}

	public String getiFilename() {
		return iFilename;
	}

	public void setiFilename(String iFilename) {
		this.iFilename = iFilename;
	}

	public int getiReadcount() {
		return iReadcount;
	}

	public void setiReadcount(int iReadcount) {
		this.iReadcount = iReadcount;
	}

	public String getiPw() {
		return iPw;
	}

	public void setiPw(String iPw) {
		this.iPw = iPw;
	}

	public Date getiDate() {
		return iDate;
	}

	public void setiDate(Date iDate) {
		this.iDate = iDate;
	}

	@Override
	public String toString() {
		return "IllustDto [iNum=" + iNum + ", mId=" + mId + ", iTitle=" + iTitle + ", iContent=" + iContent
				+ ", iFilename=" + iFilename + ", iReadcount=" + iReadcount + ", iPw=" + iPw + ", iDate=" + iDate + "]";
	}

}
