package kr.or.ddit.util;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PartUtilTest {

	private static final Logger logger = LoggerFactory
			.getLogger(PartUtilTest.class);
	/**
	* Method : getFileNameTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 파일의 이름을 가져오는 메서드 테스트
	*/
	@Test
	public void getFileNameTest() {
		/***Given***/
		String contentDisposition = "form-data; name=\"profile\"; filename=\"sally.png\"";

		/***When***/
		String fileName = PartUtil.getFileName(contentDisposition);
		logger.debug("fileName : {}", fileName);
		/***Then***/
	}
	
	/**
	* Method : getExtTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 파일이름에서 확장자를 가져오는 메서드 테스트
	*/
	@Test
	public void getExtTest(){
		/***Given***/
		String sally = "sally.png";

		/***When***/
		String ext = PartUtil.getExt(sally);
		logger.debug("ext : {}", ext);
		/***Then***/

	}

}
