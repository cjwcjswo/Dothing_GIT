package dothing.web.dao;

import java.util.List;

import dothing.web.dto.BoardDTO;
import dothing.web.dto.BoardReplyDTO;
import dothing.web.dto.NoticeBoardDTO;

public interface NoticeBoardDAO {
	
	/**
	 * �������� �ش��ϴ� �����Խ��� �Խñ� �ҷ�����
	 * @param page �ش��ϴ� ������
	 * @return �����Խñ� ����Ʈ
	 */
	List<NoticeBoardDTO> selectAll(int page);

	/**
	 * �����Խñ� ��ȣ�� �ش��ϴ� �Խñ� ���� ��������
	 * @param noticeNum ����Խñ� ��ȣ
	 * @return �Խñ� ����
	 */
	NoticeBoardDTO selectByBoardNum(int noticeNum);

	/**
	 * �����Խñ� ��ȸ�� ������Ű��
	 * @param noticeNum �ش��ϴ� �����Խñ� ��ȣ
	 * @return ���� ����
	 */
	int readnumUpdate(int noticeNum);

	/**
	 * �����Խñ� ����ϱ�
	 * @param boardDTO ����ϰ����ϴ� �����Խñ� ���� dto
	 * @return ��������
	 */
	int insert(NoticeBoardDTO boardDTO);

	/**
	 * �����Խñ� �����ϱ�
	 * @param noticeNum �ش��ϴ� ���� �Խñ� ��ȣ
	 * @return ���� ����
	 */
	int delete(int noticeNum);

	
	/**
	 * �����Խñ� ���� ��������
	 * @return �����Խñ� ����
	 * 	 */
	int countNoticeList();

}
