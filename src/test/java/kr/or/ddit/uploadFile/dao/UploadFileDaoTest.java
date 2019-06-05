package kr.or.ddit.uploadFile.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import kr.or.ddit.uploadFile.model.UploadFileVO;

import org.junit.Before;
import org.junit.Test;

public class UploadFileDaoTest {

	private IUploadFileDao uploadFileDao;
	
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

}
