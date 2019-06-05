package kr.or.ddit.uploadFile.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import kr.or.ddit.uploadFile.model.UploadFileVO;

import org.junit.Before;
import org.junit.Test;

public class UploadFileServiceTest {

	
private IUploadFileService uploadFileService;
	
	@Before
	public void setup(){
		uploadFileService = new UploadFileService();
	}
	
	/**
	* Method : test
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 첨부파일을 DB에 등록하는 메서드 테스트
	*/
	@Test
	public void insertUploadFile() {
		/***Given***/
		UploadFileVO uploadFile = new UploadFileVO("124/4112521", 1, "2151251", "뭐");

		/***When***/
//		int result = uploadFileService.insertUploadFile(uploadFile);
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
		List<UploadFileVO> uploadFileList = uploadFileService.getUploadFileList(notiId);
		/***Then***/
		assertEquals(5, uploadFileList.size());

	}
}
