package dothing.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Override
	public int insertToken(String memberId, String token) {
		Map<String, String> map = new HashMap<>();
		map.put("id", memberId);
		map.put("token", token);
		return sqlSession.update("androidMapper.updateToken", map);
	}

	@Override
	public List<String> selectTokenByDistance(String latitude, String longitude, Integer distance) {
		Map<String, Object> map = new HashMap<>();
		map.put("latitude", latitude);
		map.put("longitude", longitude);
		map.put("distance", distance);
		return sqlSession.selectList("androidMapper.selectTokenByDistance", map);
	}

}
