package kr.or.ddit.notice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.notice.dao.INoticeDao;
import kr.or.ddit.notice.dao.NoticeDao;
import kr.or.ddit.notice.model.NoticeVO;

public class NoticeService implements INoticeService {
	
	private INoticeDao noticeDao = new NoticeDao();
	/**
	* Method : noticeList
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 게시글 페이징 조회
	*/
	@Override
	public Map<String, Object> noticePagingList(Map<String, Object> pageMap) {
		int id = (int)pageMap.get("id");
		
		List<NoticeVO> noticeList = noticeDao.noticePagingList(pageMap);
		int noticeCnt = noticeDao.noticeCnt(id);
		
		int pageSize = (int) pageMap.get("pageSize");
		int paginationSize = (int) Math.ceil((double)noticeCnt/pageSize);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("noticeList", noticeList);
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}
	
	/**
	* Method : noticeCnt
	* 작성자 : PC25
	* 변경이력 :
	* @param boardVo
	* @return
	* Method 설명 : 해당 게시판의 게시글 수
	*/
	@Override
	public int noticeCnt(int id) {
		return noticeDao.noticeCnt(id);
	}
	
	/**
	* Method : insertNotice
	* 작성자 : PC25
	* 변경이력 :
	* @param noticeVo
	* @return
	* Method 설명 : 게시글 작성
	*/
	@Override
	public int insertNotice(NoticeVO noticeVo) {
		return noticeDao.insertNotice(noticeVo);
	}
	/**
	* Method : noticeAllCnt
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 모든게시판의 게시글을 합친수
	*/
	@Override
	public int noticeAllCnt() {
		return noticeDao.noticeAllCnt();
	}
	/**
	* Method : getNotice
	* 작성자 : PC25
	* 변경이력 :
	* @param notiId
	* @return
	* Method 설명 : 게시글 선택조회
	*/
	@Override
	public NoticeVO getNotice(int notiId) {
		return noticeDao.getNotice(notiId);
	}
	/**
	* Method : noticeMaxId
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 게시글의 가장 마지막 번호
	*/
	@Override
	public int noticeMaxId() {
		return noticeDao.noticeMaxId();
	}
	/**
	* Method : updateNotice
	* 작성자 : PC25
	* 변경이력 :
	* @param noticeVo
	* @return
	* Method 설명 : 게시글 수정
	*/
	@Override
	public int updateNotice(NoticeVO noticeVo) {
		return noticeDao.updateNotice(noticeVo);
	}
	/**
	* Method : deleteNotice
	* 작성자 : PC25
	* 변경이력 :
	* @param noticeVo
	* @return
	* Method 설명 : 게시글 삭제
	*/
	@Override
	public int deleteNotice(int notiId) {
		return noticeDao.deleteNotice(notiId);
	}
	/**
	* Method : replyNotice
	* 작성자 : PC25
	* 변경이력 :
	* @param createNoticeVo
	* @return
	* Method 설명 : 답글
	*/
	@Override
	public int replyNotice(NoticeVO createNoticeVo) {
		return noticeDao.replyNotice(createNoticeVo);
	}

}
