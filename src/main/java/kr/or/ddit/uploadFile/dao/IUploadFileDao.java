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
	
	/**
	* Method : getFileVo
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 첨부파일을 다운로드할때 파일의 아이디에 해당하는 파일을 가져오는 메서드
	*/
	UploadFileVO getFileVo(String fileId);

	/**
	* Method : dbDeleteFile
	* 작성자 : PC25
	* 변경이력 :
	* @param uploadFileList
	* @return
	* Method 설명 : 게시글 수정할때 이전에 있던 첨부파일을 DB에서 삭제한다. (실제 삭제되지는 않음)
	*/
	int dbDeleteFile(SqlSession sqlSession, UploadFileVO uploadFileVo);

}
