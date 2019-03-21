package com.tj.ex.dto;

import java.sql.Date;

public class TechniqueDto {

	private int tNum;
	private String tId;
	private String tTitle;
	private String tContent;
	private String tFilename;
	private int tReadcount;
	private String tPw;
	private Date tDate;

	public TechniqueDto() {}

	public TechniqueDto(int tNum, String tId, String tTitle, String tContent, String tFilename, int tReadcount,
			String tPw, Date tDate) {
		this.tNum = tNum;
		this.tId = tId;
		this.tTitle = tTitle;
		this.tContent = tContent;
		this.tFilename = tFilename;
		this.tReadcount = tReadcount;
		this.tPw = tPw;
		this.tDate = tDate;
	}

	public int gettNum() {
		return tNum;
	}

	public void settNum(int tNum) {
		this.tNum = tNum;
	}

	public String gettId() {
		return tId;
	}

	public void settId(String tId) {
		this.tId = tId;
	}

	public String gettTitle() {
		return tTitle;
	}

	public void settTitle(String tTitle) {
		this.tTitle = tTitle;
	}

	public String gettContent() {
		return tContent;
	}

	public void settContent(String tContent) {
		this.tContent = tContent;
	}

	public String gettFilename() {
		return tFilename;
	}

	public void settFilename(String tFilename) {
		this.tFilename = tFilename;
	}

	public int gettReadcount() {
		return tReadcount;
	}

	public void settReadcount(int tReadcount) {
		this.tReadcount = tReadcount;
	}

	public String gettPw() {
		return tPw;
	}

	public void settPw(String tPw) {
		this.tPw = tPw;
	}

	public Date gettDate() {
		return tDate;
	}

	public void settDate(Date tDate) {
		this.tDate = tDate;
	}

	@Override
	public String toString() {
		return "TechniqueDto [tNum=" + tNum + ", tId=" + tId + ", tTitle=" + tTitle + ", tContent=" + tContent
				+ ", tFilename=" + tFilename + ", tReadcount=" + tReadcount + ", tPw=" + tPw + ", tDate=" + tDate + "]";
	}

}
