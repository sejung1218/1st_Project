package com.tj.ex.service.IllustService;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.IllustDao;
import com.tj.ex.dto.IllustDto;
import com.tj.ex.service.MemberService.Service;

public class IllustListService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String pageNum = request.getParameter("pageNum");
		String requestPageNum = (String) request.getAttribute("pageNum");
		if (pageNum == null) {
			if (requestPageNum == null)
				pageNum = "1";
			else
				pageNum = requestPageNum;
		}
		int currentPage = Integer.parseInt(pageNum);
		final int PAGESIZE = 20, BLOCKSIZE = 10;
		int startRow = (currentPage - 1) * PAGESIZE + 1;
		int endRow = startRow + PAGESIZE - 1;
		IllustDao Dao = new IllustDao();
		ArrayList<IllustDto> list = Dao.list(startRow, endRow);
		request.setAttribute("list", list);
		int totCnt = Dao.getIllustTotCnt(); // 글갯수
		int pageCnt = (int) Math.ceil((double) totCnt / PAGESIZE);// 페이지갯수
		int startPage = ((currentPage - 1) / BLOCKSIZE) * BLOCKSIZE + 1;
		int endPage = startPage + BLOCKSIZE - 1;
		if (endPage > pageCnt) {
			endPage = pageCnt;
		}
		request.setAttribute("BLOCKSIZE", BLOCKSIZE);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageCnt", pageCnt);
		request.setAttribute("totCnt", totCnt);
		request.setAttribute("pageNum", currentPage);
	}
}