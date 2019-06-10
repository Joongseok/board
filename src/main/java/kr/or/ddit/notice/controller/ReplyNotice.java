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
import kr.or.ddit.uploadFile.service.UploadFileService;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.util.PartUtil;

@WebServlet("/replyNotice")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024*15)
public class ReplyNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
		String notiIdStr = request.getParameter("notiId");
		
		//  부모 게시글 번호
		int notiId = Integer.parseInt(notiIdStr);
		request.setAttribute("notiId", notiId);
		
		// 답글 화면으로 이동
		request.getRequestDispatcher("/notice/replyNotice.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩
		request.setCharacterEncoding("utf-8");
		String notiIdStr = request.getParameter("notiId");
		
		// 부모 게시글의 번호
		int notiId = Integer.parseInt(notiIdStr);
		
		// 부모 게시글의 정보
		NoticeVO noticeVo = noticeService.getNotice(notiId);
		
		// 작성자
		String userId = ((UserVO)request.getSession().getAttribute("USER_INFO")).getUserId();
		
		// 제목
		String title = request.getParameter("title"); 
		
		// 내용
		String content = request.getParameter("smarteditor"); 
		
		// 답글 번호
		int createNotiId = noticeService.noticeAllCnt() == 0 ? 1 : noticeService.noticeMaxId();
		
		NoticeVO createNoticeVo = new NoticeVO(createNotiId, userId, title, content, notiId ,noticeVo.getId(), noticeVo.getGroupId());
		
		// 답글 작성
		noticeService.replyNotice(createNoticeVo);
		
		// 첨부파일 리스트
		List<Part> partList = new ArrayList<Part>();
		for(Part file : request.getParts()){
			if ("files".equals(file.getName()) && file.getSize() > 0) {
				partList.add(file);
			}
		}
		
		BoardVO boardVo = boardService.getBoard(noticeVo.getId());
		PartUtil.uploadFile(partList, boardVo, createNotiId);
		List<UploadFileVO> uploadFileList = PartUtil.uploadFileList();
		
		// 첨부파일 작성
		uploadFileService.insertUploadFile(uploadFileList);
		PartUtil.uploadFileListClear();
		
		// 답글 게시글로 이동
		response.sendRedirect(request.getContextPath() + "/noticeDetail?notiId="+createNotiId);
	}

}
