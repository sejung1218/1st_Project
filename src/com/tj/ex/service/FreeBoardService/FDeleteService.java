package com.tj.ex.service.FreeBoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.FreeBoardDao;
import com.tj.ex.service.MemberService.Service;

public class FDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fNum = Integer.parseInt(request.getParameter("fNum"));
		FreeBoardDao Dao = new FreeBoardDao();
		int result = Dao.delete(fNum);
		if (result == FreeBoardDao.SUCCESS) {
			request.setAttribute("resultMsg", "글삭제 성공");
		} else {
			request.setAttribute("resultMsg", "글삭제 실패");
		}
	}
}