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
	@Override
	public List<Noti_commentVO> commentList(int notiId) {
		return ntcDao.commentList(notiId);
	}
	@Override
	public int deleteComment(List<Noti_commentVO> ntcList) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		String del_yn = "false";
		String comment = "삭제된 댓글입니다.";
		int deleteCntSum = 0;
		
		for(Noti_commentVO ntcVo : ntcList){
			ntcVo.setContent(comment);
			ntcVo.setDel_yn(del_yn);
			int deleteCnt = ntcDao.deleteComment(sqlSession, ntcVo);
			
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
	@Override
	public int deleteCmt(Noti_commentVO ntcVo) {
		return ntcDao.deleteCmt(ntcVo);
	}
	@Override
	public Noti_commentVO getCmt(int id) {
		return ntcDao.getCmt(id);
	}

}
