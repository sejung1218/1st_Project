package com.tj.ex.service.LandmarkService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.AnimalDao;
import com.tj.ex.dto.AnimalDto;
import com.tj.ex.service.MemberService.Service;

public class LandmarkContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int aNum = Integer.parseInt(request.getParameter("aNum"));
		AnimalDao Dao = new AnimalDao();
		AnimalDto dto = Dao.contentView(aNum);
		request.setAttribute("AnimalContentView", dto);
	}

}
