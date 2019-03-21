package com.tj.ex.service.LandmarkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.AnimalDao;
import com.tj.ex.service.MemberService.Service;

public class LandmarkDeleteService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int aNum = Integer.parseInt(request.getParameter("aNum"));
		AnimalDao Dao = new AnimalDao();
		int result = Dao.delete(aNum);
		if (result == AnimalDao.SUCCESS) {
			request.setAttribute("resultMsg", "글삭제 성공");
		} else {
			request.setAttribute("resultMsg", "글삭제 실패");
		}
	}
}