package com.tj.ex.service.IllustService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.IllustDao;
import com.tj.ex.service.MemberService.Service;

public class IllustDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int iNum = Integer.parseInt(request.getParameter("iNum"));
		IllustDao Dao = new IllustDao();
		int result = Dao.delete(iNum);
		if (result == IllustDao.SUCCESS) {
			request.setAttribute("resultMsg", "글삭제 성공");
		} else {
			request.setAttribute("resultMsg", "글삭제 실패");
		}
	}
}