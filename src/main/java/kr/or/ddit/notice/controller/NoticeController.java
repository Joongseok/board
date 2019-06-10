package kr.or.ddit.notice.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.notice.model.NoticeVO;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.notice.service.NoticeService;

@WebServlet("/noticeController")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private INoticeService noticeService;
	
	@Override
	public void init() throws ServletException {
		noticeService = new NoticeService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 해당 게시판의 번호
		int id = Integer.parseInt(request.getParameter("id")); 
		
		String pageStr = request.getParameter("page"); 
		String pageSizeStr = request.getParameter("pageSize"); 
		
		// 해당 페이지
		int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
		
		// 해당 페이지에 출력할 게시글 수
		int pageSize = pageSizeStr == null ? 10 : Integer.parseInt(pageSizeStr);
		
		// Map 객체에 게시판 번호, 페이지, 출력할 게시글 수를 넣는다.
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("id", id);
		pageMap.put("page", page);
		pageMap.put("pageSize", pageSize);
		
		// 페이징처리한 리스트와 페이지의 수를 구함
		Map<String, Object> resultMap = noticeService.noticePagingList(pageMap);
		int paginationSize = (int) resultMap.get("paginationSize");
		if (paginationSize == 0) {
			paginationSize = 1;
		}
		List<NoticeVO> noticeList = (List<NoticeVO>) resultMap.get("noticeList");
		
		request.setAttribute("pageMap", pageMap);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("paginationSize", paginationSize);
		
		// 게시글 페이징 리스트로 이동
		request.getRequestDispatcher("/notice/noticePagingList.jsp").forward(request, response);
	}

}
