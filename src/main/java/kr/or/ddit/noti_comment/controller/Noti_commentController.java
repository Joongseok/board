package kr.or.ddit.noti_comment.controller;

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
import kr.or.ddit.uploadFile.model.UploadFileVO;
import kr.or.ddit.uploadFile.service.IUploadFileService;
import kr.or.ddit.uploadFile.service.UploadFileService;
import kr.or.ddit.user.model.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/noti_comment")
public class Noti_commentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private INoti_CommentService noti_commentService;
	private IUploadFileService uploadFileService;
	private INoticeService noticeService;
	@Override
	public void init() throws ServletException {
		noti_commentService = new Noti_CommentService();
		uploadFileService = new UploadFileService();
		noticeService =new NoticeService();
	}
	
	private static final Logger logger = LoggerFactory
			.getLogger(Noti_commentController.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		logger.debug("noti_commentController doPost()");
		String notiIdStr = request.getParameter("cntNotiId");
		int notiId = Integer.parseInt(notiIdStr);
		String comment = request.getParameter("comment");
		String userId = ((UserVO)request.getSession().getAttribute("USER_INFO")).getUserId();
		int id = noti_commentService.commentAllCnt() == 0 ? 1 : noti_commentService.commentMaxId() + 1;
		
		Noti_commentVO ntcVo = new Noti_commentVO(id, notiId, userId, comment);
		
		List<Noti_commentVO> ntcVoList = noti_commentService.commentList(notiId);
		noti_commentService.insertComment(ntcVo);
		List<UploadFileVO> uploadFileList = uploadFileService.getUploadFileList(notiId);
		NoticeVO noticeVo = noticeService.getNotice(notiId);
		
		response.sendRedirect(request.getContextPath() + "/noticeDetail?notiId="+notiId);
	}

}
