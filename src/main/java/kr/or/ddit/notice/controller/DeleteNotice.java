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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/deleteNotice")
public class DeleteNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory
			.getLogger(DeleteNotice.class);
	
	private INoticeService noticeService;
	private INoti_CommentService ntcService;
	
	@Override
	public void init() throws ServletException {
		noticeService = new NoticeService();
		ntcService = new Noti_CommentService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("delteNotice doGet()");
		
		String notiIdStr = request.getParameter("notiId");
		int notiId = Integer.parseInt(notiIdStr);
		String del_yn = "false";
		
		String title = "삭제된 게시글입니다.";
		
		
		NoticeVO noticeVo = new NoticeVO();
		
		noticeVo.setNotiId(notiId);
		
		NoticeVO ntVo = noticeService.getNotice(notiId);
		noticeVo.setTitle(title);
		noticeVo.setDel_yn(del_yn);
		
		List<Noti_commentVO> ntcList = ntcService.commentList(notiId);
		
		ntcService.deleteComment(ntcList);
		
		noticeService.deleteNotice(noticeVo);
		
		response.sendRedirect(request.getContextPath() + "/noticeController?id="+ntVo.getId());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
