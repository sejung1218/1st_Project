package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tj.ex.dto.PeopleDto;

public class PeopleDao {

	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;

	public PeopleDao() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

//////////////////////////////////////////////////////////////////
////////////////////////  게시판 글 목록     /////////////////////////////
//////////////////////////////////////////////////////////////////

	public ArrayList<PeopleDto> list(int startRow, int endRow) {

		ArrayList<PeopleDto> dtos = new ArrayList<PeopleDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM "
				   + "(SELECT P.* FROM PEOPLE P, MEMBER M WHERE P.MID = M.MID ORDER BY PNUM DESC) A) "
				   + "WHERE RN BETWEEN ? AND ?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int pNum = rs.getInt("pNum");
				String mId = rs.getString("mId");
				String pTitle = rs.getString("pTitle");
				String pContent = rs.getString("pContent");
				String pFilename = rs.getString("pFilename");
				int pReadcount = rs.getInt("pReadcount");
				String pPw = rs.getString("pPw");
				Date pDate = rs.getDate("pDate");
				dtos.add(new PeopleDto(pNum, mId, pTitle, pContent, pFilename, pReadcount, pPw, pDate));
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
		return dtos;
	}

//////////////////////////////////////////////////////////////////
//////////////////////////    글 갯수          ///////////////////////////
//////////////////////////////////////////////////////////////////

	public int getPeopleTotCnt() {

		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null; // ?채우기
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM PEOPLE";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);

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
		return cnt;
	}

//////////////////////////////////////////////////////////////////
//////////////////////////    글쓰기          ////////////////////////////
//////////////////////////////////////////////////////////////////

	public int write(String mId, String pTitle, String pContent, String pFilename, String pPw) {

		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO PEOPLE (PNUM, MID, PTITLE, PCONTENT, PFILENAME, PREADCOUNT, PPW, PDATE) "
				   + "VALUES (PEOPLE_SEQ.NEXTVAL, ?, ?, ?, ?, '0', ?, SYSDATE)";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, pTitle);
			pstmt.setString(3, pContent);
			pstmt.setString(4, pFilename);
			pstmt.setString(5, pPw);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "원글쓰기성공" : "원글쓰기실패");

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
////////////////////////     조회수 올리기          /////////////////////////
//////////////////////////////////////////////////////////////////

	private void ReadcountUp(int pNum) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PEOPLE SET PREADCOUNT = PREADCOUNT + 1 WHERE PNUM = ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNum);
			pstmt.executeUpdate();

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
	}

//////////////////////////////////////////////////////////////////
////////////    글 상세보기 (조회수 up + pNum으로 dto 보기           //////////////
//////////////////////////////////////////////////////////////////

	public PeopleDto contentView(int pNum) {

		ReadcountUp(pNum);
		PeopleDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT P.* FROM PEOPLE P, MEMBER M WHERE P.MID = M.MID AND PNUM = ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mId = rs.getString("mId");
				String pTitle = rs.getString("pTitle");
				String pContent = rs.getString("pContent");
				String pFilename = rs.getString("pFilename");
				int pReadcount = rs.getInt("pReadcount");
				String pPw = rs.getString("pPw");
				Date pDate = rs.getDate("pDate");
				dto = new PeopleDto(pNum, mId, pTitle, pContent, pFilename, pReadcount, pPw, pDate);
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
		return dto;
	}

//////////////////////////////////////////////////////////////////
////////////                글 수정하기   		        //////////////
//////////////////////////////////////////////////////////////////

	public int modify(int pNum, String pTitle, String pContent, String pFilename, String pPw) {

		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE PEOPLE SET PTITLE = ? , "
				   + "PCONTENT = ? , "
				   + "PFILENAME = ? , "
				   + "PPW = ? , "
				   + "PDATE = SYSDATE "
				   + "WHERE PNUM = ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pTitle);
			pstmt.setString(2, pContent);
			pstmt.setString(3, pFilename);
			pstmt.setString(4, pPw);
			pstmt.setInt(5, pNum);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글 수정성공" : "글 수정실패");

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
/////////////////	     글 삭제하기(pNum으로 글 삭제)      /////////////////
//////////////////////////////////////////////////////////////////

	public int delete(int pNum) {

		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM PEOPLE WHERE PNUM = ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pNum);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "글 삭제성공" : "글 삭제실패");

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

}
