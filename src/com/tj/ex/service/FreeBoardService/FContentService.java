package com.tj.ex.service.FreeBoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.FreeBoardDao;
import com.tj.ex.dto.FreeBoardDto;
import com.tj.ex.service.MemberService.Service;

public class FContentService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fNum = Integer.parseInt(request.getParameter("fNum"));
		FreeBoardDao boardDao = new FreeBoardDao();
		FreeBoardDto dto = boardDao.contentView(fNum);
		request.setAttribute("FreeBoardContentView", dto);
	}
}
