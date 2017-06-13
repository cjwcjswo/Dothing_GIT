package dothing.web.service;

import java.util.List;

import dothing.web.dto.BoardDTO;
import dothing.web.dto.BoardReplyDTO;

public interface BoardService {
	/**
	 * ���ڵ� ��ü �˻�
	 */
	List<BoardDTO> selectAll();

	/**
	 * �����ȣ�� �ش��ϴ� ���ڵ� �˻�
	 * 
	 * @param: state
	 *             true�̸� ��ȸ������, false�̸� ��ȸ��������.
	 */
	BoardDTO selectByBoardNum(int inquiryNum, boolean state) throws Exception;

	/**
	 * ���ڵ� ����
	 */
	int insert(BoardDTO boardDTO);

	/**
	 * �����ȣ�� �ش��ϴ� ���ڵ� ����
	 */
	int delete(int inquiryNum) throws Exception;

	/**
	 * �𵨹�ȣ�� �ش��ϴ� ���ڵ� ����
	 */
	/* int update(BoardDTO boardDTO) throws Exception; */

	/**
	 * ��� ����
	 */
	int insertReply(BoardReplyDTO brDTO)throws Exception;

	/**
	 * �Խ��� ��ȣ�� �ش��ϴ� ��� �ҷ�����
	 */
	List<BoardReplyDTO> selectReply(int inquiryNum);
}
