package com.tj.ex.service.PeopleService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.PeopleDao;
import com.tj.ex.dto.PeopleDto;
import com.tj.ex.service.MemberService.Service;

public class PeopleContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int pNum = Integer.parseInt(request.getParameter("pNum"));
		PeopleDao Dao = new PeopleDao();
		PeopleDto dto = Dao.contentView(pNum);
		request.setAttribute("PeopleContentView", dto);
	}

}
