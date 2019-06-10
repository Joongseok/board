package kr.or.ddit.noti_comment.service;

import java.util.List;

import mybatis.MybatisUtil;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.noti_comment.dao.INoti_CommentDao;
import kr.or.ddit.noti_comment.dao.Noti_CommentDao;
import kr.or.ddit.noti_comment.model.Noti_commentVO;

public class Noti_CommentService implements INoti_CommentService{

	
	private INoti_CommentDao ntcDao = new Noti_CommentDao();
	/**
	* Method : commentAllCnt
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 전체 댓글 수
	*/
	@Override
	public int commentAllCnt() {
		
		return ntcDao.commentAllCnt();
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
		return ntcDao.commentMaxId();
	}
	/**
	* Method : insertComment
	* 작성자 : PC25
	* 변경이력 :
	* @param ntcVo
	* @return
	* Method 설명 : 댓글 작성
	*/
	@Override
	public int insertComment(Noti_commentVO ntcVo) {
		return ntcDao.insertComment(ntcVo);
	}
	
	/**
	* Method : commentList
	* 작성자 : PC25
	* 변경이력 :
	* @param notiId
	* @return
	* Method 설명 : 해당 게시글의 댓글 리스트
	*/
	@Override
	public List<Noti_commentVO> commentList(int notiId) {
		return ntcDao.commentList(notiId);
	}
	/**
	* Method : deleteComment
	* 작성자 : PC25
	* 변경이력 :
	* @param ntcList
	* @return
	* Method 설명 : 해당 게시글 이 삭제되면 댓글도 전부 cascade
	*/
	@Override
	public int deleteComment(List<Noti_commentVO> ntcList) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int deleteCntSum = 0;
		
		for(Noti_commentVO ntcVo : ntcList){
			int deleteCnt = ntcDao.deleteComment(sqlSession, ntcVo.getId());
			deleteCntSum += deleteCnt;
			if (deleteCnt != 1) {
				sqlSession.rollback();
				break;
			}
		}
		sqlSession.commit();
		sqlSession.close();
		return deleteCntSum;
	}
	
	/**
	* Method : deleteCmt
	* 작성자 : PC25
	* 변경이력 :
	* @param ntcId
	* @return
	* Method 설명 : ID에 해당하는 한개의 댓글 삭제
	*/
	@Override
	public int deleteCmt(int ntcId) {
		return ntcDao.deleteCmt(ntcId);
	}

}
