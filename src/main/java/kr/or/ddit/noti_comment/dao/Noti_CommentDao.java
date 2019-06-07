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

	@Override
	public int insertComment(Noti_commentVO ntcVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int insertComment = sqlSession.insert("noti_comment.insertComment", ntcVo);
		sqlSession.commit();
		sqlSession.close();
		return insertComment;
	}

	@Override
	public List<Noti_commentVO> commentList(int notiId) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		List<Noti_commentVO> commentList = sqlSession.selectList("noti_comment.commentList", notiId);
		sqlSession.close();
		return commentList;
	}

	@Override
	public int deleteComment(SqlSession sqlSession, Noti_commentVO ntcVo) {
		return sqlSession.update("noti_comment.deleteComment", ntcVo);
	}

	@Override
	public int deleteCmt(Noti_commentVO ntcVo) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int result = sqlSession.update("noti_comment.deleteCmt", ntcVo);
		sqlSession.commit();
		return result;
	}

	@Override
	public Noti_commentVO getCmt(int id) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		Noti_commentVO ntcVo = sqlSession.selectOne("noti_comment.getCmt", id);
		return ntcVo;
	}



}
