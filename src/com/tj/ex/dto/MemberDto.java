package com.tj.ex.dto;

import java.sql.Date;

public class MemberDto {

	private String mId;
	private String mPw;
	private String mName;
	private String mTel;
	private String mEmail;
	private Date mBirth;
	private String mAddr;

	public MemberDto() {
	}

	public MemberDto(String mId, String mPw, String mName, String mTel, String mEmail, Date mBirth, String mAddr) {
		this.mId = mId;
		this.mPw = mPw;
		this.mName = mName;
		this.mTel = mTel;
		this.mEmail = mEmail;
		this.mBirth = mBirth;
		this.mAddr = mAddr;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmPw() {
		return mPw;
	}

	public void setmPw(String mPw) {
		this.mPw = mPw;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmTel() {
		return mTel;
	}

	public void setmTel(String mTel) {
		this.mTel = mTel;
	}

	public String getmEmail() {
		return mEmail;
	}

	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}

	public Date getmBirth() {
		return mBirth;
	}

	public void setmBirth(Date mBirth) {
		this.mBirth = mBirth;
	}

	public String getmAddr() {
		return mAddr;
	}

	public void setmAddr(String mAddr) {
		this.mAddr = mAddr;
	}

	@Override
	public String toString() {
		return "MemberDto [mId=" + mId + ", mPw=" + mPw + ", mName=" + mName + ", mTel=" + mTel + ", mEmail=" + mEmail
				 + ", mBirth=" + mBirth + ", mAddr=" + mAddr + "]";
	}

}
