package com.tj.ex.dto;

import java.sql.Date;

public class VehicleDto {

	private int vNum;
	private String vId;
	private String vTitle;
	private String vContent;
	private String vFilename;
	private int vReadcount;
	private String vPw;
	private Date vDate;

	public VehicleDto() {}

	public VehicleDto(int vNum, String vId, String vTitle, String vContent, String vFilename, int vReadcount,
			String vPw, Date vDate) {
		this.vNum = vNum;
		this.vId = vId;
		this.vTitle = vTitle;
		this.vContent = vContent;
		this.vFilename = vFilename;
		this.vReadcount = vReadcount;
		this.vPw = vPw;
		this.vDate = vDate;
	}

	public int getvNum() {
		return vNum;
	}

	public void setvNum(int vNum) {
		this.vNum = vNum;
	}

	public String getvId() {
		return vId;
	}

	public void setvId(String vId) {
		this.vId = vId;
	}

	public String getvTitle() {
		return vTitle;
	}

	public void setvTitle(String vTitle) {
		this.vTitle = vTitle;
	}

	public String getvContent() {
		return vContent;
	}

	public void setvContent(String vContent) {
		this.vContent = vContent;
	}

	public String getvFilename() {
		return vFilename;
	}

	public void setvFilename(String vFilename) {
		this.vFilename = vFilename;
	}

	public int getvReadcount() {
		return vReadcount;
	}

	public void setvReadcount(int vReadcount) {
		this.vReadcount = vReadcount;
	}

	public String getvPw() {
		return vPw;
	}

	public void setvPw(String vPw) {
		this.vPw = vPw;
	}

	public Date getvDate() {
		return vDate;
	}

	public void setvDate(Date vDate) {
		this.vDate = vDate;
	}

	@Override
	public String toString() {
		return "VehicleDto [vNum=" + vNum + ", vId=" + vId + ", vTitle=" + vTitle + ", vContent=" + vContent
				+ ", vFilename=" + vFilename + ", vReadcount=" + vReadcount + ", vPw=" + vPw + ", vDate=" + vDate + "]";
	}

}
