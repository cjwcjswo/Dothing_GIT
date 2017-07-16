package dothing.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dothing.web.dto.MemberDTO;

@Repository
public class AndroidDAOImpl implements AndroidDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public String androidLogin(String email) {
		return sqlSession.selectOne("androidMapper.checkId", email);
	}

	@Override
	public int androidSignIn(MemberDTO memberDTO) {
		return sqlSession.insert("androidMapper.signIn", memberDTO);
	}

}
