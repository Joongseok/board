package kr.or.ddit.noti_comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.noti_comment.service.INoti_CommentService;
import kr.or.ddit.noti_comment.service.Noti_CommentService;

@WebServlet("/deleteComment")
public class DeleteComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private INoti_CommentService ntcService;
	
	@Override
	public void init() throws ServletException {
		ntcService = new Noti_CommentService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String notiIdStr = request.getParameter("notiId");
		String ntcStr = request.getParameter("id");
		
		// 게시글 번호
		int ntcId = Integer.parseInt(ntcStr);
		
		// 댓글 번호
		int notiId = Integer.parseInt(notiIdStr);
		
		// 댓글 번호에 해당하는 댓글 삭제
		ntcService.deleteCmt(ntcId);
		
		// 해당 게시글로 돌아감
		response.sendRedirect(request.getContextPath() + "/noticeDetail?notiId="+notiId);
	}

}
