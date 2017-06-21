package dothing.web.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dothing.web.dto.PointDTO;

@Repository
public class AdminMoneyDAOImpl implements AdminMoneyDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<PointDTO> selectAll(int page) {
		
		return sqlSession.selectList("mapper.pointMapper.selectAll",null,new RowBounds((page-1)*5, 5));
	}
	
	@Override
	public int changePoint(String userId) {
		
		return sqlSession.update("mapper.pointMapper.changePoint",userId);
	}
	
	@Override
	public int pointCancel(String userId) {
		
		return sqlSession.update("mapper.pointMapper.pointCancel",userId);
	}
	
	@Override
	public int countPointList() {
		
		return sqlSession.selectOne("mapper.pointMapper.countPointList");
	}
	
	@Override
	public int getRequestPoint(String id){
		return sqlSession.selectOne("mapper.pointMapper.getRequestPoint", id);
	}

}
