package kr.or.ddit.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.notice.model.NoticeVO;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.notice.service.NoticeService;
import kr.or.ddit.uploadFile.model.UploadFileVO;
import kr.or.ddit.uploadFile.service.IUploadFileService;
import kr.or.ddit.uploadFile.service.UploadFileService;
import kr.or.ddit.util.PartUtil;

@WebServlet("/updateNotice")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024*15)
public class UpdateNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private INoticeService noticeService;
	private IUploadFileService uploadFileService;
	private IBoardService boardService;
	private static final Logger logger = LoggerFactory
			.getLogger(UpdateNotice.class);
	
	@Override
	public void init() throws ServletException {
		noticeService = new NoticeService();
		uploadFileService = new UploadFileService();
		boardService = new BoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("updateNotice doGet()");
		
		String notiIdStr = request.getParameter("notiId");
		int notiId = Integer.parseInt(notiIdStr);
		
		NoticeVO noticeVo = noticeService.getNotice(notiId);
		
		request.setAttribute("noticeVo", noticeVo);
		request.getRequestDispatcher("/notice/updateNotice.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("updateNotice doPost()");
		request.setCharacterEncoding("UTF-8");
		
		String notiIdStr = request.getParameter("notiId");
		String title = request.getParameter("title");
		String content = request.getParameter("smarteditor");
		logger.debug("notiIdStr : {}", notiIdStr);
		logger.debug("title : {}", title);
		logger.debug("content : {}", content);
		
		int notiId = Integer.parseInt(notiIdStr);
		logger.debug("notiId : {}", notiId);
		
		List<UploadFileVO> dbUploadFileList = uploadFileService.getUploadFileList(notiId);
		
		uploadFileService.dbDeleteFile(dbUploadFileList);
		
		NoticeVO noticeVo = new NoticeVO(notiId, title, content);
		NoticeVO originallyNoticeVo = noticeService.getNotice(notiId);
		logger.debug("noticeVo : {}", noticeVo);
		logger.debug("noticeVo.getId : {}", noticeVo.getId());
		noticeService.updateNotice(noticeVo);
		BoardVO boardVo = boardService.getBoard(originallyNoticeVo.getId());
		logger.debug("boardVo : {}", boardVo);
		
		Part file = request.getPart("file");
		Part file2 = request.getPart("file2");
		Part file3 = request.getPart("file3");
		Part file4 = request.getPart("file4");
		Part file5 = request.getPart("file5");
		
		if (file != null && file.getSize() > 0 ) {
			PartUtil.uploadFile(file, boardVo , notiId);
		} if(file2 != null && file2.getSize() > 0 ){
			PartUtil.uploadFile(file2, boardVo , notiId);
			
		} if(file3 != null && file3.getSize() > 0 ){
			PartUtil.uploadFile(file3, boardVo , notiId);
			
		} if(file4 != null && file4.getSize() > 0 ){
			PartUtil.uploadFile(file4, boardVo , notiId);
			
		}if(file5 != null && file5.getSize() > 0 ){
			PartUtil.uploadFile(file5, boardVo , notiId);
		}
		List<UploadFileVO> uploadFileList = PartUtil.uploadFileList();
		for(UploadFileVO fileVo : uploadFileList){
			logger.debug("uploadFileId : {}", fileVo.getFileId());
		}
		uploadFileService.insertUploadFile(uploadFileList);
		PartUtil.uploadFileListClear();
		response.sendRedirect(request.getContextPath() + "/noticeDetail?notiId="+notiId);
	}

}
