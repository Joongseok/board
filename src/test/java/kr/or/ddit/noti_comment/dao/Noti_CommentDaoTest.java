package kr.or.ddit.noti_comment.dao;

import static org.junit.Assert.assertEquals;
import kr.or.ddit.noti_comment.model.Noti_commentVO;

import org.junit.Before;
import org.junit.Test;

public class Noti_CommentDaoTest {

	private INoti_CommentDao ntcDao;
	
	@Before
	public void setup(){
		ntcDao = new Noti_CommentDao();
	}
	
	/**
	* Method : commentAllCntTest
	* 작성자 : PC25
	* 변경이력 :
	* 테스트 ID : TSS-10
	* Method 설명 : 댓글 작성 테스트
	*/
	@Test
	public void insertCommentTest() {
		/***Given***/
		Noti_commentVO ntcVo = new Noti_commentVO(ntcDao.commentMaxId(), 1, "moon", "댓글 테스트");
		/***When***/
		int result = ntcDao.insertComment(ntcVo); 
		/***Then***/
		assertEquals(1, result);
	}

	/**
	* Method : commentAllCntTest
	* 작성자 : PC25
	* 변경이력 :
	* 테스트 ID : TSS-11
	* Method 설명 : 댓글 삭제 테스트
	*/
	@Test
	public void deleteCmtTest() {
		/***Given***/
		int ntcId = ntcDao.commentAllCnt();
		/***When***/
		int result = ntcDao.deleteCmt(ntcId);
		/***Then***/
		assertEquals(1, result);
	}
}
