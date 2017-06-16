package dothing.web.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dothing.web.dto.NoticeBoardDTO;

@Repository
public class NoticeBoardDAOImpl implements NoticeBoardDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<NoticeBoardDTO> selectAll(int page) {
		List<NoticeBoardDTO>list = sqlSession.selectList("mapper.noticeBoardMapper.selectAll",null,new RowBounds((page-1)*5, 5));
		return list;
	}

	@Override
	public NoticeBoardDTO selectByBoardNum(int noticeNum) {
		return sqlSession.selectOne("mapper.noticeBoardMapper.selectByNoticeNum", noticeNum);
	}

	@Override
	public int readnumUpdate(int noticeNum) {
		return sqlSession.update("mapper.noticeBoardMapper.boardReadNumPlus", noticeNum);
	}

	@Override
	public int insert(NoticeBoardDTO boardDTO) {
		return sqlSession.insert("mapper.noticeBoardMapper.boardInsert", boardDTO);
	}

	@Override
	public int delete(int noticeNum) {
		return sqlSession.delete("mapper.noticeBoardMapper.boardDelete", noticeNum);
	}
	
	@Override
	public int countNoticeList() {
		return sqlSession.selectOne("mapper.noticeBoardMapper.countNoticeList");
	}

}
