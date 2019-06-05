package kr.or.ddit.notice.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.notice.model.NoticeVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoticeServiceTest {

	
	INoticeService noticeService;
	private static final Logger logger = LoggerFactory
			.getLogger(NoticeServiceTest.class);
	@Before
	public void setup(){
		noticeService = new NoticeService();
	}
	
	/**
	* Method : noticeListTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 게시판에 해당하는 게시글만 조회 테스트
	*/
	@Test
	public void noticeListTest() {
		/***Given***/
		BoardVO boardVo = new BoardVO();
		boardVo.setId(2);

		/***When***/
		List<NoticeVO> noticeList = noticeService.noticeList(boardVo);
		/***Then***/
		assertEquals(1, noticeList.size());

	}
	/**
	* Method : noticeCntTest
	* 작성자 : PC25
	* 변경이력 :
	* @param boardVo
	* @return
	* Method 설명 : 삭제여부를 가려서 게시판의 번호를 생성하는 메서드 테스트
	*/
	@Test
	public void noticeCntTest(){
		/***Given***/
		BoardVO boardVo = new BoardVO();
		boardVo.setId(1);

		/***When***/
		int noticeCnt = noticeService.noticeCnt(boardVo);
		/***Then***/
		assertEquals(6, noticeCnt);
	}
	
	/**
	* Method : noticeAllCnt
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 모든게시판의 게시글 수를 가져오는 메서드 테스트
	*/
	@Test
	public void noticeAllCntTest(){
		/***Given***/
		

		/***When***/
		int noticeAllCnt = noticeService.noticeAllCnt();
		logger.debug("noticeAllCnt : {}", noticeAllCnt);
		/***Then***/

	}
	
	/**
	* Method : insertNoticeTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 게시글 작성 테스트
	*/
	@Test
	public void insertNoticeTest(){
		/***Given***/
		int notiId = noticeService.noticeAllCnt() == 0 ? 1 : noticeService.noticeAllCnt() + 1;
		NoticeVO noticeVo = new NoticeVO(
				notiId
				, "brown"
				, "테스트제목"
				, "테스트내용"
				, 1
				);

		/***When***/
		int result = noticeService.insertNotice(noticeVo);
		/***Then***/
		assertEquals(1, result);
	}

}
