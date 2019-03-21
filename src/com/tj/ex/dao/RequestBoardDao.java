package com.tj.ex.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.tj.ex.dto.RequestBoardDto;

public class RequestBoardDao {
	
	public static final int FAIL = 0;
	public static final int SUCCESS = 1;
	private DataSource ds;

	public RequestBoardDao() {
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
	
	public ArrayList<RequestBoardDto> list(int startRow, int endRow) {

		ArrayList<RequestBoardDto> dtos = new ArrayList<RequestBoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT ROWNUM RN, A.* FROM "
				   + "(SELECT R.* FROM REQUESTBOARD R, MEMBER M WHERE R.MID = M.MID ORDER BY RGROUP DESC, RSTEP) A) "
				   + "WHERE RN BETWEEN ? AND ?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int rNum = rs.getInt("rNum");
				String mId = rs.getString("mId");
				String rTitle = rs.getString("rTitle");
				String rContent = rs.getString("rContent");
				int rReadcount = rs.getInt("rReadcount");
				String rPw = rs.getString("rPw");
				int rGroup = rs.getInt("rGroup");
				int rStep = rs.getInt("rStep");
				int rIndent = rs.getInt("rIndent");
				Date rDate = rs.getDate("rDate");
				dtos.add(new RequestBoardDto(rNum, mId, rTitle, rContent, rReadcount, rPw, rGroup, rStep, rIndent, rDate));
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
	
	public int getRequestBoardTotCnt() {

		int cnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null; // ?채우기
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM REQUESTBOARD";

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
	
	public int write(String mId, String rTitle, String rContent, String rPw) {

		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO REQUESTBOARD (RNUM, MID, RTITLE, RCONTENT, RREADCOUNT, RPW, RGROUP, RSTEP, RINDENT, RDATE) "
				   + "VALUES (REQUESTBOARD_SEQ.NEXTVAL, ?, ?, ?, 0, ?, REQUESTBOARD_SEQ.CURRVAL, 0, 0, SYSDATE)";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, rTitle);
			pstmt.setString(3, rContent);
			pstmt.setString(4, rPw);
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
	
	private void ReadcountUp(int rNum) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REQUESTBOARD SET RREADCOUNT = RREADCOUNT + 1 WHERE RNUM = ?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rNum);
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
////////////    글 상세보기 (조회수 up + rNum으로 dto 보기           //////////////
//////////////////////////////////////////////////////////////////
	
	public RequestBoardDto contentView(int rNum) {

		ReadcountUp(rNum);
		RequestBoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM REQUESTBOARD WHERE RNUM=?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String mId = rs.getString("mId");
				String rTitle = rs.getString("rTitle");
				String rContent = rs.getString("rContent");
				int rReadcount = rs.getInt("rReadcount");
				String rPw = rs.getString("rPw");
				int rGroup = rs.getInt("rGroup");
				int rStep = rs.getInt("rStep");
				int rIndent = rs.getInt("rIndent");
				Date rDate = rs.getDate("rDate");
				dto = new RequestBoardDto(rNum, mId, rTitle, rContent, rReadcount, rPw, rGroup, rStep, rIndent, rDate);
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
////////////  답변글 view + 수정 view ( rNum로 dto 리턴 )   //////////////
//////////////////////////////////////////////////////////////////
	
	public RequestBoardDto modifyView_replyView(int rNum) {
		
		RequestBoardDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT R.* FROM REQUESTBOARD R, MEMBER M WHERE R.MID = M.MID AND RNUM= ? ";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rNum);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String mId = rs.getString("mId");
				String rTitle = rs.getString("rTitle");
				String rContent = rs.getString("rContent");
				int rReadcount = rs.getInt("rReadcount");
				String rPw = rs.getString("rPw");
				int rGroup = rs.getInt("rGroup");
				int rStep = rs.getInt("rStep");
				int rIndent = rs.getInt("rIndent");
				Date rDate = rs.getDate("rDate");
				dto = new RequestBoardDto(rNum, mId, rTitle, rContent, rReadcount, rPw, rGroup, rStep, rIndent, rDate);
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
	
	public int modify(int rNum, String rTitle, String rContent, String rPw) {
		
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REQUESTBOARD SET RTITLE = ?, " 
				   + "RCONTENT = ?, " 
				   + "RPW = ?, " 
				   + "RDATE = SYSDATE " 
				   + "WHERE RNUM = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rTitle);
			pstmt.setString(2, rContent);
			pstmt.setString(3, rPw);
			pstmt.setInt(4, rNum);
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
/////////////////	     글 삭제하기(rNum으로 글 삭제)      /////////////////
//////////////////////////////////////////////////////////////////
	
	public int delete(int rNum) {
		
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM REQUESTBOARD WHERE RNUM= ? ";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rNum);
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
	
	private void preReplyStepA(int rGroup, int rStep) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE REQUESTBOARD SET RSTEP = RSTEP+1 WHERE RGROUP = ? AND RSTEP > ? ";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rGroup);
			pstmt.setInt(2, rStep);
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
	
	public int reply(String mId, String rTitle, String rContent, String rPw, int rGroup, int rStep, int rIndent) {

		preReplyStepA(rGroup, rStep); // 답변글 저장 전 step A 먼저 실행
		int result = FAIL;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO REQUESTBOARD (RNUM, MID, RTITLE, RCONTENT, RREADCOUNT, RPW, RGROUP, RSTEP, RINDENT, RDATE) "
				   + "VALUES (REQUESTBOARD_SEQ.NEXTVAL, ?, ?, ?, '0', ?, ?, ?, ?, SYSDATE)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mId);
			pstmt.setString(2, rTitle);
			pstmt.setString(3, rContent);
			pstmt.setString(4, rPw);
			pstmt.setInt(5, rGroup);
			pstmt.setInt(6, rStep + 1);
			pstmt.setInt(7, rIndent + 1);
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
