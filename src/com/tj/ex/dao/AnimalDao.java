package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tj.ex.dto.AnimalDto;

public class AnimalDao {

	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;

	public AnimalDao() {
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

	public ArrayList<AnimalDto> list(int startRow, int endRow) {

		ArrayList<AnimalDto> dtos = new ArrayList<AnimalDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM "
				   + "(SELECT A.* FROM ANIMAL A, MEMBER M WHERE A.MID = M.MID ORDER BY ANUM DESC) A) "
				   + "WHERE RN BETWEEN ? AND ?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int aNum = rs.getInt("aNum");
				String mId = rs.getString("mId");
				String aTitle = rs.getString("aTitle");
				String aContent = rs.getString("aContent");
				String aFilename = rs.getString("aFilename");
				int aReadcount = rs.getInt("aReadcount");
				String aPw = rs.getString("aPw");
				Date aDate = rs.getDate("aDate");
				dtos.add(new AnimalDto(aNum, mId, aTitle, aContent, aFilename, aReadcount, aPw, aDate));
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

	public int getAnimalTotCnt() {

		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null; // ?채우기
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM ANIMAL";

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

	public int write(String mId, String aTitle, String aContent, String aFilename, String aPw) {

		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO ANIMAL (ANUM, MID, ATITLE, ACONTENT, AFILENAME, AREADCOUNT, APW, ADATE) "
				   + "VALUES (ANIMAL_SEQ.NEXTVAL, ?, ?, ?, ?, '0', ?, SYSDATE)";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, aTitle);
			pstmt.setString(3, aContent);
			pstmt.setString(4, aFilename);
			pstmt.setString(5, aPw);
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

	private void ReadcountUp(int aNum) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE ANIMAL SET AREADCOUNT = AREADCOUNT + 1 WHERE ANUM = ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aNum);
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
////////////    글 상세보기 (조회수 up + aNum으로 dto 보기           //////////////
//////////////////////////////////////////////////////////////////

	public AnimalDto contentView(int aNum) {

		ReadcountUp(aNum);
		AnimalDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT A.* FROM ANIMAL A, MEMBER M WHERE A.MID = M.MID AND ANUM = ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mId = rs.getString("mId");
				String aTitle = rs.getString("aTitle");
				String aContent = rs.getString("aContent");
				String aFilename = rs.getString("aFilename");
				int aReadcount = rs.getInt("aReadcount");
				String aPw = rs.getString("aPw");
				Date aDate = rs.getDate("aDate");
				dto = new AnimalDto(aNum, mId, aTitle, aContent, aFilename, aReadcount, aPw, aDate);
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

	public int modify(int aNum, String aTitle, String aContent, String aFilename, String aPw) {

		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE ANIMAL SET ATITLE = ? , "
				   + "ACONTENT = ? , "
				   + "AFILENAME = ? , "
				   + "APW = ? , "
				   + "ADATE = SYSDATE "
				   + "WHERE ANUM = ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aTitle);
			pstmt.setString(2, aContent);
			pstmt.setString(3, aFilename);
			pstmt.setString(4, aPw);
			pstmt.setInt(5, aNum);
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
/////////////////	     글 삭제하기(aNum으로 글 삭제)      /////////////////
//////////////////////////////////////////////////////////////////

	public int delete(int aNum) {

		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM ANIMAL WHERE ANUM = ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, aNum);
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
