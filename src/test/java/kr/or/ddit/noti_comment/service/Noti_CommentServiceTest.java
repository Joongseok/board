package kr.or.ddit.noti_comment.service;

import static org.junit.Assert.assertEquals;
import kr.or.ddit.noti_comment.model.Noti_commentVO;

import org.junit.Before;
import org.junit.Test;

public class Noti_CommentServiceTest {

	private INoti_CommentService ntcService;
	
	@Before
	public void setup(){
		ntcService = new Noti_CommentService();
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
		Noti_commentVO ntcVo = new Noti_commentVO(ntcService.commentMaxId(), 1, "moon", "댓글 테스트");
		/***When***/
		int result = ntcService.insertComment(ntcVo); 
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
		int ntcId = ntcService.commentAllCnt();
		/***When***/
		int result = ntcService.deleteCmt(ntcId);
		/***Then***/
		assertEquals(1, result);
	}
}
