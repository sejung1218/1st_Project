package com.tj.ex.dto;

import java.sql.Date;

public class AnimalDto {

	private int aNum;
	private String mId;
	private String aTitle;
	private String aContent;
	private String aFilename;
	private int aReadcount;
	private String aPw;
	private Date aDate;

	public AnimalDto() {}

	public AnimalDto(int aNum, String mId, String aTitle, String aContent, String aFilename, int aReadcount,
			String aPw, Date aDate) {
		this.aNum = aNum;
		this.mId = mId;
		this.aTitle = aTitle;
		this.aContent = aContent;
		this.aFilename = aFilename;
		this.aReadcount = aReadcount;
		this.aPw = aPw;
		this.aDate = aDate;
	}

	public int getaNum() {
		return aNum;
	}

	public void setaNum(int aNum) {
		this.aNum = aNum;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getaTitle() {
		return aTitle;
	}

	public void setaTitle(String aTitle) {
		this.aTitle = aTitle;
	}

	public String getaContent() {
		return aContent;
	}

	public void setaContent(String aContent) {
		this.aContent = aContent;
	}

	public String getaFilename() {
		return aFilename;
	}

	public void setaFilename(String aFilename) {
		this.aFilename = aFilename;
	}

	public int getaReadcount() {
		return aReadcount;
	}

	public void setaReadcount(int aReadcount) {
		this.aReadcount = aReadcount;
	}

	public String getaPw() {
		return aPw;
	}

	public void setaPw(String aPw) {
		this.aPw = aPw;
	}

	public Date getaDate() {
		return aDate;
	}

	public void setaDate(Date aDate) {
		this.aDate = aDate;
	}

	@Override
	public String toString() {
		return "AnimalDto [aNum=" + aNum + ", mId=" + mId + ", aTitle=" + aTitle + ", aContent=" + aContent
				+ ", aFilename=" + aFilename + ", aReadcount=" + aReadcount + ", aPw=" + aPw + ", aDate=" + aDate + "]";
	}

}
