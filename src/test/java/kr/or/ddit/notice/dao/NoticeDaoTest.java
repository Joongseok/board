package kr.or.ddit.notice.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.notice.model.NoticeVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoticeDaoTest {

	INoticeDao noticeDao;
	
	private static final Logger logger = LoggerFactory
			.getLogger(NoticeDaoTest.class);
	@Before
	public void setup(){
		noticeDao = new NoticeDao();
	}
	
	/**
	* Method : noticeListTest
	* 작성자 : PC25
	* 변경이력 :
	* 테스트 ID : TSS-04
	* Method 설명 : 해당 게시판의 게시글 페이징 조회 테스트
	*/
	@Test
	public void noticeListTest() {
		/***Given***/
		int id = 1;
		int page = 1;
		int pageSize = 10;
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("id", id);
		pageMap.put("page", page);
		pageMap.put("pageSize", pageSize);
		/***When***/
		List<NoticeVO> noticeList = (List<NoticeVO>) noticeDao.noticePagingList(pageMap);
		
		/***Then***/
		assertEquals(10, noticeList.size());
	}

	/**
	* Method : insertNoticeTest
	* 작성자 : PC25
	* 변경이력 :
	* 테스트 ID : TSS-05
	* Method 설명 : 게시글 작성 테스트
	*/
	@Test
	public void insertNoticeTest(){
		/***Given***/
		NoticeVO noticeVo = new NoticeVO(noticeDao.noticeMaxId(), "sally", "테스트제목", "테스트내용", 1, 1);

		/***When***/
		int result = noticeDao.insertNotice(noticeVo);
		/***Then***/
		assertEquals(1, result);
	}
	
	/**
	* Method : insertNoticeTest
	* 작성자 : PC25
	* 변경이력 :
	* 테스트 ID : TSS-06
	* Method 설명 : 게시글 조회 테스트
	*/
	@Test
	public void readNoticeTest(){
		/***Given***/
		int notiId = noticeDao.noticeAllCnt();
		/***When***/
		NoticeVO noticeVo = noticeDao.getNotice(notiId);
		logger.debug("noticeVo : {}", noticeVo);
		/***Then***/
		assertNotNull(noticeVo);
	}
	
	/**
	* Method : insertNoticeTest
	* 작성자 : PC25
	* 변경이력 :
	* 테스트 ID : TSS-07
	* Method 설명 : 게시글 수정 테스트
	*/
	@Test
	public void updateNoticeTest(){
		/***Given***/
		NoticeVO noticeVo = new NoticeVO(noticeDao.noticeAllCnt(), "수정 테스트 제목", "수정테스트 내용");

		/***When***/
		int result = noticeDao.updateNotice(noticeVo);
		/***Then***/
		assertEquals(1, result);
	}
	
	/**
	 * Method : insertNoticeTest
	 * 작성자 : PC25
	 * 변경이력 :
	 * 테스트 ID : TSS-08
	 * Method 설명 : 게시글 삭제 테스트
	 */
	@Test
	public void deleteNoticeTest(){
		/***Given***/
		int notiId = noticeDao.noticeAllCnt();
		/***When***/
		int result = noticeDao.deleteNotice(notiId);
		/***Then***/
		assertEquals(1, result);
	}
	
	/**
	* Method : insertNoticeTest
	* 작성자 : PC25
	* 변경이력 :
	* 테스트 ID : TSS-09
	* Method 설명 : 답글 작성 테스트
	*/
	@Test
	public void replyNoticeTest(){
		/***Given***/
		NoticeVO noticeVo = new NoticeVO(noticeDao.noticeMaxId(), "brown", "답글테스트 제목", "답글 테스트 내용", noticeDao.noticeAllCnt(), 1, 1);

		/***When***/
		int result = noticeDao.replyNotice(noticeVo);
		/***Then***/
		assertEquals(1, result);
	}
	
}
