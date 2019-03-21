package com.tj.ex.service.RequestBoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.RequestBoardDao;
import com.tj.ex.service.MemberService.Service;

public class RReplyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession httpSession = request.getSession();
		String mId = (String) httpSession.getAttribute("mId");
		String rTitle = request.getParameter("rTitle");
		String rContent = request.getParameter("rContent");
		String rPw = request.getParameter("rPw");
		int rGroup = Integer.parseInt(request.getParameter("rGroup"));
		int rStep = Integer.parseInt(request.getParameter("rStep"));
		int rIndent = Integer.parseInt(request.getParameter("rIndent"));
		RequestBoardDao Dao = new RequestBoardDao();
		int result = Dao.reply(mId, rTitle, rContent, rPw, rGroup, rStep, rIndent);
		if (result == RequestBoardDao.SUCCESS) {
			request.setAttribute("resultMsg", "답글쓰기 성공");
		} else {
			request.setAttribute("resultMsg", "답글쓰기 실패");
		}
		String pageNum = request.getParameter("pageNum");
		request.setAttribute("pageNum", pageNum);
	}
}
