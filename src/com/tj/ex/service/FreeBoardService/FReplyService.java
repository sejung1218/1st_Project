package com.tj.ex.service.FreeBoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.FreeBoardDao;
import com.tj.ex.service.MemberService.Service;

public class FReplyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession httpSession = request.getSession();
		String mId = (String) httpSession.getAttribute("mId");
		String fTitle = request.getParameter("fTitle");
		String fContent = request.getParameter("fContent");
		String fPw = request.getParameter("fPw");
		int fGroup = Integer.parseInt(request.getParameter("fGroup"));
		int fStep = Integer.parseInt(request.getParameter("fStep"));
		int fIndent = Integer.parseInt(request.getParameter("fIndent"));
		FreeBoardDao Dao = new FreeBoardDao();
		int result = Dao.reply(mId, fTitle, fContent, fPw, fGroup, fStep, fIndent);
		if (result == FreeBoardDao.SUCCESS) {
			request.setAttribute("resultMsg", "답글쓰기 성공");
		} else {
			request.setAttribute("resultMsg", "답글쓰기 실패");
		}
		String pageNum = request.getParameter("pageNum");
		request.setAttribute("pageNum", pageNum);
	}

}
