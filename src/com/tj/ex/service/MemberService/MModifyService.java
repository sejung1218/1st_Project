package com.tj.ex.service.MemberService;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.MemberDao;
import com.tj.ex.dto.MemberDto;

public class MModifyService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		String mName = request.getParameter("mName");
		String mTel = request.getParameter("mTel");
		String mEmail = request.getParameter("mEmail");
		Date mBirth = Date.valueOf(request.getParameter("mBirth"));
		String mAddr = request.getParameter("mAddr");

		MemberDto dto = new MemberDto(mId, mPw, mName, mTel, mEmail, mBirth, mAddr);
		MemberDao dao = new MemberDao();

		int result = dao.modifyMember(dto);
		if (result == MemberDao.SUCCESS) {
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("member", dto);
			request.setAttribute("modifyResultMsg", "회원정보 수정 성공");
		} else {
			request.setAttribute("modifyResultMsg", "회원정보 수정 실패");
		}
	}

}
