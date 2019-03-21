package com.tj.ex.service.IllustService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.IllustDao;
import com.tj.ex.dto.IllustDto;
import com.tj.ex.service.MemberService.Service;

public class IllustModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int iNum = Integer.parseInt(request.getParameter("iNum"));
		IllustDao Dao = new IllustDao();
		IllustDto dto = Dao.contentView(iNum);
		request.setAttribute("IllustModifyView", dto);
	}
}
