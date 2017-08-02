package dothing.web.service;

import java.util.List;

import dothing.web.dto.BoardDTO;
import dothing.web.dto.NoticeBoardDTO;

public interface NoticeBoardService {
	
	/**
	 * �������� �ش��ϴ� �����Խ��� �Խñ� �ҷ�����
	 * @param page �ش��ϴ� ������
	 * @return �����Խñ� ����Ʈ
	 */
	List<NoticeBoardDTO> selectAll(int page);

	/**
	 * �����Խñ� ��ȣ�� �ش��ϴ� �Խñ� ���� ��������
	 * @param noticeNum ����Խñ� ��ȣ
	 * @param state true = ��ȸ�� ����, false = �״��
	 * @return �Խñ� ����
	 */
	NoticeBoardDTO selectByBoardNum(int noticeNum, boolean state) throws Exception;

	/**
	 * �����Խñ� ����ϱ�
	 * @param boardDTO: ����ϰ����ϴ� �����Խñ� ���� dto
	 * @return ��������
	 */
	int insert(NoticeBoardDTO boardDTO);

	/**
	 * �����Խñ� �����ϱ�
	 * @param noticeNum �ش��ϴ� ���� �Խñ� ��ȣ
	 * @return ���� ����
	 */
	int delete(int noticeNum) throws Exception;

	
	/**
	 * �����Խñ� ���� ��������
	 * @return �����Խñ� ����
	 * 	 */
	int countNoticeList();

	
}
