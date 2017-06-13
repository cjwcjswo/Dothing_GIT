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
		System.out.println("Ȯ��:" + boardDTO.getUserId());
		System.out.println("Ȯ��:" + boardDTO.getBoardContent());
		System.out.println("Ȯ��:" + boardDTO.getBoardTitle());
		return sqlSession.insert("mapper.boardMapper.boardInsert", boardDTO);
	}

	@Override
	public int delete(int inquiryNum) {
		
		return sqlSession.delete("mapper.boardMapper.boardDelete", inquiryNum);
	}

	/*@Override
	public int update(BoardDTO boardDTO) {
		return sqlSession.update("mapper.boardMapper.boardUpdate", boardDTO);
	}*/

}
