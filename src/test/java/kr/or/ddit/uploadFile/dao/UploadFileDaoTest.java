package kr.or.ddit.uploadFile.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import kr.or.ddit.uploadFile.model.UploadFileVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UploadFileDaoTest {

	private IUploadFileDao uploadFileDao;
	private static final Logger logger = LoggerFactory
			.getLogger(UploadFileDaoTest.class);
	@Before
	public void setup(){
		uploadFileDao = new UploadFileDao();
	}
	
	/**
	* Method : test
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 첨부파일을 DB에 등록하는 메서드 테스트
	*/
	@Test
	public void insertUploadFileTest() {
		/***Given***/
		UploadFileVO uploadFile = new UploadFileVO("124/12521", 1, "2151251", "뭐");

		/***When***/
//		int result = uploadFileDao.insertUploadFile(uploadFile);
		/***Then***/
//		assertEquals(1, result);
	}
	
	/**
	* Method : uploadFileListTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 해당게시글의 업로드 파일리스트를 불러오는 메서드 테스트
	*/
	@Test
	public void uploadFileListTest(){
		/***Given***/
		int notiId  = 20;

		/***When***/
		List<UploadFileVO> uploadFileList = uploadFileDao.getUploadFileList(notiId);
		/***Then***/
		assertEquals(5, uploadFileList.size());

	}
	
	/**
	* Method : getFileVoTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 아이디에 해당하는 파일객체 얻는 테스트
	*/
	@Test
	public void getFileVoTest(){
		/***Given***/
		String fileId = "d:\\upload\\자유게시판\\2019\\06\\74a262e0-0f54-442c-983c-0aeefa3db8bb.png";

		/***When***/
		UploadFileVO fileVo = uploadFileDao.getFileVo(fileId);
		logger.debug("fileVo : {}", fileVo);
		/***Then***/
		
		
	}

}
