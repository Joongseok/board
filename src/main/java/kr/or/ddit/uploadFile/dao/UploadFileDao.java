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

}
