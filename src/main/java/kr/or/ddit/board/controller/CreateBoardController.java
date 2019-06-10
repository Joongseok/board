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

@WebServlet("/createBoard")
public class CreateBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IBoardService boardService;
	
	@Override
	public void init() throws ServletException {
		boardService = new BoardService();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String createBoardName = request.getParameter("createBoardName");
		String use_yn = request.getParameter("use_yn");
		String userId = ((UserVO)request.getSession().getAttribute("USER_INFO")).getUserId();
		int id = boardService.boardsCnt() == 0 ? 1 : boardService.boardsCnt() + 1;
		
		BoardVO boardVo = new BoardVO(id, userId, createBoardName, use_yn);
		
		boardService.insertBoard(boardVo);
		request.getSession().setAttribute("boardAllList", boardService.boardAllList());
		request.getSession().setAttribute("boardList", boardService.boardList());
		response.sendRedirect(request.getContextPath() + "/board/boardManager.jsp");
	}

}
