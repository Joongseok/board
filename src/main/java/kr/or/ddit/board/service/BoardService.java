package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.model.BoardVO;

public class BoardService implements IBoardService {

	private IBoardDao boardDao = new BoardDao();
	
	/**
	* Method : boardsCnt
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 전체 수 조회
	*/
	@Override
	public int boardsCnt() {
		return boardDao.boardsCnt();
	}

	/**
	* Method : insertBoard
	* 작성자 : PC25
	* 변경이력 :
	* @param boardVo
	* @return
	* Method 설명 : 게시판 생성
	*/
	@Override
	public int insertBoard(BoardVO boardVo) {
		
		return boardDao.insertBoard(boardVo);
	}

	/**
	* Method : deleteBoard
	* 작성자 : PC25
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 게시판 삭제
	*/
	@Override
	public int deleteBoard(int id) {
		return boardDao.deleteBoard(id);
	}

	/**
	* Method : boardList
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 사용여부가 true인 boardList 조회
	*/
	@Override
	public List<BoardVO> boardList() {
		return boardDao.boardList();
	}

	/**
	* Method : getBoard
	* 작성자 : PC25
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 게시판 선택조회
	*/
	@Override
	public BoardVO getBoard(int id) {
		return boardDao.getBoard(id);
	}

	/**
	* Method : updateBoard
	* 작성자 : PC25
	* 변경이력 :
	* @param boardVo
	* @return
	* Method 설명 : 게시판 사용여부 수정
	*/
	@Override
	public int updateBoard(BoardVO boardVo) {
		return boardDao.updateBoard(boardVo);
	}

	@Override
	public List<BoardVO> boardAllList() {
		return boardDao.boardAllList();
	}

	
}
