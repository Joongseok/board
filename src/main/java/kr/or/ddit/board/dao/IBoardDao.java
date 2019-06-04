package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;

public interface IBoardDao {
	/**
	* Method : boardsCnt
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 전체 수 조회
	*/
	int boardsCnt();
	
	/**
	* Method : insertBoard
	* 작성자 : PC25
	* 변경이력 :
	* @param boardVo
	* @return
	* Method 설명 : 게시판 생성
	*/
	int insertBoard(BoardVO boardVo);

	/**
	* Method : deleteBoard
	* 작성자 : PC25
	* 변경이력 :
	* @param i
	* Method 설명 : 게시판 삭제
	*/
	int deleteBoard(int id);

	/**
	* Method : boardList
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 사용여부가 true인 boardList 조회
	*/
	List<BoardVO> boardList();
	
	/**
	* Method : boardAllList
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 사용여부 상관없이 모든 boardList 조회
	*/
	List<BoardVO> boardAllList();
	/**
	* Method : getBoard
	* 작성자 : PC25
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 게시판 선택 조회
	*/
	BoardVO getBoard(int id);
	
	
	/**
	* Method : updateBoard
	* 작성자 : PC25
	* 변경이력 :
	* @param boardVo
	* @return
	* Method 설명 : 게시판 사용여부 수정
	*/
	int updateBoard(BoardVO boardVo);
	
}
