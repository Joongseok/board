package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;

@WebServlet("/updateBoard")
public class UpdateBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private IBoardService boardService;
	private static final Logger logger = LoggerFactory
			.getLogger(UpdateBoardController.class);
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String use_yn = request.getParameter("updateUse_yn");
		
		logger.debug("updateBoardController id : {}", id);
		logger.debug("updateBoardController use_yn : {}", use_yn);
		
		BoardVO boardVo = boardService.getBoard(id);
		boardVo.setUse_yn(use_yn);
		logger.debug("updateBoardController boardVo : {}", boardVo);
		
		int updateBoard = boardService.updateBoard(boardVo);
		
		request.getSession().setAttribute("boardAllList", boardService.boardAllList());
		request.getSession().setAttribute("boardList", boardService.boardList());
		
		response.sendRedirect( request.getContextPath() + "/board/boardManager.jsp");
		
		
		
		
	}

}
