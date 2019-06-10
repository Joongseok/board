package kr.or.ddit.uploadFile.dao;

import java.util.List;

import kr.or.ddit.uploadFile.model.UploadFileVO;
import mybatis.MybatisUtil;

import org.apache.ibatis.session.SqlSession;

public class UploadFileDao implements IUploadFileDao{

	/**
	* Method : insertUploadFile
	* 작성자 : PC25
	* 변경이력 :
	 * @param sqlSession 
	* @param uploadFile
	* @return
	* Method 설명 : 첨부파일을 등록하는 메서드
	*/
	@Override
	public int insertUploadFile(SqlSession sqlSession, UploadFileVO uploadFile) {
		return sqlSession.insert("uploadFile.insertUploadFile", uploadFile);
	}

	/**
	* Method : getUploadFileList
	* 작성자 : PC25
	* 변경이력 :
	* @param notiId
	* @return
	* Method 설명 : 해당 게시글의 첨부파일을 불러오는 메서드
	*/
	@Override
	public List<UploadFileVO> getUploadFileList(int notiId) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		List<UploadFileVO> uploadFileList = sqlSession.selectList("uploadFile.getUploadFileList", notiId);
		sqlSession.close();
		return uploadFileList;
	}

	/**
	* Method : getFileVo
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 첨부파일을 다운로드할때 파일의 아이디에 해당하는 파일을 가져오는 메서드
	*/
	@Override
	public UploadFileVO getFileVo(String fileId) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		UploadFileVO fileVo = sqlSession.selectOne("uploadFile.getFileVo", fileId);
		sqlSession.close();
		return fileVo;
	}
	/**
	* Method : dbDeleteFile
	* 작성자 : PC25
	* 변경이력 :
	* @param uploadFileList
	* @return
	* Method 설명 : 게시글 수정할때 DB에 있는 그전의 첨부파일 내역을 삭제한다.(실제 폴더에서 삭제되지는 않음)
	*/

	@Override
	public int dbDeleteFile(SqlSession sqlSession, UploadFileVO uploadFileVo) {
		return sqlSession.delete("uploadFile.deleteUploadFile", uploadFileVo);
	}

}
