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
		return sqlSession.selectList("mapper.errandsMapper.selectErrands", 0);
	}

	@Override
	public ErrandsDTO selectErrands(int errandsNum) {
		return sqlSession.selectOne("mapper.errandsMapper.selectErrands", errandsNum);
	}

	@Override
	public int insertErrands(ErrandsDTO dto) {
		return sqlSession.insert("mapper.errandsMapper.insertErrands", dto);
	}

	@Override
	public int insertErrandsPos(ErrandsDTO dto) {
		return sqlSession.insert("mapper.errandsHashtagMapper.insertHash", dto);
	}

	@Override
	public int insertErrandsHashtag(ErrandsDTO dto) {
		return sqlSession.insert("mapper.errandsPosMapper.insertPos", dto);
	}
	
	
	
}
