package kr.or.ddit.board.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoTest;
import kr.or.ddit.board.model.BoardVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardServiceTest {

	
	
private IBoardService boardService;
	
	private static final Logger logger = LoggerFactory
			.getLogger(BoardDaoTest.class);
	
	@Before
	public void setup(){
		boardService = new BoardService();
	}
	
	/**
	* Method : boardsCntTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 게시판 전체 수 조회 테스트
	*/
	@Test
	public void boardsCntTest() {
		/***Given***/
		

		/***When***/
		int boardsCnt = boardService.boardsCnt();
		logger.debug("boardsCnt : {}", boardsCnt);
		/***Then***/
		assertEquals(0, boardsCnt);
	}

	
	/**
	* Method : insertBoardTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 게시판 생성테스트
	*/
	@Test
	public void insertBoardTest(){
		/***Given***/
		BoardVO boardVo = new BoardVO(1, "brown", "자유게시판", "true");

		/***When***/
		int insertBoard = boardService.insertBoard(boardVo);
		int deleteBoard = boardService.deleteBoard(1);
		/***Then***/
		assertEquals(1, insertBoard);
		assertEquals(1, deleteBoard);

	}
	
	/**
	* Method : getBoardTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 게시판 선택 조회 테스트
	*/
	@Test
	public void getBoardTest(){
		/***Given***/
		int id = 3;

		/***When***/
		BoardVO boardVo = boardService.getBoard(id);
		logger.debug("boardVo : {}", boardVo);
		/***Then***/
		assertNotNull(boardVo);
	}
	
	@Test
	public void boardListTest(){
		/***Given***/
		

		/***When***/
		List<BoardVO> boardList = boardService.boardList();
		/***Then***/
		assertEquals(2, boardList.size());
	}
	
	
	/**
	* Method : boardAllListTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 사용여부와 상관없이 모든 boardList조회 테스트
	*/
	@Test
	public void boardAllListTest(){
		/***Given***/
		

		/***When***/
		List<BoardVO> boardList = boardService.boardAllList();
		/***Then***/
		assertEquals(3, boardList.size());
	}
}
