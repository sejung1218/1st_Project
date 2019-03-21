package com.tj.ex.service.PeopleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.PeopleDao;
import com.tj.ex.service.MemberService.Service;

public class PeopleDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int pNum = Integer.parseInt(request.getParameter("pNum"));
		PeopleDao Dao = new PeopleDao();
		int result = Dao.delete(pNum);
		if (result == PeopleDao.SUCCESS) {
			request.setAttribute("resultMsg", "글삭제 성공");
		} else {
			request.setAttribute("resultMsg", "글삭제 실패");
		}
	}
}