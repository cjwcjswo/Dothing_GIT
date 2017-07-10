package dothing.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AndroidDAOImpl implements AndroidDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public String androidLogin(String email) {
		return sqlSession.selectOne("androidMapper.checkId", email);
	}

}
