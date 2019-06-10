package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;

@WebServlet("/boardManager")
public class BoardManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BoardVO> boardList = boardService.boardList(); 
		if (boardList != null) {
			request.getSession().setAttribute("boardAllList", boardService.boardAllList());
			request.getSession().setAttribute("boardList", boardService.boardList());
		}
		request.getRequestDispatcher("/board/boardManager.jsp").forward(request, response);
	}

}
