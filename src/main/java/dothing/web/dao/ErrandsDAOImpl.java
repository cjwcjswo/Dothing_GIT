package dothing.web.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsHashtagDTO;
import dothing.web.dto.ErrandsPosDTO;
import dothing.web.dto.ErrandsReplyDTO;

@Repository
public class ErrandsDAOImpl implements ErrandsDAO{
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<ErrandsDTO> selectAll(int page) {
		return sqlSession.selectList("mapper.errandsMapper.selectErrands", 0, new RowBounds((page-1)*5, 5));
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
	public int insertErrandsPos(ErrandsPosDTO dto) {
		return sqlSession.insert("mapper.errandsPosMapper.insertPos", dto);
	}

	@Override
	public int insertErrandsHashtag(ErrandsHashtagDTO dto) {
		return sqlSession.insert("mapper.errandsHashtagMapper.insertHash", dto);
	}

	@Override
	public int selectNum() {
		return sqlSession.selectOne("mapper.errandsMapper.selectNum");
	}

	@Override
	public int insertReply(ErrandsReplyDTO dto) {
		return sqlSession.insert("mapper.errandsReplyMapper.insertErrandsReply", dto);
	}

	@Override
	public int deleteErrands(int num) {
		return sqlSession.delete("mapper.errandsMapper.deleteErrands", num);
	}

	@Override
	public int countErrands() {
		return sqlSession.selectOne("mapper.errandsMapper.countErrands");
	}
	
	
	
}
