package kr.or.ddit.user.dao;

import mybatis.MybatisUtil;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.user.model.UserVO;

public class UserDao implements IUserDao {

	@Override
	public UserVO getUser(String userId) {
		SqlSession sqlSession = MybatisUtil.getSqlSession();
		UserVO userVo = sqlSession.selectOne("user.getUser", userId);
		sqlSession.close();
		return userVo;
	}

}
