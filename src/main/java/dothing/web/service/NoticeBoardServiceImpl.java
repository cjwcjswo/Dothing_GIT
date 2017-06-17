package dothing.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dothing.web.dao.BoardDAO;
import dothing.web.dao.NoticeBoardDAO;
import dothing.web.dto.BoardDTO;
import dothing.web.dto.NoticeBoardDTO;

@Service
@Transactional
public class NoticeBoardServiceImpl implements NoticeBoardService {
	
	@Autowired
	private NoticeBoardDAO boardDAO;

	@Override
	public List<NoticeBoardDTO> selectAll(int page) {
		//System.out.println(page);
		List<NoticeBoardDTO> list = boardDAO.selectAll(page);
		return list;
	}

	@Override
	public NoticeBoardDTO selectByBoardNum(int noticeNum, boolean state) throws Exception {
		if (state == true) {
			int re = boardDAO.readnumUpdate(noticeNum);
			if (re == 0)
				throw new Exception("��ȸ�� ���� �����Դϴ�.");
		}
		return boardDAO.selectByBoardNum(noticeNum);
	}

	@Override
	public int insert(NoticeBoardDTO boardDTO) {
		
		System.out.println(boardDTO.getNoticeContent());
		System.out.println(boardDTO.getNoticeTitle());
		System.out.println(boardDTO.getUserId());
		return boardDAO.insert(boardDTO);
	}

	@Override
	public int delete(int noticeNum) throws Exception {
		int re = boardDAO.delete(noticeNum);
		
		if(re == 0){
			throw new Exception("���������Դϴ�. �ٽ��̿����ּ���.");
		}
		
		return re;
	}
	
	@Override
	public int countNoticeList() {
		return boardDAO.countNoticeList();
	}

}
