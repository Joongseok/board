package kr.or.ddit.uploadFile.dao;

import java.util.List;

import kr.or.ddit.uploadFile.model.UploadFileVO;
import mybatis.MybatisUtil;

import org.apache.ibatis.session.SqlSession;

public class UploadFileDao implements IUploadFileDao{

	@Override
	public int insertUploadFile(SqlSession sqlSession, UploadFileVO uploadFile) {
		return sqlSession.insert("uploadFile.insertUploadFile", uploadFile);
	}

	@Override
	public List<UploadFileVO> getUploadFileList(int notiId) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		List<UploadFileVO> uploadFileList = sqlSession.selectList("uploadFile.getUploadFileList", notiId);
		sqlSession.close();
		return uploadFileList;
	}

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
