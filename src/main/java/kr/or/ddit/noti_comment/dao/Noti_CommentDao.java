package kr.or.ddit.noti_comment.dao;

import java.util.List;

import kr.or.ddit.noti_comment.model.Noti_commentVO;

import org.apache.ibatis.session.SqlSession;

import mybatis.MybatisUtil;

public class Noti_CommentDao implements INoti_CommentDao {

	/**
	* Method : commentAllCnt
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 댓글 전체 수
	*/
	@Override
	public int commentAllCnt() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int commentAllCnt = sqlSession.selectOne("noti_comment.commentAllCnt");
		sqlSession.close();
		return commentAllCnt;
	}

	/**
	* Method : commentMaxId
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 댓글의 마지막 번호
	*/
	@Override
	public int commentMaxId() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int commentMaxId = sqlSession.selectOne("noti_comment.commentMaxId");
		sqlSession.close();
		return commentMaxId;
	}

	/**
	* Method : insertComment
	* 작성자 : PC25
	* 변경이력 :
	* @param ntcVo
	* @return
	* Method 설명 : 댓글 생성
	*/
	@Override
	public int insertComment(Noti_commentVO ntcVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int insertComment = sqlSession.insert("noti_comment.insertComment", ntcVo);
		sqlSession.commit();
		sqlSession.close();
		return insertComment;
	}

	/**
	* Method : commentList
	* 작성자 : PC25
	* 변경이력 :
	* @param notiId
	* @return
	* Method 설명 : 댓글 리스트
	*/
	@Override
	public List<Noti_commentVO> commentList(int notiId) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		List<Noti_commentVO> commentList = sqlSession.selectList("noti_comment.commentList", notiId);
		sqlSession.close();
		return commentList;
	}

	/**
	* Method : deleteComment
	* 작성자 : PC25
	* 변경이력 :
	* @param sqlSession
	* @param ntcVo
	* @return
	* Method 설명 : 해당 게시글 이 삭제되면 댓글도 전부 cascade
	*/
	@Override
	public int deleteComment(SqlSession sqlSession, int id) {
		return sqlSession.update("noti_comment.deleteComment", id);
	}

	/**
	* Method : deleteCmt
	* 작성자 : PC25
	* 변경이력 :
	* @param ntcId
	* @return
	* Method 설명 : ID에 해당하는 댓글 삭제
	*/
	@Override
	public int deleteCmt(int ntcId) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int result = sqlSession.update("noti_comment.deleteCmt", ntcId);
		sqlSession.commit();
		return result;
	}

}
