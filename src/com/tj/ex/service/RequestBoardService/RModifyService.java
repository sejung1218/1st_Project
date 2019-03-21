package com.tj.ex.service.RequestBoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.RequestBoardDao;
import com.tj.ex.service.MemberService.Service;

public class RModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		int rNum = Integer.parseInt(request.getParameter("rNum"));
		String rTitle = request.getParameter("rTitle");
		String rContent = request.getParameter("rContent");
		String rPw = request.getParameter("rPw");
		RequestBoardDao Dao = new RequestBoardDao();
		int result = Dao.modify(rNum, rTitle, rContent, rPw);
		if (result == RequestBoardDao.SUCCESS) {
			request.setAttribute("resultMsg", "글수정 성공");
		} else {  
			request.setAttribute("resultMsg", "글수정 실패");
		}
		String pageNum = request.getParameter("pageNum");
		request.setAttribute("pageNum", pageNum);

	}
}
