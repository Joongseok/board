package kr.or.ddit.notice.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.notice.model.NoticeVO;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.notice.service.NoticeService;
import kr.or.ddit.uploadFile.model.UploadFileVO;
import kr.or.ddit.uploadFile.service.IUploadFileService;
import kr.or.ddit.uploadFile.service.UploadFileService;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.PartUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/noticeForm")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024*15)
public class NoticeFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory
			.getLogger(NoticeFormController.class);
	
	private IBoardService boardService;
	private IUploadFileService uploadFileService;
	private INoticeService noticeService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
		noticeService = new NoticeService();
		uploadFileService = new UploadFileService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("noticeFormController doGet()");
		
		request.setAttribute("id", request.getParameter("id"));
		logger.debug("notiForm doGet() boardVo.id : {}", request.getParameter("id"));
		request.getRequestDispatcher("/notice/noticeForm.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 인코딩을 위한 UTF-8 설정
		logger.debug("noticeFormController doPost()");
		request.setCharacterEncoding("utf-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		BoardVO boardVo = boardService.getBoard(id);
		
		String userId = ((UserVO)request.getSession().getAttribute("USER_INFO")).getUserId();
		logger.debug("notiForm doPost() boardVo  : {} ", boardVo);
		
		String title = request.getParameter("title"); // 제목
		String content = request.getParameter("smarteditor"); //내용
		logger.debug("title : {}", title);
		logger.debug("content : {}", content);
		int notiId = noticeService.noticeAllCnt() == 0 ? 1 : noticeService.noticeAllCnt() + 1;
		NoticeVO noticeVo = new NoticeVO(notiId, userId, title, content, id);
		logger.debug("noticeVo : {}", noticeVo);
		noticeService.insertNotice(noticeVo);
		
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
		uploadFileService.insertUploadFile(uploadFileList);
		
		response.sendRedirect(request.getContextPath() + "/main.jsp");
		
	}

}
