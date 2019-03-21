package com.tj.ex.service.RequestBoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.RequestBoardDao;
import com.tj.ex.service.MemberService.Service;

public class RWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession httpSession = request.getSession();
		String mId = (String) httpSession.getAttribute("mId");
		String rTitle = request.getParameter("rTitle");
		String rContent = request.getParameter("rContent");
		String rPw = request.getParameter("rPw");
		RequestBoardDao rDao = new RequestBoardDao();
		int result = rDao.write(mId, rTitle, rContent, rPw);
		if (result == RequestBoardDao.SUCCESS) {
			request.setAttribute("resultMsg", "글쓰기 성공");
		} else {
			request.setAttribute("resultMsg", "글쓰기 실패");
		}
	}
}
