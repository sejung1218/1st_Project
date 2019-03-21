package com.tj.ex.service.RequestBoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.RequestBoardDao;
import com.tj.ex.service.MemberService.Service;

public class RDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int rNum = Integer.parseInt(request.getParameter("rNum"));
		RequestBoardDao Dao = new RequestBoardDao();
		int result = Dao.delete(rNum);
		if (result == RequestBoardDao.SUCCESS) {
			request.setAttribute("resultMsg", "글삭제 성공");
		} else {
			request.setAttribute("resultMsg", "글삭제 실패");
		}
	}
}