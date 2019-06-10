package kr.or.ddit.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.user.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IUserService userService;
	private IBoardService boardService;
	@Override
	public void init() throws ServletException {
		userService = new UserService();
		boardService = new BoardService();
	}
	private static final Logger logger = LoggerFactory
			.getLogger(LoginController.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("LoginController doGet()");
		request.getRequestDispatcher("/login/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.debug("LoginController doPost()");
		
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		logger.debug("userId : {}", userId);
		logger.debug("password : {}", pass);
		String encryptPass = KISA_SHA256.encrypt(pass);
		
		UserVO userVo = userService.getUser(userId);
		
		if (userVo != null && userVo.getPass().equals(encryptPass)) {
			HttpSession session = request.getSession();
			session.setAttribute("USER_INFO", userVo);
			request.getSession().setAttribute("boardAllList", boardService.boardAllList());
			request.getSession().setAttribute("boardList", boardService.boardList());
			
			response.sendRedirect(request.getContextPath() + "/main.jsp");
		}else{
			request.setAttribute("userId", userId);
			request.getRequestDispatcher("/login/login.jsp").forward(request, response);
		}
		
	}

}
