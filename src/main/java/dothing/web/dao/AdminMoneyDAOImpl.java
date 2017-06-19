package dothing.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dothing.web.dto.PointDTO;

@Repository
public class AdminMoneyDAOImpl implements AdminMoneyDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<PointDTO> selectAll() {
		
		return sqlSession.selectList("mapper.pointMapper.selectAll");
	}

}
