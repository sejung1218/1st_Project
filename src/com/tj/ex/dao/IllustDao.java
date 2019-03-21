package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tj.ex.dto.IllustDto;

public class IllustDao {

	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;

	public IllustDao() {
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

	public ArrayList<IllustDto> list(int startRow, int endRow) {

		ArrayList<IllustDto> dtos = new ArrayList<IllustDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM "
				+ "(SELECT I.* FROM Illust I, MEMBER M WHERE I.MID = M.MID ORDER BY INUM DESC) A) "
				+ "WHERE RN BETWEEN ? AND ?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int iNum = rs.getInt("iNum");
				String mId = rs.getString("mId");
				String iTitle = rs.getString("iTitle");
				String iContent = rs.getString("iContent");
				String iFilename = rs.getString("iFilename");
				int iReadcount = rs.getInt("iReadcount");
				String iPw = rs.getString("iPw");
				Date iDate = rs.getDate("iDate");
				dtos.add(new IllustDto(iNum, mId, iTitle, iContent, iFilename, iReadcount, iPw, iDate));
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

	public int getIllustTotCnt() {

		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null; // ?채우기
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM Illust";

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

	public int write(String mId, String iTitle, String iContent, String iFilename, String iPw) {

		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO Illust (INUM, MID, ITITLE, ICONTENT, IFILENAME, IREADCOUNT, IPW, IDATE) "
				+ "VALUES (Illust_SEQ.NEXTVAL, ?, ?, ?, ?, '0', ?, SYSDATE)";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, iTitle);
			pstmt.setString(3, iContent);
			pstmt.setString(4, iFilename);
			pstmt.setString(5, iPw);
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

	private void ReadcountUp(int iNum) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE Illust SET IREADCOUNT = IREADCOUNT + 1 WHERE INUM = ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, iNum);
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
////////////    글 상세보기 (조회수 up + iNum으로 dto 보기           //////////////
//////////////////////////////////////////////////////////////////

	public IllustDto contentView(int iNum) {

		ReadcountUp(iNum);
		IllustDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT I.* FROM Illust I, MEMBER M WHERE I.MID = M.MID AND INUM = ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, iNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mId = rs.getString("mId");
				String iTitle = rs.getString("iTitle");
				String iContent = rs.getString("iContent");
				String iFilename = rs.getString("iFilename");
				int iReadcount = rs.getInt("iReadcount");
				String iPw = rs.getString("iPw");
				Date iDate = rs.getDate("iDate");
				dto = new IllustDto(iNum, mId, iTitle, iContent, iFilename, iReadcount, iPw, iDate);
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

	public int modify(int iNum, String iTitle, String iContent, String iFilename, String iPw) {

		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE Illust SET ITITLE = ? , " + "ICONTENT = ? , " + "IFILENAME = ? , " + "IPW = ? , "
				+ "IDATE = SYSDATE " + "WHERE INUM = ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, iTitle);
			pstmt.setString(2, iContent);
			pstmt.setString(3, iFilename);
			pstmt.setString(4, iPw);
			pstmt.setInt(5, iNum);
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
/////////////////	     글 삭제하기(iNum으로 글 삭제)      /////////////////
//////////////////////////////////////////////////////////////////

	public int delete(int iNum) {

		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM Illust WHERE INUM = ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, iNum);
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
