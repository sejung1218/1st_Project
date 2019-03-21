package com.tj.ex.service.RequestBoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.dao.RequestBoardDao;
import com.tj.ex.dto.RequestBoardDto;
import com.tj.ex.service.MemberService.Service;

public class RReplyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int rNum = Integer.parseInt(request.getParameter("rNum"));
		RequestBoardDao Dao = new RequestBoardDao();
		RequestBoardDto dto = Dao.modifyView_replyView(rNum);
		request.setAttribute("RequestBoardReplyView", dto);
	}
}