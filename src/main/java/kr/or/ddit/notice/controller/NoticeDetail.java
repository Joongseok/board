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
import kr.or.ddit.uploadFile.model.UploadFileVO;
import kr.or.ddit.uploadFile.service.IUploadFileService;
import kr.or.ddit.uploadFile.service.UploadFileService;

@WebServlet("/noticeDetail")
public class NoticeDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private INoticeService noticeService;
	private INoti_CommentService ntcService;
	private IUploadFileService uploadFileService;
	
	@Override
	public void init() throws ServletException {
		noticeService = new NoticeService();
		ntcService = new Noti_CommentService();
		uploadFileService = new UploadFileService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String notiIdStr = request.getParameter("notiId");
		
		// 해당 게시글 번호
		int notiId = Integer.parseInt(notiIdStr);
		
		// 게시글 번호에 해당하는 게시글 정보
		NoticeVO noticeVo = noticeService.getNotice(notiId);
		
		// 게시글 번호에 해당하는 첨부파일 리스트
		List<UploadFileVO> uploadFileList =  uploadFileService.getUploadFileList(notiId);
		
		// 게시글 번호에 해당하는 댓글 리스트
		List<Noti_commentVO> ntcList = ntcService.commentList(notiId);
				
		request.setAttribute("noticeVo", noticeVo);
		request.setAttribute("uploadFileList", uploadFileList);
		request.setAttribute("ntcList", ntcList);
		
		// 조회 화면으로 이동
		request.getRequestDispatcher("/notice/noticeDetail.jsp").forward(request, response);
		
	}
}
