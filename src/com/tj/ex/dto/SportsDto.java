package com.tj.ex.dto;

import java.sql.Date;

public class SportsDto {

	private int sNum;
	private String sId;
	private String sTitle;
	private String sContent;
	private String sFilename;
	private int sReadcount;
	private String sPw;
	private Date sDate;

	public SportsDto() {}

	public SportsDto(int sNum, String sId, String sTitle, String sContent, String sFilename, int sReadcount, String sPw,
			Date sDate) {
		this.sNum = sNum;
		this.sId = sId;
		this.sTitle = sTitle;
		this.sContent = sContent;
		this.sFilename = sFilename;
		this.sReadcount = sReadcount;
		this.sPw = sPw;
		this.sDate = sDate;
	}

	public int getsNum() {
		return sNum;
	}

	public void setsNum(int sNum) {
		this.sNum = sNum;
	}

	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	public String getsTitle() {
		return sTitle;
	}

	public void setsTitle(String sTitle) {
		this.sTitle = sTitle;
	}

	public String getsContent() {
		return sContent;
	}

	public void setsContent(String sContent) {
		this.sContent = sContent;
	}

	public String getsFilename() {
		return sFilename;
	}

	public void setsFilename(String sFilename) {
		this.sFilename = sFilename;
	}

	public int getsReadcount() {
		return sReadcount;
	}

	public void setsReadcount(int sReadcount) {
		this.sReadcount = sReadcount;
	}

	public String getsPw() {
		return sPw;
	}

	public void setsPw(String sPw) {
		this.sPw = sPw;
	}

	public Date getsDate() {
		return sDate;
	}

	public void setsDate(Date sDate) {
		this.sDate = sDate;
	}

	@Override
	public String toString() {
		return "SportsDto [sNum=" + sNum + ", sId=" + sId + ", sTitle=" + sTitle + ", sContent=" + sContent
				+ ", sFilename=" + sFilename + ", sReadcount=" + sReadcount + ", sPw=" + sPw + ", sDate=" + sDate + "]";
	}

}
