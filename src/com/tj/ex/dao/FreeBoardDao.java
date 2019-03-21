package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tj.ex.dto.FreeBoardDto;

public class FreeBoardDao {

	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;

	public FreeBoardDao() {
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
	public ArrayList<FreeBoardDto> list(int startRow, int endRow) {

		ArrayList<FreeBoardDto> dtos = new ArrayList<FreeBoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM "
				+ "(SELECT F.* FROM FREEBOARD F, MEMBER M WHERE F.MID = M.MID ORDER BY FGROUP DESC, FSTEP) A) "
				+ "WHERE RN BETWEEN ? AND ?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int fNum = rs.getInt("fNum");
				String mId = rs.getString("mId");
				String fTitle = rs.getString("fTitle");
				String fContent = rs.getString("fContent");
				int fReadcount = rs.getInt("fReadcount");
				String fPw = rs.getString("fPw");
				int fGroup = rs.getInt("fGroup");
				int fStep = rs.getInt("fStep");
				int fIndent = rs.getInt("fIndent");
				Date fDate = rs.getDate("fDate");
				dtos.add(new FreeBoardDto(fNum, mId, fTitle, fContent, fReadcount, fPw, fGroup, fStep, fIndent, fDate));
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

	public int getFreeBoardTotCnt() {

		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null; // ?채우기
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM FREEBOARD";

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

	public int write(String mId, String fTitle, String fContent, String fPw) {

		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO FREEBOARD (FNUM, MID, FTITLE, FCONTENT, FREADCOUNT, FPW, FGROUP, FSTEP, FINDENT, FDATE) "
				+ "VALUES (FREEBOARD_SEQ.NEXTVAL, ?, ?, ?, 0, ?, FREEBOARD_SEQ.CURRVAL, 0, 0, SYSDATE)";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, fTitle);
			pstmt.setString(3, fContent);
			pstmt.setString(4, fPw);
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
////////////////////////    조회수 올리기          /////////////////////////
//////////////////////////////////////////////////////////////////

	private void ReadcountUp(int fNum) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FREEBOARD SET FREADCOUNT = FREADCOUNT + 1 WHERE FNUM = ?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fNum);
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
////////////    글 상세보기 (조회수 up + fNum으로 dto 보기           //////////////
//////////////////////////////////////////////////////////////////

	public FreeBoardDto contentView(int fNum) {

		ReadcountUp(fNum);
		FreeBoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM FREEBOARD WHERE FNUM= ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mId = rs.getString("mId");
				String fTitle = rs.getString("fTitle");
				String fContent = rs.getString("fContent");
				int fReadcount = rs.getInt("fReadcount");
				String fPw = rs.getString("fPw");
				int fGroup = rs.getInt("fGroup");
				int fStep = rs.getInt("fStep");
				int fIndent = rs.getInt("fIndent");
				Date fDate = rs.getDate("fDate");
				dto = new FreeBoardDto(fNum, mId, fTitle, fContent, fReadcount, fPw, fGroup, fStep, fIndent, fDate);
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
////////////  답변글 view + 수정 view ( fNum로 dto 리턴 )   //////////////
//////////////////////////////////////////////////////////////////

	public FreeBoardDto modifyView_replyView(int fNum) {

		FreeBoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT F.* FROM FREEBOARD F, MEMBER M WHERE F.MID = M.MID AND FNUM= ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fNum);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String mId = rs.getString("mId");
				String fTitle = rs.getString("fTitle");
				String fContent = rs.getString("fContent");
				int fReadcount = rs.getInt("fReadcount");
				String fPw = rs.getString("fPw");
				int fGroup = rs.getInt("fGroup");
				int fStep = rs.getInt("fStep");
				int fIndent = rs.getInt("fIndent");
				Date fDate = rs.getDate("fDate");
				dto = new FreeBoardDto(fNum, mId, fTitle, fContent, fReadcount, fPw, fGroup, fStep, fIndent, fDate);
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
////////////				   글 수정하기   		        //////////////
//////////////////////////////////////////////////////////////////

	public int modify(int fNum, String fTitle, String fContent, String fPw) {

		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FREEBOARD SET FTITLE = ?, " + "FCONTENT = ?, " + "FPW = ?, " + "FDATE = SYSDATE "
				+ "WHERE FNUM = ?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fTitle);
			pstmt.setString(2, fContent);
			pstmt.setString(3, fPw);
			pstmt.setInt(4, fNum);
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
/////////////////	     글 삭제하기(fNum으로 글 삭제)      /////////////////
//////////////////////////////////////////////////////////////////

	public int delete(int fNum) {

		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM FREEBOARD WHERE FNUM= ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fNum);
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

//////////////////////////////////////////////////////////////////
/////////////////	     	   step A      	     /////////////////
//////////////////////////////////////////////////////////////////	

	private void preReplyStepA(int fGroup, int fStep) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE FREEBOARD SET FSTEP = FSTEP+1 WHERE FGROUP = ? AND FSTEP > ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fGroup);
			pstmt.setInt(2, fStep);
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
/////////////////	     	   답변글 쓰기         	     /////////////////
//////////////////////////////////////////////////////////////////

	public int reply(String mId, String fTitle, String fContent, String fPw, int fGroup, int fStep, int fIndent) {

		preReplyStepA(fGroup, fStep); // 답변글 저장 전 step A 먼저 실행
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO FREEBOARD (FNUM, MID, FTITLE, FCONTENT, FREADCOUNT, FPW, FGROUP, FSTEP, FINDENT, FDATE) "
				+ "VALUES (FREEBOARD_SEQ.NEXTVAL, ?, ?, ?, '0', ?, ?, ?, ?, SYSDATE)";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, fTitle);
			pstmt.setString(3, fContent);
			pstmt.setString(4, fPw);
			pstmt.setInt(5, fGroup);
			pstmt.setInt(6, fStep + 1);
			pstmt.setInt(7, fIndent + 1);
			result = pstmt.executeUpdate();
			System.out.println(result == SUCCESS ? "답변쓰기성공" : "답변쓰기실패");

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