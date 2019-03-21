package com.tj.ex.dto;

import java.sql.Date;

public class FreeBoardDto {

	private int fNum;
	private String mId;
	private String fTitle;
	private String fContent;
	private int fReadcount;
	private String fPw;
	private int fGroup;
	private int fStep;
	private int fIndent;
	private Date fDate;

	public FreeBoardDto() {}

	public FreeBoardDto(int fNum, String mId, String fTitle, String fContent, int fReadcount, String fPw, int fGroup,
			int fStep, int fIndent, Date fDate) {
		this.fNum = fNum;
		this.mId = mId;
		this.fTitle = fTitle;
		this.fContent = fContent;
		this.fReadcount = fReadcount;
		this.fPw = fPw;
		this.fGroup = fGroup;
		this.fStep = fStep;
		this.fIndent = fIndent;
		this.fDate = fDate;
	}

	public int getfNum() {
		return fNum;
	}

	public void setfNum(int fNum) {
		this.fNum = fNum;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getfTitle() {
		return fTitle;
	}

	public void setfTitle(String fTitle) {
		this.fTitle = fTitle;
	}

	public String getfContent() {
		return fContent;
	}

	public void setfContent(String fContent) {
		this.fContent = fContent;
	}

	public int getfReadcount() {
		return fReadcount;
	}

	public void setfReadcount(int fReadcount) {
		this.fReadcount = fReadcount;
	}

	public String getfPw() {
		return fPw;
	}

	public void setfPw(String fPw) {
		this.fPw = fPw;
	}

	public int getfGroup() {
		return fGroup;
	}

	public void setfGroup(int fGroup) {
		this.fGroup = fGroup;
	}

	public int getfStep() {
		return fStep;
	}

	public void setfStep(int fStep) {
		this.fStep = fStep;
	}

	public int getfIndent() {
		return fIndent;
	}

	public void setfIndent(int fIndent) {
		this.fIndent = fIndent;
	}

	public Date getfDate() {
		return fDate;
	}

	public void setfDate(Date fDate) {
		this.fDate = fDate;
	}

	@Override
	public String toString() {
		return "FreeBoardDto [fNum=" + fNum + ", mId=" + mId + ", fTitle=" + fTitle + ", fContent=" + fContent
				+ ", fReadcount=" + fReadcount + ", fPw=" + fPw + ", fGroup=" + fGroup + ", fStep=" + fStep
				+ ", fIndent=" + fIndent + ", fDate=" + fDate + "]";
	}

}
