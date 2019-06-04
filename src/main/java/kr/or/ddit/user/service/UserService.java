package kr.or.ddit.user.service;

import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDao;
import kr.or.ddit.user.model.UserVO;

public class UserService implements IUserService {

	private IUserDao userDao = new UserDao(); 
	@Override
	public UserVO getUser(String userId) {
		
		return userDao.getUser(userId);
	}
	
}
