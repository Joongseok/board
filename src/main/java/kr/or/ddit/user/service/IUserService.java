package kr.or.ddit.user.service;

import kr.or.ddit.user.model.UserVO;

public interface IUserService {
	/**
	* Method : getUser
	* 작성자 : PC25
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 선택 조회
	*/
	UserVO getUser(String userId);
}
