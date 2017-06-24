package dothing.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsPosDTO;
import dothing.web.dto.ErrandsReplyDTO;
import dothing.web.dto.GPADTO;

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
	public int insertErrandsPos(ErrandsPosDTO dto) {
		return sqlSession.insert("mapper.errandsPosMapper.insertPos", dto);
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

	@Override
	public List<ErrandsDTO> searchErrands(String hash, Integer minPrice, Integer maxPrice,
			Integer distance, String latitude, String longitude) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("hash", "%"+ hash + "%");
		map.put("minPrice", minPrice);
		map.put("maxPrice", maxPrice);
		map.put("latitude", latitude);
		map.put("longitude", longitude);
		map.put("distance", distance);
		return sqlSession.selectList("mapper.errandsMapper.searchErrands", map);
	}

	@Override
	public List<ErrandsDTO> myRequestErrands(String userId, int page) {
		return sqlSession.selectList("mapper.errandsMapper.myErrandsRequest", userId, new RowBounds((page-1)*5, 5));
	}

	@Override
	public List<ErrandsDTO> myResponseErrands(String userId, int page) {
		return sqlSession.selectList("mapper.errandsMapper.myErrandsResponse", userId, new RowBounds((page-1)*5, 5));
	}

	@Override
	public int deleteReply(int num) {
		return sqlSession.delete("mapper.errandsReplyMapper.deleteErrandsReply", num);
	}

	@Override
	public int updateErrands(int errandsNum, String responseId, String startTime, String arrivalTime,
			String finishTime) {
		Map<String, Object> map = new HashMap<>();
		map.put("responseId", responseId);
		map.put("startTime", startTime);
		map.put("arrivalTime", arrivalTime);
		map.put("finishTime", finishTime);
		map.put("num", errandsNum);
		return sqlSession.update("mapper.errandsMapper.updateErrands", map);
	}

	@Override
	public int countMyRequest(String id) {
		return sqlSession.selectOne("mapper.errandsMapper.countRequest", id);
	}

	@Override
	public int countMyResponse(String id) {
		return sqlSession.selectOne("mapper.errandsMapper.countResponse", id);
	}

	@Override
	public int insertGPA(GPADTO dto) {
		return sqlSession.insert("mapper.errandsGPAMapper.insertGPA", dto);
	}

	@Override
	public List<ErrandsReplyDTO> selectByErrands(int num) {
		return sqlSession.selectList("mapper.errandsReplyMapper.selectByErrands", num);
	}

	@Override
	public List<GPADTO> selectGPA(int num) {
		return sqlSession.selectList("mapper.errandsGPAMapper.selectGPA",num);
	}

	@Override
	public List<GPADTO> selectGPAById(String id) {
		return sqlSession.selectList("mapper.errandsGPAMapper.selectGPAById",id);
	}

	@Override
	public List<ErrandsDTO> moneyErrands() {
		return sqlSession.selectList("mapper.errandsMapper.moneyErrands", null, new RowBounds(0, 3));
	}

	@Override
	public List<ErrandsDTO> selectList(Integer sort, String addr, int page) {
		Map<String, Object> map = new HashMap<>();
		map.put("sort", sort);
		if(addr != null) map.put("addr", "%"+addr+"%");
		return sqlSession.selectList("mapper.errandsMapper.selectList", map, new RowBounds((page-1) * 10, 10));
	}
	@Override
	public int countList(String addr){
		String keyword = null;
		if(addr != null) keyword = "%"+addr+"%";
		return sqlSession.selectOne("mapper.errandsMapper.countList", keyword);
	}

	@Override
	public int deleteTimeErrands() {
		return sqlSession.delete("mapper.errandsMapper.deleteTimeErrands");
	}
	
	
	
}
