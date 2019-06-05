package kr.or.ddit.uploadFile.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.uploadFile.model.UploadFileVO;

public interface IUploadFileDao {

	/**
	* Method : insertUploadFile
	* 작성자 : PC25
	* 변경이력 :
	 * @param sqlSession 
	* @param uploadFile
	* @return
	* Method 설명 : 첨부파일을 등록하는 메서드
	*/
	int insertUploadFile(SqlSession sqlSession, UploadFileVO uploadFile);

	/**
	* Method : getUploadFileList
	* 작성자 : PC25
	* 변경이력 :
	* @param notiId
	* @return
	* Method 설명 : 해당 게시글의 첨부파일을 불러오는 메서드
	*/
	List<UploadFileVO> getUploadFileList(int notiId);

}
