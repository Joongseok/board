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

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.notice.model.NoticeVO;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.notice.service.NoticeService;
import kr.or.ddit.uploadFile.model.UploadFileVO;
import kr.or.ddit.uploadFile.service.UploadFileService;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.PartUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/replyNotice")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024*15)
public class ReplyNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private static final Logger logger = LoggerFactory
			.getLogger(ReplyNotice.class);
	
	private INoticeService noticeService;
	private IBoardService boardService;
	private UploadFileService uploadFileService;
	
	@Override
	public void init() throws ServletException {
		noticeService = new NoticeService();
		boardService = new BoardService();
		uploadFileService = new UploadFileService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("replyNotice doGet()");
		
		String notiIdStr = request.getParameter("notiId");
		logger.debug("notiIdStr : {}", notiIdStr);
		
		int notiId = Integer.parseInt(notiIdStr);
		
		request.setAttribute("notiId", notiId);
		request.getRequestDispatcher("/notice/replyNotice.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 인코딩을 위한 UTF-8 설정
				logger.debug("noticeFormController doPost()");
				request.setCharacterEncoding("utf-8");
				String notiIdStr = request.getParameter("notiId");
				int notiId = Integer.parseInt(notiIdStr);
				
				NoticeVO noticeVo = noticeService.getNotice(notiId);
				
				BoardVO boardVo = boardService.getBoard(noticeVo.getId());
				
				
				String userId = ((UserVO)request.getSession().getAttribute("USER_INFO")).getUserId();
				logger.debug("notiForm doPost() boardVo  : {} ", boardVo);
				
				String title = request.getParameter("title"); // 제목
				String content = request.getParameter("smarteditor"); //내용
				logger.debug("title : {}", title);
				logger.debug("content : {}", content);
				int createNotiId = noticeService.noticeAllCnt() == 0 ? 1 : noticeService.noticeMaxId() + 1;
				NoticeVO createNoticeVo = new NoticeVO(createNotiId, userId, title, content, notiId ,noticeVo.getId(), noticeVo.getGroupId());
				logger.debug("");
				logger.debug("noticeVo : {}", createNoticeVo);
				noticeService.replyNotice(createNoticeVo);
				Part file = request.getPart("file");
				Part file2 = request.getPart("file2");
				Part file3 = request.getPart("file3");
				Part file4 = request.getPart("file4");
				Part file5 = request.getPart("file5");
				
				if (file != null && file.getSize() > 0 ) {
					PartUtil.uploadFile(file, boardVo , createNotiId);
				} if(file2 != null && file2.getSize() > 0 ){
					PartUtil.uploadFile(file2, boardVo , createNotiId);
					
				} if(file3 != null && file3.getSize() > 0 ){
					PartUtil.uploadFile(file3, boardVo , createNotiId);
					
				} if(file4 != null && file4.getSize() > 0 ){
					PartUtil.uploadFile(file4, boardVo , createNotiId);
					
				}if(file5 != null && file5.getSize() > 0 ){
					PartUtil.uploadFile(file5, boardVo , createNotiId);
				}
				List<UploadFileVO> uploadFileList = PartUtil.uploadFileList();
				for(UploadFileVO fileVo : uploadFileList){
					logger.debug("uploadFileId : {}", fileVo.getFileId());
				}
				uploadFileService.insertUploadFile(uploadFileList);
				PartUtil.uploadFileListClear();
				response.sendRedirect(request.getContextPath() + "/noticeDetail?notiId="+createNotiId);
	}

}
