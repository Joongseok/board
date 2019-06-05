package kr.or.ddit.notice.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.notice.model.NoticeVO;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.notice.service.NoticeService;
import kr.or.ddit.uploadFile.model.UploadFileVO;
import kr.or.ddit.uploadFile.service.IUploadFileService;
import kr.or.ddit.uploadFile.service.UploadFileService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/noticeDetail")
public class NoticeDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private INoticeService noticeService;
	private IUploadFileService uploadFileService;
	
	@Override
	public void init() throws ServletException {
		noticeService = new NoticeService();
		uploadFileService = new UploadFileService();
	}
	
	private static final Logger logger = LoggerFactory
			.getLogger(NoticeDetail.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("noticeDetailController doGet()");
		
		String notiIdStr = request.getParameter("notiId");
		int notiId = Integer.parseInt(notiIdStr);
		
		NoticeVO noticeVo = noticeService.getNotice(notiId);
		List<UploadFileVO> uploadFileList =  uploadFileService.getUploadFileList(notiId);
				
		request.setAttribute("noticeVo", noticeVo);
		request.setAttribute("uploadFileList", uploadFileList);
		request.getRequestDispatcher("/notice/noticeDetail.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
