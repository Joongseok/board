package kr.or.ddit.board.dao;

import static org.junit.Assert.assertEquals;

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
	* Method : insertBoardTest
	* 작성자 : PC25
	* 변경이력 :
	* 테스트 ID : TSS-00
	* Method 설명 : 게시판 생성테스트 
	*/
	@Test
	public void insertBoardTest(){
		/***Given***/
		BoardVO boardVo = new BoardVO(boardDao.boardsCnt()+1, "brown", "자유게시판", "true");
		/***When***/
		
		int insertBoard = boardDao.insertBoard(boardVo);
		/***Then***/
		assertEquals(1, insertBoard);
	}
	
	/**
	* Method : updateBoardTest
	* 작성자 : PC25
	* 변경이력 :
	* 테스트 ID : TSS-01
	* Method 설명 : 게시판 사용여부 수정 테스트
	*/
	@Test
	public void updateBoardTest(){
		/***Given***/
		BoardVO boardVo = new BoardVO();
		boardVo.setId(boardDao.boardsCnt());
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
	 * 테스트 ID : TSS-02
	 * Method 설명 : 사용여부가 true인 boardList만 조회 테스트
	 */
	@Test
	public void boardListTest(){
		/***Given***/
		
		/***When***/
		List<BoardVO> boardList = boardDao.boardList();
		logger.debug("boardList : {}", boardList);
		/***Then***/
	}
	
	/**
	 * Method : boardAllListTest
	 * 작성자 : PC25
	 * 변경이력 :
	 * 테스트 ID : TSS-03
	 * Method 설명 : 사용여부와 상관없이 모든 boardList조회 테스트
	 */
	@Test
	public void boardAllListTest(){
		/***Given***/
		
		/***When***/
		List<BoardVO> boardList = boardDao.boardAllList();
		/***Then***/
		assertEquals(boardDao.boardsCnt(), boardList.size());
	}
	
}
