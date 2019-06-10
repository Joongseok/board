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
import kr.or.ddit.util.PartUtil;

@WebServlet("/updateNotice")
@MultipartConfig(maxFileSize=1024*1024*3, maxRequestSize=1024*1024*15)
public class UpdateNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private INoticeService noticeService;
	private IUploadFileService uploadFileService;
	private IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		noticeService = new NoticeService();
		uploadFileService = new UploadFileService();
		boardService = new BoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String notiIdStr = request.getParameter("notiId");
	
		// 수정할 게시글 번호
		int notiId = Integer.parseInt(notiIdStr);
		
		// 수정할 게시글의 정보
		NoticeVO noticeVo = noticeService.getNotice(notiId);
		
		// 게시글의 첨부파일 리스트
		List<UploadFileVO> fileList =  uploadFileService.getUploadFileList(notiId);
		
		request.setAttribute("noticeVo", noticeVo);
		request.setAttribute("fileList", fileList);
		
		// 게시글 수정 화면으로 이동
		request.getRequestDispatcher("/notice/updateNotice.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩
		request.setCharacterEncoding("UTF-8");
		
		String notiIdStr = request.getParameter("notiId");
		
		// 게시글 번호
		int notiId = Integer.parseInt(notiIdStr);
		
		// 제목
		String title = request.getParameter("title");
		
		// 내용
		String content = request.getParameter("smarteditor");
		
		// 수정전에 남아있던 첨부파일중 수정, 삭제된 첨부파일 아이디
		String deleteFileId1 = request.getParameter("deleteFileId1");
		String deleteFileId2 = request.getParameter("deleteFileId2");
		String deleteFileId3 = request.getParameter("deleteFileId3");
		String deleteFileId4 = request.getParameter("deleteFileId4");
		String deleteFileId5 = request.getParameter("deleteFileId5");
		String[] fileIds = {deleteFileId1, deleteFileId2, deleteFileId3, deleteFileId4, deleteFileId5};
		
		List<UploadFileVO> dbUploadFileList = new ArrayList<UploadFileVO>();
			for(String fileid : fileIds){
				if (fileid !=null) {
					dbUploadFileList.add(uploadFileService.getFileVo(fileid));
				}
			}
		if (dbUploadFileList.size() != 0) {
			// 수정되고 없어진 첨부파일 삭제
			uploadFileService.dbDeleteFile(dbUploadFileList);
		}
		
		// 수정된 내용을 담는 게시글 객체
		NoticeVO noticeVo = new NoticeVO(notiId, title, content);
		
		// 수정전 내용이 담긴 게시글 객체
		NoticeVO originallyNoticeVo = noticeService.getNotice(notiId);
		
		// 게시글 수정
		noticeService.updateNotice(noticeVo);
		
		// 첨부파일 리스트
		List<Part> partList = new ArrayList<Part>();
		for(Part file : request.getParts()){
			if ("files".equals(file.getName()) && file.getSize() > 0) {
				partList.add(file);
			}
		}
		
		BoardVO boardVo = boardService.getBoard(originallyNoticeVo.getId());
		PartUtil.uploadFile(partList, boardVo, notiId);
		List<UploadFileVO> uploadFileList = PartUtil.uploadFileList();
		
		// 첨부파일 DB에 저장
		uploadFileService.insertUploadFile(uploadFileList);
		PartUtil.uploadFileListClear();
		
		// 수정된 게시글로 이동
		response.sendRedirect(request.getContextPath() + "/noticeDetail?notiId="+notiId);
	}

}
