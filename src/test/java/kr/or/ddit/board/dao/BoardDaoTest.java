package kr.or.ddit.board.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoardDaoTest {

	
	private IBoardDao boardDao;
	
	private static final Logger logger = LoggerFactory
			.getLogger(BoardDaoTest.class);
	
	@Before
	public void setup(){
		boardDao = new BoardDao();
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
		int boardsCnt = boardDao.boardsCnt();
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
		BoardVO boardVo = new BoardVO(1, "brown", "공지게시판", "true");

		/***When***/
		
		int insertBoard = boardDao.insertBoard(boardVo);
		int deleteBoard = boardDao.deleteBoard(1);
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
		BoardVO boardVo = boardDao.getBoard(id);
		logger.debug("boardVo : {}", boardVo);
		/***Then***/
		assertNotNull(boardVo);
	}
	
	/**
	* Method : updateBoardTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 게시판 사용여부 수정 테스트
	*/
	@Test
	public void updateBoardTest(){
		/***Given***/
		BoardVO boardVo = new BoardVO();
		boardVo.setId(3);
		boardVo.setUse_yn("true");
		/***When***/
		int updateBoard = boardDao.updateBoard(boardVo);
		/***Then***/
		assertEquals(1, updateBoard);
	}

	/**
	* Method : boardListTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 사용여부가 true인 boardList만 조회 테스트
	*/
	@Test
	public void boardListTest(){
		/***Given***/
		

		/***When***/
		List<BoardVO> boardList = boardDao.boardList();
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
		List<BoardVO> boardList = boardDao.boardAllList();
		/***Then***/
		assertEquals(3, boardList.size());
	}
}
