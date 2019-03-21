package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tj.ex.dto.MemberDto;

public class MemberDao {

	public static final int EXISTENT = 0;
	public static final int NONEXISTENT = 1;
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	public static final int LOGIN_SUCCESS = 1;
	public static final int LOGIN_FAIL_PW = 0;
	public static final int LOGIN_FAIL_ID = -1;

	private DataSource ds;

	public MemberDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

//////////////////////////////////////////////////////////////////
///////////////////  ID Confirm (아이디 중복체크)  ////////////////////
//////////////////////////////////////////////////////////////////

	public int mIdConfirm(String mId) {

		int result = EXISTENT;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER WHERE MID=?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = EXISTENT;
			} else {
				result = NONEXISTENT;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

///////////////////////////////////////////////////////////////////
/////////////////////////  Join (회원가입)  //////////////////////////
///////////////////////////////////////////////////////////////////

	public int joinMember(MemberDto dto) {

		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO MEMBER (MID, MPW, MNAME, MTEL, MEMAIL, MBIRTH, MADDR) "
				   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmId());
			pstmt.setString(2, dto.getmPw());
			pstmt.setString(3, dto.getmName());
			pstmt.setString(4, dto.getmTel());
			pstmt.setString(5, dto.getmEmail());
			pstmt.setDate(6, dto.getmBirth());
			pstmt.setString(7, dto.getmAddr());
			result = pstmt.executeUpdate();
			System.out.println((result == SUCCESS ? "회원가입성공" : "회원가입실패"));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

//////////////////////////////////////////////////////////////////
///////////////////////  Login (로그인 체크)  ////////////////////////
//////////////////////////////////////////////////////////////////

	public int loginCheck(String mId, String mPw) {

		int result = LOGIN_FAIL_ID;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER WHERE MID= ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbmPw = rs.getString("mPw");
				if (dbmPw.equals(mPw)) {
					result = LOGIN_SUCCESS;
				} else {
					result = LOGIN_FAIL_PW;
				}
			} else {
				result = LOGIN_FAIL_ID;
			}
			/* System.out.println(1 + toString()); */
			System.out.println((result == SUCCESS ? "로그인성공" : "로그인실패"));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

//////////////////////////////////////////////////////////////////
///////////////////  mId로 Dto 가져오기 (로그인 시 )  ////////////////////
//////////////////////////////////////////////////////////////////

	public MemberDto getMember(String mId) {

		MemberDto member = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM MEMBER WHERE MID= ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				member = new MemberDto();
				member.setmId(rs.getString("mId"));
				member.setmPw(rs.getString("mPw"));
				member.setmName(rs.getString("mName"));
				member.setmTel(rs.getString("mTel"));
				member.setmEmail(rs.getString("mEmail"));
				member.setmBirth(rs.getDate("mBirth"));
				member.setmAddr(rs.getString("mAddr"));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return member;
	}

///////////////////////////////////////////////////////////////////
////////////////////////  Modify (정보수정)  /////////////////////////
///////////////////////////////////////////////////////////////////	

	public int modifyMember(MemberDto dto) {

		int result = FAIL;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE MEMBER SET MPW= ? , " 
				   + "MNAME= ? , " 
				   + "MTEL= ? , " 
				   + "MEMAIL= ? , " 
				   + "MBIRTH= ? , " 
				   + "MADDR= ? " 
				   + "WHERE MID= ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getmPw());
			pstmt.setString(2, dto.getmName());
			pstmt.setString(3, dto.getmTel());
			pstmt.setString(4, dto.getmEmail());
			pstmt.setDate(5, dto.getmBirth());
			pstmt.setString(6, dto.getmAddr());
			pstmt.setString(7, dto.getmId());
			result = pstmt.executeUpdate();
			System.out.println((result == SUCCESS ? "회원수정성공" : "회원수정실패"));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

///////////////////////////////////////////////////////////////////
/////////////////////  가입한 회원 수 (전체회원 보기)  //////////////////////
///////////////////////////////////////////////////////////////////	

	public int getMemberTotCnt() {

		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM MEMBER";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

///////////////////////////////////////////////////////////////////
/////////////////  StartRow ~ EndRow 까지 list 출력  //////////////////
///////////////////////////////////////////////////////////////////

	public ArrayList<MemberDto> list(int startRow, int endRow) {

		ArrayList<MemberDto> dtos = new ArrayList<MemberDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM ( SELECT ROWNUM RN, A.* FROM (SELECT * FROM MEMBER ORDER BY MID) A) "
				   + "WHERE RN BETWEEN ? AND ?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String mId = rs.getString("mId");
				String mPw = rs.getString("mPw");
				String mName = rs.getString("mName");
				String mTel = rs.getString("mTel");
				String mEmail = rs.getString("mEmail");
				Date mBirth = rs.getDate("mBirth");
				String mAddr = rs.getString("mAddr");
				dtos.add(new MemberDto(mId, mPw, mName, mTel, mEmail, mBirth, mAddr));
			}

		} catch (Exception e) {
			System.out.println("error" + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return dtos;
	}
}