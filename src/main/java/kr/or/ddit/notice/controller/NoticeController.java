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

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.notice.model.NoticeVO;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.notice.service.NoticeService;
import kr.or.ddit.paging.model.PageVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/noticeController")
public class NoticeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory
			.getLogger(NoticeController.class);

	
	private INoticeService noticeService;
	private IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		noticeService = new NoticeService();
		boardService = new BoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("noticeController doGet()");
		logger.debug("id : {}", request.getParameter("id"));
		int id = Integer.parseInt(request.getParameter("id"));
		
		BoardVO boardVo = boardService.getBoard(id);
		Map<String, Object> map = new HashMap<String, Object>();
		String pageStr = request.getParameter("page");
		String pageSizeStr = request.getParameter("pageSize");
		
		int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
		int pageSize = pageSizeStr == null ? 10 : Integer.parseInt(pageSizeStr);
		
		map.put("id", id);
		map.put("page", page);
		map.put("pageSize", pageSize);
		
		Map<String, Object> resultMap = noticeService.noticeList(boardVo, map);
		
		List<NoticeVO> noticeList = (List<NoticeVO>) resultMap.get("noticeList");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		logger.debug("paginationSize : {}", paginationSize);
		PageVO pageVo = new PageVO(page, pageSize);
		
		request.setAttribute("boardVo", boardVo);
		request.setAttribute("pageVo", pageVo);
		request.setAttribute("noticeList", noticeList);
		request.setAttribute("paginationSize", paginationSize);
		request.getRequestDispatcher("/notice/noticePagingList.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
