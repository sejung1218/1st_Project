package com.tj.ex.dto;

import java.sql.Date;

public class NatureDto {

	private int nNum;
	private String mId;
	private String nTitle;
	private String nContent;
	private String nFilename;
	private int nReadcount;
	private String nPw;
	private Date nDate;

	public NatureDto() {}

	public NatureDto(int nNum, String mId, String nTitle, String nContent, String nFilename, int nReadcount, String nPw,
			Date nDate) {
		this.nNum = nNum;
		this.mId = mId;
		this.nTitle = nTitle;
		this.nContent = nContent;
		this.nFilename = nFilename;
		this.nReadcount = nReadcount;
		this.nPw = nPw;
		this.nDate = nDate;
	}

	public int getnNum() {
		return nNum;
	}

	public void setnNum(int nNum) {
		this.nNum = nNum;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getnTitle() {
		return nTitle;
	}

	public void setnTitle(String nTitle) {
		this.nTitle = nTitle;
	}

	public String getnContent() {
		return nContent;
	}

	public void setnContent(String nContent) {
		this.nContent = nContent;
	}

	public String getnFilename() {
		return nFilename;
	}

	public void setnFilename(String nFilename) {
		this.nFilename = nFilename;
	}

	public int getnReadcount() {
		return nReadcount;
	}

	public void setnReadcount(int nReadcount) {
		this.nReadcount = nReadcount;
	}

	public String getnPw() {
		return nPw;
	}

	public void setnPw(String nPw) {
		this.nPw = nPw;
	}

	public Date getnDate() {
		return nDate;
	}

	public void setnDate(Date nDate) {
		this.nDate = nDate;
	}

	@Override
	public String toString() {
		return "NatureDto [nNum=" + nNum + ", mId=" + mId + ", nTitle=" + nTitle + ", nContent=" + nContent
				+ ", nFilename=" + nFilename + ", nReadcount=" + nReadcount + ", nPw=" + nPw + ", nDate=" + nDate + "]";
	}

}
