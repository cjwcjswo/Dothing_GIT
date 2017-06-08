package dothing.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dothing.web.dto.ErrandsDTO;

@Repository
public class ErrandsDAOImpl implements ErrandsDAO{
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<ErrandsDTO> selectAll() {
		return sqlSession.selectList("mapper.errandsMapper.selectErrands");
	}
	
	
}
