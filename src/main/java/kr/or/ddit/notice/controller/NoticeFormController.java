package kr.or.ddit.notice.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import kr.or.ddit.uploadFile.service.IUploadFileService;
import kr.or.ddit.uploadFile.service.UploadFileService;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.PartUtil;

@WebServlet("/noticeForm")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024*15)
public class NoticeFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		// 게시판 번호
		request.setAttribute("id", request.getParameter("id"));
		
		// 게시판 번호를 가지고 게시글 작성화면으로 이동
		request.getRequestDispatcher("/notice/noticeForm.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩
		request.setCharacterEncoding("utf-8");
		
		// 게시판 번호
		int id = Integer.parseInt(request.getParameter("id"));
		
		// 작성자
		String userId = ((UserVO)request.getSession().getAttribute("USER_INFO")).getUserId();
		
		// 제목
		String title = request.getParameter("title"); 
		
		// 게시글 내용
		String content = request.getParameter("smarteditor"); 
		
		// 게시글 번호
		int notiId = noticeService.noticeAllCnt() == 0 ? 1 : noticeService.noticeMaxId();
		
		NoticeVO noticeVo = new NoticeVO(notiId, userId, title, content, id, notiId);
		
		// 게시글 작성
		noticeService.insertNotice(noticeVo);
		
		// 첨부파일 리스트
		List<Part> partList = new ArrayList<Part>();
		for(Part file : request.getParts()){
			if ("files".equals(file.getName()) && file.getSize() > 0) {
				partList.add(file);
			}
		}

		BoardVO boardVo = boardService.getBoard(id);
		PartUtil.uploadFile(partList, boardVo, notiId);
		List<UploadFileVO> uploadFileList = PartUtil.uploadFileList();
		
		// 첨부파일 insert
		uploadFileService.insertUploadFile(uploadFileList);
		PartUtil.uploadFileListClear();
		
		// 작성한 게시글로 이동
		response.sendRedirect(request.getContextPath() + "/noticeDetail?notiId="+notiId);
		
	}

}
