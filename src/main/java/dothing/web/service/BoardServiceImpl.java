package dothing.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dothing.web.dao.BoardDAO;
import dothing.web.dto.BoardDTO;
import dothing.web.dto.BoardReplyDTO;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO boardDAO;

	@Override
	public List<BoardDTO> selectAll(int page) {
		List<BoardDTO> list = boardDAO.selectAll(page);
		return list;
	}
	
	@Override
	public List<BoardDTO> selectAllMember(int page, String userId) {
		List<BoardDTO> list = boardDAO.selectAllMember(page, userId);
		return list;
	}

	@Override
	public BoardDTO selectByBoardNum(int inquiryNum, boolean state) throws Exception {
		if (state == true) {
			int re = boardDAO.readnumUpdate(inquiryNum);
			if (re == 0) throw new Exception("조회수 증가 오류입니다.");
		}
		return boardDAO.selectByBoardNum(inquiryNum);
	}

	@Override
	public int insert(BoardDTO boardDTO) {
		return boardDAO.insert(boardDTO);
	}

	@Override
	public int delete(int inquiryNum) throws Exception {
		int re = boardDAO.delete(inquiryNum);
		if (re == 0) throw new Exception("삭제오류입니다. 다시이용해주세요.");
		return re;
	}



	@Override
	public int insertReply(BoardReplyDTO brDTO) throws Exception {
		int re = boardDAO.insertReply(brDTO);
		if (re == 0) throw new Exception("댓글 등록 오류");
		return re;
	}

	@Override
	public List<BoardReplyDTO> selectReply(int inquiryNum) {
		return boardDAO.selectReply(inquiryNum);
	}	
	
	@Override
	public int countList() {
		return boardDAO.countList();
	}

}
