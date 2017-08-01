package dothing.web.dao;

import java.util.List;

import dothing.web.dto.BoardDTO;
import dothing.web.dto.BoardReplyDTO;
import dothing.web.dto.NoticeBoardDTO;

public interface NoticeBoardDAO {
	
	/**
	 * �������� �ش��ϴ� �����Խ��� �Խñ� �ҷ�����
	 * @param int page: �ش��ϴ� ������
	 * @return List<NoticeBoardDTO> �����Խñ� ����Ʈ
	 */
	List<NoticeBoardDTO> selectAll(int page);

	/**
	 * �����Խñ� ��ȣ�� �ش��ϴ� �Խñ� ���� ��������
	 * @param int noticeNum: ����Խñ� ��ȣ
	 * @return NoticeBoardDTO: �Խñ� ����
	 */
	NoticeBoardDTO selectByBoardNum(int noticeNum);

	/**
	 * �����Խñ� ��ȸ�� ������Ű��
	 * @param int noticeNum: �ش��ϴ� �����Խñ� ��ȣ
	 * @return int: ���� ����
	 */
	int readnumUpdate(int noticeNum);

	/**
	 * �����Խñ� ����ϱ�
	 * @param NoticeBoardDTO boardDTO: ����ϰ����ϴ� �����Խñ� ���� dto
	 * @return int: ��������
	 */
	int insert(NoticeBoardDTO boardDTO);

	/**
	 * �����Խñ� �����ϱ�
	 * @param int noticeNum: �ش��ϴ� ���� �Խñ� ��ȣ
	 * @return int: ���� ����
	 */
	int delete(int noticeNum);

	
	/**
	 * �����Խñ� ���� ��������
	 * @return int: �����Խñ� ����
	 * 	 */
	int countNoticeList();

}
