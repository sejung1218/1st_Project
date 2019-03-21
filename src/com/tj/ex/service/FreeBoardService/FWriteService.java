package com.tj.ex.service.FreeBoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.FreeBoardDao;
import com.tj.ex.service.MemberService.Service;

public class FWriteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession httpSession = request.getSession();
		String mId = (String) httpSession.getAttribute("mId");
		String fTitle = request.getParameter("fTitle");
		String fContent = request.getParameter("fContent");
		String fPw = request.getParameter("fPw");
		FreeBoardDao fDao = new FreeBoardDao();
		int result = fDao.write(mId, fTitle, fContent, fPw);
		if (result == FreeBoardDao.SUCCESS) {
			request.setAttribute("resultMsg", "글쓰기 성공");
		} else {
			request.setAttribute("resultMsg", "글쓰기 실패");
		}
	}
}
