package com.tj.ex.service.MemberService;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tj.ex.dao.MemberDao;
import com.tj.ex.dto.MemberDto;

public class MJoinService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		String mName = request.getParameter("mName");
		String mTel = request.getParameter("mTel");
		String mEmail = request.getParameter("mEmail");
		Date mBirth = null;
		String mBirthStr = request.getParameter("mBirth");
		if (!mBirthStr.equals("")) {
			mBirth = Date.valueOf(mBirthStr);
		}
		String mAddr = request.getParameter("mAddr");
		
		MemberDto dto = new MemberDto(mId, mPw, mName, mTel, mEmail, mBirth, mAddr);
		MemberDao dao = new MemberDao();
		
		int result = dao.mIdConfirm(mId);
		if (result == MemberDao.NONEXISTENT) {
			result = dao.joinMember(dto);
			if (result == MemberDao.SUCCESS) {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("mId", mId);
				request.setAttribute("msg", "회원가입이 성공되었습니다.");
			} else {
				request.setAttribute("errorMsg", "회원가입이 실패되었습니다.");
			}
		} else {
			request.setAttribute("errorMsg", "중복된 ID라서 회원가입이 불가능합니다.");
		}

	}

}



















