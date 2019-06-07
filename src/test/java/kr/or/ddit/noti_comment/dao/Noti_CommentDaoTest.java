package kr.or.ddit.noti_comment.dao;

import static org.junit.Assert.assertEquals;

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
	* Method 설명 : 댓글 전체 수 테스트
	*/
	@Test
	public void commentAllCntTest() {
		/***Given***/
		

		/***When***/
		int cnt = ntcDao.commentAllCnt();
		/***Then***/
		assertEquals(0, cnt);
	}

}
