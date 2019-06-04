package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.user.model.UserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/createBoard")
public class CreateBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final Logger logger = LoggerFactory
			.getLogger(CreateBoardController.class);
	
	private IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("createBoardController doPost()");
		request.setCharacterEncoding("UTF-8");
		
		String createBoardName = request.getParameter("createBoardName");
		String use_yn = request.getParameter("use_yn");
		String userId = ((UserVO)request.getSession().getAttribute("USER_INFO")).getUserId();
		int id = boardService.boardsCnt() == 0 ? 1 : boardService.boardsCnt() + 1;
		
		logger.debug("createBoardName : {}", createBoardName);
		logger.debug("use_yn : {}", use_yn);
		logger.debug("userId : {}", userId);
		logger.debug("id : {}", id);
		
		BoardVO boardVo = new BoardVO(id, userId, createBoardName, use_yn);
		
		int insertBoard = boardService.insertBoard(boardVo);
		logger.debug("게시판 생성 성공");
		request.getSession().setAttribute("boardAllList", boardService.boardAllList());
		request.getSession().setAttribute("boardList", boardService.boardList());
		response.sendRedirect(request.getContextPath() + "/board/boardManager.jsp");
		
	}

}
