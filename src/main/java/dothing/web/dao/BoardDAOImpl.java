package dothing.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dothing.web.dto.BoardDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<BoardDTO> selectAll() {
		return sqlSession.selectList("mapper.boardMapper.selectAll");
	}

	@Override
	public BoardDTO selectByBoardNum(int inquiryNum) {
		
		return sqlSession.selectOne("mapper.boardMapper.selectAll", inquiryNum);
	}

	@Override
	public int readnumUpdate(int inquiryNum) {
		return sqlSession.update("mapper.boardMapper.boardReadNumPlus", inquiryNum);
	}

	@Override
	public int insert(BoardDTO boardDTO) {
		return sqlSession.insert("mapper.boardMapper.boardInsert", boardDTO);
	}

	/*@Override
	public int delete(int inquiryNum, String password) {
		Map<String, Object> map = new HashMap<>();
		map.put("inquiryNum", inquiryNum);
		map.put("password", password);
		return sqlSession.delete("mapper.boardMapper.boardDelete", map);
	}*/

	/*@Override
	public int update(BoardDTO boardDTO) {
		return sqlSession.update("mapper.boardMapper.boardUpdate", boardDTO);
	}*/

}
