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
	public List<BoardDTO> selectAll() {
		List<BoardDTO>list = boardDAO.selectAll();
		return list;
	}

	@Override
	public BoardDTO selectByBoardNum(int inquiryNum, boolean state)throws Exception {
		if(state == true){
			int re = boardDAO.readnumUpdate(inquiryNum);
			if(re ==0) throw new Exception("조회수 증가 오류입니다.");
		}
		return boardDAO.selectByBoardNum(inquiryNum);
	}

	@Override
	public int insert(BoardDTO boardDTO) {
		
		return boardDAO.insert(boardDTO);
	}

	@Override
	public int delete(int inquiryNum) throws Exception{
		BoardDTO dbBoard = boardDAO.selectByBoardNum(inquiryNum);
		
		/*if(dbBoard.getPassword().equals(password)){
			int re = boardDAO.delete(inquiryNum);
			if(re == 0){
				throw new Exception("삭제되지 않았습니다.");
			}
		}else{
			throw new Exception("삭제 오류입니다.");
		}*/
		return 1;
	}

	/*@Override
	public int update(BoardDTO boardDTO) throws Exception{
		BoardDTO dbBoard = boardDAO.selectByBoardNum(boardDTO.getInquiryNum());
		
		if(dbBoard.getPassword().equals(boardDTO.getPassword())){
			int re = boardDAO.update(boardDTO);
			if(re == 0)throw new Exception("업데이트 오류입니다.");
		}
		else{
			throw new Exception("비밀번호 안맞아 수정 안됨 ㅋ");
		}
		
		
		return 1;
	}*/
	
	@Override
	public int insertReply(BoardReplyDTO brDTO)throws Exception{
		int re = boardDAO.insertReply(brDTO);
		
		if(re == 0){
			throw new Exception("댓글 등록 오류");
		}
		return re;
	}
	
	
	@Override
	public List<BoardReplyDTO> selectReply(int inquiryNum) {
		
		return boardDAO.selectReply(inquiryNum);
	}

}
