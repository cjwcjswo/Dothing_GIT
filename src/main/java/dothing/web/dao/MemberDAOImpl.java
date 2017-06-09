package dothing.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dothing.web.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertMember(MemberDTO memberDTO) {
		
		return sqlSession.insert("memberMapper.insertMember",memberDTO);
	}

	@Override
	public MemberDTO selectMemberById(String id) {
		
		return sqlSession.selectOne("memberMapper.selectMemberById",id);
	}

}
