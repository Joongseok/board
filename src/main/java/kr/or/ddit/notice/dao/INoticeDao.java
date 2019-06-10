package kr.or.ddit.notice.dao;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.notice.model.NoticeVO;

public interface INoticeDao {
	/**
	* Method : noticeList
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 게시판에 해당하는 게시글만 조회
	*/
	List<NoticeVO> noticePagingList(Map<String, Object>pageMap);
	
	/**
	* Method : noticeCnt
	* 작성자 : PC25
	* 변경이력 :
	* @param id
	* @return
	* Method 설명 : 해당 게시판의 게시글 수
	*/
	int noticeCnt(int id);
	
	/**
	* Method : insertNotice
	* 작성자 : PC25
	* 변경이력 :
	* @param noticeVo
	* @return
	* Method 설명 : 게시글 작성
	*/
	int insertNotice(NoticeVO noticeVo);
	
	/**
	* Method : noticeAllCnt
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 모든게시판의 게시글을 합친수
	*/
	int noticeAllCnt();

	/**
	* Method : getNotice
	* 작성자 : PC25
	* 변경이력 :
	* @param notiId
	* @return
	* Method 설명 : 게시글 선택 조회
	*/
	NoticeVO getNotice(int notiId);

	/**
	* Method : noticeMaxId
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 게시글의 가장 마지막 번호
	*/
	int noticeMaxId();

	/**
	* Method : updateNotice
	* 작성자 : PC25
	* 변경이력 :
	* @param noticeVo
	* @return
	* Method 설명 : 게시글 수정
	*/
	int updateNotice(NoticeVO noticeVo);

	/**
	* Method : deleteNotice
	* 작성자 : PC25
	* 변경이력 :
	* @param notiId
	* @return
	* Method 설명 : 게시글 삭제
	*/
	int deleteNotice(int notiId);

	/**
	* Method : replyNotice
	* 작성자 : PC25
	* 변경이력 :
	* @param createNoticeVo
	* @return
	* Method 설명 : 답글
	*/
	int replyNotice(NoticeVO createNoticeVo);
}
