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
			if(re ==0) throw new Exception("��ȸ�� ���� �����Դϴ�.");
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
				throw new Exception("�������� �ʾҽ��ϴ�.");
			}
		}else{
			throw new Exception("���� �����Դϴ�.");
		}*/
		return 1;
	}

	/*@Override
	public int update(BoardDTO boardDTO) throws Exception{
		BoardDTO dbBoard = boardDAO.selectByBoardNum(boardDTO.getInquiryNum());
		
		if(dbBoard.getPassword().equals(boardDTO.getPassword())){
			int re = boardDAO.update(boardDTO);
			if(re == 0)throw new Exception("������Ʈ �����Դϴ�.");
		}
		else{
			throw new Exception("��й�ȣ �ȸ¾� ���� �ȵ� ��");
		}
		
		
		return 1;
	}*/
	
	@Override
	public int insertReply(BoardReplyDTO brDTO)throws Exception{
		int re = boardDAO.insertReply(brDTO);
		
		if(re == 0){
			throw new Exception("��� ��� ����");
		}
		return re;
	}
	
	
	@Override
	public List<BoardReplyDTO> selectReply(int inquiryNum) {
		
		return boardDAO.selectReply(inquiryNum);
	}

}
