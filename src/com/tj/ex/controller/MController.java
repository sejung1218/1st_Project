package com.tj.ex.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tj.ex.service.AnimalService.AnimalContentService;
import com.tj.ex.service.AnimalService.AnimalDeleteService;
import com.tj.ex.service.AnimalService.AnimalListService;
import com.tj.ex.service.AnimalService.AnimalModifyService;
import com.tj.ex.service.AnimalService.AnimalModifyViewService;
import com.tj.ex.service.AnimalService.AnimalWriteService;
import com.tj.ex.service.FreeBoardService.FContentService;
import com.tj.ex.service.FreeBoardService.FDeleteService;
import com.tj.ex.service.FreeBoardService.FListService;
import com.tj.ex.service.FreeBoardService.FModifyService;
import com.tj.ex.service.FreeBoardService.FModifyViewService;
import com.tj.ex.service.FreeBoardService.FReplyService;
import com.tj.ex.service.FreeBoardService.FReplyViewService;
import com.tj.ex.service.FreeBoardService.FWriteService;
import com.tj.ex.service.IllustService.IllustContentService;
import com.tj.ex.service.IllustService.IllustDeleteService;
import com.tj.ex.service.IllustService.IllustListService;
import com.tj.ex.service.IllustService.IllustModifyService;
import com.tj.ex.service.IllustService.IllustModifyViewService;
import com.tj.ex.service.IllustService.IllustWriteService;
import com.tj.ex.service.MemberService.IdConfirmService;
import com.tj.ex.service.MemberService.MJoinService;
import com.tj.ex.service.MemberService.MLoginService;
import com.tj.ex.service.MemberService.MLogoutService;
import com.tj.ex.service.MemberService.MModifyService;
import com.tj.ex.service.MemberService.Service;
import com.tj.ex.service.PeopleService.PeopleContentService;
import com.tj.ex.service.PeopleService.PeopleDeleteService;
import com.tj.ex.service.PeopleService.PeopleListService;
import com.tj.ex.service.PeopleService.PeopleModifyService;
import com.tj.ex.service.PeopleService.PeopleModifyViewService;
import com.tj.ex.service.PeopleService.PeopleWriteService;
import com.tj.ex.service.RequestBoardService.RContentService;
import com.tj.ex.service.RequestBoardService.RDeleteService;
import com.tj.ex.service.RequestBoardService.RListService;
import com.tj.ex.service.RequestBoardService.RModifyService;
import com.tj.ex.service.RequestBoardService.RModifyViewService;
import com.tj.ex.service.RequestBoardService.RReplyService;
import com.tj.ex.service.RequestBoardService.RReplyViewService;
import com.tj.ex.service.RequestBoardService.RWriteService;

@WebServlet("*.do")
public class MController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private int write_view = 0;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com = uri.substring(conPath.length());
		String viewPage = null;
		Service service = null;

//idConfirm ////////////////////////////////

		if (com.equals("/mIdConfirm.do")) {
			service = new IdConfirmService();
			service.execute(request, response);
			viewPage = "Member/idConfirm.jsp";
		}

