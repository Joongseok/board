package kr.or.ddit.user.dao;

import static org.junit.Assert.assertNotNull;
import kr.or.ddit.user.model.UserVO;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDaoTest {

	private IUserDao userDao;
	
	@Before
	public void setup(){
		userDao = new UserDao();
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
		UserVO userVo = userDao.getUser(userId);
		logger.debug("userVo : {}", userVo);
		/***Then***/
		assertNotNull(userVo);
	}

}
