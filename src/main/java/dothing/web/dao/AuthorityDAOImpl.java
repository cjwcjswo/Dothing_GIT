package dothing.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dothing.web.dto.AuthorityDTO;

@Repository
public class AuthorityDAOImpl implements AuthorityDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertAuthority(AuthorityDTO authorityDTO) {

		return sqlSession.insert("authorityMapper.insertAuthority",authorityDTO);
	}

	@Override
	public List<AuthorityDTO> selectAuthorityByUserName(String userName) {
		
		return sqlSession.selectList("authorityMapper.selectAuthorityByUserName",userName);
	}

}