//joinView.do, join.do//////////////////////////////////////////////////////////////////////////////////////////////

		if (com.equals("/joinView.do")) {
			viewPage = "Member/join.jsp";

		} else if (com.equals("/join.do")) {
			service = new MJoinService();
			service.execute(request, response);
			viewPage = "Member/login.jsp";

//loginView.do, login.do////////////////////////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/loginView.do")) {
			viewPage = "Member/login.jsp";

		} else if (com.equals("/login.do")) {
			service = new MLoginService();
			service.execute(request, response);
			viewPage = "main.do";

//logout.do/////////////////////////////////////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/logout.do")) {
			service = new MLogoutService();
			service.execute(request, response);
			viewPage = "loginView.do";

//modifyView.do, modify.do//////////////////////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/modifyView.do")) {
			viewPage = "Member/modify.jsp";

		} else if (com.equals("/modify.do")) {
			service = new MModifyService();
			service.execute(request, response);
			viewPage = "main.do";

//main.do///////////////////////////////////////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/main.do")) {
			viewPage = "Main/main.jsp";

		} else if (com.equals("/myInfo.do")) {
			viewPage = "Member/myInfo.jsp";

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////FREEBOARD//////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//FreeBoardList.do//////////////////////////////////////////////////////////////////////////////////////////////////	

		} else if (com.equals("/FreeBoardList.do")) {
			service = new FListService();
			service.execute(request, response);
			viewPage = "FreeBoard/FreeBoardList.jsp";

//FreeBoardWriteView.do, FreeBoardWrite.do//////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/FreeBoardWriteView.do")) {
			write_view = 1;
			viewPage = "FreeBoard/FreeBoardWrite.jsp";

		} else if (com.equals("/FreeBoardWrite.do")) {
			if (write_view == 1) {
				service = new FWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "FreeBoardList.do";

//FreeBoardContentView.do///////////////////////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/FreeBoardContentView.do")) {
			service = new FContentService();
			service.execute(request, response);
			viewPage = "FreeBoard/FreeBoardContent.jsp";

//FreeBoardModifyView.do, FreeBoardModify.do////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/FreeBoardModifyView.do")) {
			service = new FModifyViewService();
			service.execute(request, response);

			viewPage = "FreeBoard/FreeBoardModify.jsp";
		} else if (com.equals("/FreeBoardModify.do")) {
			service = new FModifyService();
			service.execute(request, response);
			viewPage = "FreeBoardList.do";

//FreeBoardReplyView.do, FreeBoardReply.do//////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/FreeBoardReplyView.do")) {
			service = new FReplyViewService();
			service.execute(request, response);
			viewPage = "FreeBoard/FreeBoardReply.jsp";

		} else if (com.equals("/FreeBoardReply.do")) {
			service = new FReplyService();
			service.execute(request, response);
			viewPage = "FreeBoardList.do";

//FreeBoardDelete.do////////////////////////////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/FreeBoardDelete.do")) {
			service = new FDeleteService();
			service.execute(request, response);

			viewPage = "FreeBoardList.do";

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////REQUESTBOARD////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// RequestBoardList.do//////////////////////////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/RequestBoardList.do")) {
			service = new RListService();
			service.execute(request, response);
			viewPage = "RequestBoard/RequestBoardList.jsp";

// RequestBoardWriteView.do, RequestBoardWrite.do///////////////////////////////////////////////////////////////////

		} else if (com.equals("/RequestBoardWriteView.do")) {
			write_view = 1;
			viewPage = "RequestBoard/RequestBoardWrite.jsp";

		} else if (com.equals("/RequestBoardWrite.do")) {
			if (write_view == 1) {
				service = new RWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "RequestBoardList.do";

// RequestBoardContentView.do///////////////////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/RequestBoardContentView.do")) {
			service = new RContentService();
			service.execute(request, response);
			viewPage = "RequestBoard/RequestBoardContent.jsp";

// RequestBoardModifyView.do, RequestBoardModify.do/////////////////////////////////////////////////////////////////

		} else if (com.equals("/RequestBoardModifyView.do")) {
			service = new RModifyViewService();
			service.execute(request, response);
			viewPage = "RequestBoard/RequestBoardModify.jsp";

		} else if (com.equals("/RequestBoardModify.do")) {
			service = new RModifyService();
			service.execute(request, response);
			viewPage = "RequestBoardList.do";

// RequestBoardReplyView.do, RequestBoardReply.do///////////////////////////////////////////////////////////////////

		} else if (com.equals("/RequestBoardReplyView.do")) {
			service = new RReplyViewService();
			service.execute(request, response);
			viewPage = "RequestBoard/RequestBoardReply.jsp";

		} else if (com.equals("/RequestBoardReply.do")) {
			service = new RReplyService();
			service.execute(request, response);
			viewPage = "RequestBoardList.do";

// RequestBoardDelete.do////////////////////////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/RequestBoardDelete.do")) {
			service = new RDeleteService();
			service.execute(request, response);
			viewPage = "RequestBoardList.do";

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////1_ANIMALBOARD/////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////			

// AnimalList.do//////////////////////////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/AnimalList.do")) {
			service = new AnimalListService();
			service.execute(request, response);
			viewPage = "1_Animal/AnimalList.jsp";

// AnimalWriteView.do, AnimalWrite.do///////////////////////////////////////////////////////////////////

		} else if (com.equals("/AnimalWriteView.do")) {
			write_view = 1;
			viewPage = "1_Animal/AnimalWrite.jsp";

		} else if (com.equals("/AnimalWrite.do")) {
			if (write_view == 1) {
				service = new AnimalWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "AnimalList.do";

// AnimalContentView.do///////////////////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/AnimalContentView.do")) {
			service = new AnimalContentService();
			service.execute(request, response);
			viewPage = "1_Animal/AnimalContent.jsp";

// AnimalModifyView.do, AnimalModify.do/////////////////////////////////////////////////////////////////

		} else if (com.equals("/AnimalModifyView.do")) {
			service = new AnimalModifyViewService();
			service.execute(request, response);
			viewPage = "1_Animal/AnimalModify.jsp";

		} else if (com.equals("/AnimalModify.do")) {
			service = new AnimalModifyService();
			service.execute(request, response);
			viewPage = "AnimalList.do";

// AnimalDelete.do////////////////////////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/AnimalDelete.do")) {
			service = new AnimalDeleteService();
			service.execute(request, response);
			viewPage = "AnimalList.do";

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////2_PEOPLEBOARD/////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//PeopleList.do//////////////////////////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/PeopleList.do")) {
			service = new PeopleListService();
			service.execute(request, response);
			viewPage = "2_People/PeopleList.jsp";

//PeopleWriteView.do, PeopleWrite.do///////////////////////////////////////////////////////////////////

		} else if (com.equals("/PeopleWriteView.do")) {
			write_view = 1;
			viewPage = "2_People/PeopleWrite.jsp";

		} else if (com.equals("/PeopleWrite.do")) {
			if (write_view == 1) {
				service = new PeopleWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "PeopleList.do";

//PeopleContentView.do///////////////////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/PeopleContentView.do")) {
			service = new PeopleContentService();
			service.execute(request, response);
			viewPage = "2_People/PeopleContent.jsp";

//PeopleModifyView.do, PeopleModify.do/////////////////////////////////////////////////////////////////

		} else if (com.equals("/PeopleModifyView.do")) {
			service = new PeopleModifyViewService();
			service.execute(request, response);
			viewPage = "2_People/PeopleModify.jsp";

		} else if (com.equals("/PeopleModify.do")) {
			service = new PeopleModifyService();
			service.execute(request, response);
			viewPage = "PeopleList.do";

//PeopleDelete.do////////////////////////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/PeopleDelete.do")) {
			service = new PeopleDeleteService();
			service.execute(request, response);
			viewPage = "PeopleList.do";

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////5_ILLUSTBOARD/////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//IllustList.do//////////////////////////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/IllustList.do")) {
			service = new IllustListService();
			service.execute(request, response);
			viewPage = "5_Illust/IllustList.jsp";

//IllustWriteView.do, IllustWrite.do///////////////////////////////////////////////////////////////////

		} else if (com.equals("/IllustWriteView.do")) {
			write_view = 1;
			viewPage = "5_Illust/IllustWrite.jsp";

		} else if (com.equals("/IllustWrite.do")) {
			if (write_view == 1) {
				service = new IllustWriteService();
				service.execute(request, response);
				write_view = 0;
			}
			viewPage = "IllustList.do";

//IllustContentView.do///////////////////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/IllustContentView.do")) {
			service = new IllustContentService();
			service.execute(request, response);
			viewPage = "5_Illust/IllustContent.jsp";

//IllustModifyView.do, IllustModify.do/////////////////////////////////////////////////////////////////

		} else if (com.equals("/IllustModifyView.do")) {
			service = new IllustModifyViewService();
			service.execute(request, response);
			viewPage = "5_Illust/IllustModify.jsp";

		} else if (com.equals("/IllustModify.do")) {
			service = new IllustModifyService();
			service.execute(request, response);
			viewPage = "IllustList.do";

//IllustDelete.do////////////////////////////////////////////////////////////////////////////////////////////

		} else if (com.equals("/IllustDelete.do")) {
			service = new IllustDeleteService();
			service.execute(request, response);
			viewPage = "IllustList.do";

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////BOARD/////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

}
