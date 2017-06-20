package dothing.web.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dothing.web.dto.BoardDTO;
import dothing.web.dto.BoardReplyDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<BoardDTO> selectAll(int page) {
		return sqlSession.selectList("mapper.boardMapper.selectAll",null,new RowBounds((page-1)*5, 5));
	}
	
	@Override
	public List<BoardDTO> selectAllMember(int page, String userId) {
		return sqlSession.selectList("mapper.boardMapper.selectAllMember",userId,new RowBounds((page-1)*5, 5));
	}

	@Override
	public BoardDTO selectByBoardNum(int inquiryNum) {
		return sqlSession.selectOne("mapper.boardMapper.selectByInquriyNum", inquiryNum);
	}

	@Override
	public int readnumUpdate(int inquiryNum) {
		return sqlSession.update("mapper.boardMapper.boardReadNumPlus", inquiryNum);
	}

	@Override
	public int insert(BoardDTO boardDTO) {
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
	
	@Override
	public int insertReply(BoardReplyDTO brDTO){
		return sqlSession.insert("mapper.boardMapper.insertReply", brDTO);
	}
	
	@Override
	public List<BoardReplyDTO> selectReply(int inquiryNum) {
		return sqlSession.selectList("mapper.boardMapper.callReply", inquiryNum);
	}
	
	@Override
	public int countNoticeList() {
		return sqlSession.selectOne("mapper.boardMapper.countNoticeList");
	}

}
