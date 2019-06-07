package kr.or.ddit.notice.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import kr.or.ddit.board.model.BoardVO;
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
	* Method 설명 : 게시판에 해당하는 게시글만 조회 테스트
	*/
	@Test
	public void noticeListTest() {
		/***Given***/
		BoardVO boardVo = new BoardVO();
		boardVo.setId(1);

		/***When***/
		List<NoticeVO> noticeList = noticeDao.noticeList(boardVo);
		/***Then***/
		assertEquals(3, noticeList.size());
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
		int noticeCnt = noticeDao.noticeCnt(boardVo);
		/***Then***/
		assertEquals(3, noticeCnt);
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
		int noticeAllCnt = noticeDao.noticeAllCnt();
		logger.debug("noticeAllCnt : {}", noticeAllCnt);
		/***Then***/

	}
	
	/**
	* Method : insertNoticeTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 게시글 삭제 테스트
	*/
	@Test
	public void deleteNoticeTest(){
		/***Given***/
		String title = "삭제된 게시글입니다.";
		String del_yn = "false";
		int notiId = 10;
		NoticeVO noticeVo = new NoticeVO();
		noticeVo.setDel_yn(del_yn);
		noticeVo.setTitle(title);
		noticeVo.setNotiId(notiId);
		/***When***/
		int result = noticeDao.deleteNotice(noticeVo);
		
		/***Then***/
		assertEquals(1, result);
	}
	
	/**
	* Method : getParentIdTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : DB에 ParentId가 NULL값일때 어떻게 받아오는지 확인하는 테스트
	*/
	@Test
	public void getParentIdTest(){
		/***Given***/
		int notiId = 11;

		/***When***/
		NoticeVO noticeVo = noticeDao.getNotice(notiId);
		logger.debug("parentId : {}", noticeVo.getParentId());
		/***Then***/
		
		
	}
}
