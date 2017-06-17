package dothing.web.dao;

import java.util.List;

import dothing.web.dto.BoardDTO;
import dothing.web.dto.BoardReplyDTO;
import dothing.web.dto.NoticeBoardDTO;

public interface NoticeBoardDAO {
	/**
	 * ���ڵ� ��ü �˻�
	 **/
	List<NoticeBoardDTO> selectAll(int page);

	/**
	 * �Խ��ǹ�ȣ�� �ش��ϴ� ���ڵ� �˻�
	 */
	NoticeBoardDTO selectByBoardNum(int noticeNum);

	/**
	 * �Խ��ǹ�ȣ�� �ش��ϴ� ���ڵ� ��ȸ�� �����ϱ�
	 */
	int readnumUpdate(int noticeNum);

	/**
	 * ���ڵ� ����
	 */
	int insert(NoticeBoardDTO boardDTO);

	/**
	 * �Խ��ǹ�ȣ�� �ش��ϴ� ���ڵ� ����
	 */
	int delete(int noticeNum);

	/**
	 * �Խ��ǹ�ȣ�� �ش��ϴ� ���ڵ� ����
	 */
	/* int update(BoardDTO boardDTO); */
	
	/**
	 * ����¡
	 */
	int countNoticeList();

}
