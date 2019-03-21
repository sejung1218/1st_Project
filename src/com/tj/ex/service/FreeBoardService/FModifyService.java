package com.tj.ex.service.FreeBoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.FreeBoardDao;
import com.tj.ex.service.MemberService.Service;

public class FModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		int fNum = Integer.parseInt(request.getParameter("fNum"));
		String fTitle = request.getParameter("fTitle");
		String fContent = request.getParameter("fContent");
		String fPw = request.getParameter("fPw");
		FreeBoardDao Dao = new FreeBoardDao();
		int result = Dao.modify(fNum, fTitle, fContent, fPw);
		if (result == FreeBoardDao.SUCCESS) {
			request.setAttribute("resultMsg", "글수정 성공");
		} else {  
			request.setAttribute("resultMsg", "글수정 실패");
		}
		String pageNum = request.getParameter("pageNum");
		request.setAttribute("pageNum", pageNum);

	}
}
