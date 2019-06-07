package kr.or.ddit.noti_comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.noti_comment.model.Noti_commentVO;
import kr.or.ddit.noti_comment.service.INoti_CommentService;
import kr.or.ddit.noti_comment.service.Noti_CommentService;
import kr.or.ddit.notice.model.NoticeVO;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.notice.service.NoticeService;

/**
 * Servlet implementation class DeleteComment
 */
@WebServlet("/deleteComment")
public class DeleteComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private INoticeService noticeService;
	private INoti_CommentService ntcService;
	
	@Override
	public void init() throws ServletException {
		noticeService  = new NoticeService();
		ntcService = new Noti_CommentService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String notiIdStr = request.getParameter("notiId");
		String cmtStr = request.getParameter("id");
		int id = Integer.parseInt(cmtStr);
		int notiId = Integer.parseInt(notiIdStr);
		NoticeVO noticeVo  = noticeService.getNotice(notiId);
		
		
		if (userId.equals(noticeVo.getUserid())) {
			Noti_commentVO ntcVo = ntcService.getCmt(id);
			ntcVo.setContent("삭제된 댓글입니다.");
			ntcVo.setDel_yn("false");
			ntcService.deleteCmt(ntcVo);
		}
		response.sendRedirect(request.getContextPath() + "/noticeDetail?notiId="+notiId);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
