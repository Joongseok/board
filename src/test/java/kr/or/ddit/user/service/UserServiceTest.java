package kr.or.ddit.user.service;

import static org.junit.Assert.assertNotNull;
import kr.or.ddit.user.dao.UserDaoTest;
import kr.or.ddit.user.model.UserVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceTest {

	
	
private IUserService userService;
	
	@Before
	public void setup(){
		userService = new UserService();
	}
	private static final Logger logger = LoggerFactory
			.getLogger(UserDaoTest.class);
	/**
	* Method : getUserTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 사용자 선택 조회 테스트
	*/
	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "brown";

		/***When***/
		UserVO userVo = userService.getUser(userId);
		logger.debug("userVo : {}", userVo);
		/***Then***/
		assertNotNull(userVo);
	}

}
