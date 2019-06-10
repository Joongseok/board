package kr.or.ddit.notice.dao;

import java.util.List;
import java.util.Map;

import mybatis.MybatisUtil;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.model.BoardVO;
import kr.or.ddit.notice.model.NoticeVO;

public class NoticeDao implements INoticeDao {

	/**
	* Method : noticeList
	* 작성자 : PC25
	* 변경이력 :
	* @param boardVo
	* @return
	* Method 설명 : 게시글 페이징 조회
	*/
	@Override
	public List<NoticeVO> noticePagingList(Map<String, Object>pageMap) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		List<NoticeVO> noticeList = sqlSession.selectList("notice.noticePagingList", pageMap);
		sqlSession.close();
		return noticeList;
	}
	/**
	* Method : noticeCnt
	* 작성자 : PC25
	* 변경이력 :
	* @param boardVo
	* @return
	* Method 설명 : 게시판의 번호를 출력하는 메서드
	*/
	@Override
	public int noticeCnt(int id) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int noticeCnt = sqlSession.selectOne("notice.noticeCnt", id);
		sqlSession.close();
		return noticeCnt;
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
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int insertNotice = sqlSession.insert("notice.insertNotice", noticeVo);
		if (insertNotice == 1) {
			sqlSession.commit();
		}else{
			sqlSession.rollback();
		}
		sqlSession.close();
		return insertNotice;
	}
	/**
	* Method : noticeAllCnt
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 모든 게시판의 게시글을 합친수
	*/
	@Override
	public int noticeAllCnt() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int noticeAllCnt = sqlSession.selectOne("notice.noticeAllCnt");
		sqlSession.close();
		return noticeAllCnt;
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
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		NoticeVO noticeVo = sqlSession.selectOne("notice.getNotice", notiId);
		sqlSession.close();
		return noticeVo;
	}
	/**
	* Method : noticeMaxId
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 게시글의 마지막 번호
	*/
	@Override
	public int noticeMaxId() {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int noticeMaxId = sqlSession.selectOne("notice.noticeMaxId");
		sqlSession.close();
		return noticeMaxId;
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
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int updateNotice = sqlSession.update("notice.updateNotice", noticeVo);
		sqlSession.commit();
		sqlSession.close();
		return updateNotice;
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
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int deleteNotice = sqlSession.delete("notice.deleteNotice", notiId);
		sqlSession.commit();
		sqlSession.close();
		return deleteNotice;
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
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		int createNotice = sqlSession.insert("notice.replyNotice", createNoticeVo);
		sqlSession.commit();
		sqlSession.close();
		return createNotice;
	}

}
