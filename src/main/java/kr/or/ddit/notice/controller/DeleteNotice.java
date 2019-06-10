package kr.or.ddit.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.noti_comment.model.Noti_commentVO;
import kr.or.ddit.noti_comment.service.INoti_CommentService;
import kr.or.ddit.noti_comment.service.Noti_CommentService;
import kr.or.ddit.notice.model.NoticeVO;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.notice.service.NoticeService;

@WebServlet("/deleteNotice")
public class DeleteNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private INoticeService noticeService;
	private INoti_CommentService ntcService;
	
	@Override
	public void init() throws ServletException {
		noticeService = new NoticeService();
		ntcService = new Noti_CommentService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String notiIdStr = request.getParameter("notiId");
		
		// 게시글 번호
		int notiId = Integer.parseInt(notiIdStr);
		
		// 게시글 번호에 해당하는 게시글 정보
		NoticeVO ntVo = noticeService.getNotice(notiId);
		
		// 게시글 번호에 해당하는 댓글리스트
		List<Noti_commentVO> ntcList = ntcService.commentList(notiId);
		
		// 댓글리스트 삭제
		ntcService.deleteComment(ntcList);
		
		// 게시글 삭제
		noticeService.deleteNotice(notiId);
		
		// 해당 게시글이 있던 게시판리스트로 돌아감
		response.sendRedirect(request.getContextPath() + "/noticeController?id="+ntVo.getId());
	}

}
