package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;
import mybatis.MybatisUtil;

import org.apache.ibatis.session.SqlSession;

public class BoardDao implements IBoardDao {

	/**
	* Method : boardsCnt
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 게시판 전체 수 조회
	*/
	@Override
	public int boardsCnt() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int boardsCnt = sqlSession.selectOne("board.boardsCnt");
		sqlSession.close();
		return boardsCnt;
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
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int insertBoard = sqlSession.insert("board.insertBoard", boardVo);
		if (insertBoard == 1) {
			sqlSession.commit();
		}else{
			sqlSession.rollback();
		}
		sqlSession.close();
		return insertBoard;
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
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int deleteBoard = sqlSession.delete("board.deleteBoard", id);
		if (deleteBoard == 1) {
			sqlSession.commit();
		}else{
			sqlSession.rollback();
		}
		sqlSession.close();
		return deleteBoard;
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
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		List<BoardVO> boardList = sqlSession.selectList("board.boardList");
		sqlSession.close();
		return boardList;
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
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		BoardVO boardVo = sqlSession.selectOne("board.getBoard", id);
		sqlSession.close();
		return boardVo;
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
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int updateBoard = sqlSession.delete("board.updateBoard", boardVo);
		if (updateBoard == 1) {
			sqlSession.commit();
		}else{
			sqlSession.rollback();
		}
		sqlSession.close();
		return updateBoard;
	}

	/**
	* Method : boardAllList
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 사용여부 상관없이 모든 boardList 조회
	*/
	@Override
	public List<BoardVO> boardAllList() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		List<BoardVO> boardList = sqlSession.selectList("board.boardAllList");
		sqlSession.close();
		return boardList;
	}
	
	
	
}
