package kr.or.ddit.uploadFile.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.uploadFile.model.UploadFileVO;

public interface IUploadFileService {

	/**
	* Method : insertUploadFile
	* 작성자 : PC25
	* 변경이력 :
	* @param uploadFile
	* Method 설명 : 첨부파일을 등록하는 메서드
	*/
	int insertUploadFile(List<UploadFileVO> uploadFile);

	/**
	* Method : getUploadFile
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
	* @param fileId
	* @return
	* Method 설명 : 파일아이디에 해당하는 파일 vo가져오기
	*/
	UploadFileVO getFileVo(String fileId);

	/**
	* Method : dbDeleteFile
	* 작성자 : PC25
	* 변경이력 :
	* @param uploadFileList
	* @return
	* Method 설명 : 게시글 수정할때 DB에 있는 그전의 첨부파일 내역을 삭제한다.(실제 폴더에서 삭제되지는 않음)
	*/
	int dbDeleteFile(List<UploadFileVO> uploadFileList);

}
