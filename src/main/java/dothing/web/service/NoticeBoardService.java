package dothing.web.service;

import java.util.List;

import dothing.web.dto.BoardDTO;
import dothing.web.dto.NoticeBoardDTO;

public interface NoticeBoardService {
	/**
	 * ���ڵ� ��ü �˻�
	 */
	List<NoticeBoardDTO> selectAll(int page);

	/**
	 * �����ȣ�� �ش��ϴ� ���ڵ� �˻�
	 * 
	 * @param: state
	 *             true�̸� ��ȸ������, false�̸� ��ȸ��������.
	 */
	NoticeBoardDTO selectByBoardNum(int noticeNum, boolean state) throws Exception;

	/**
	 * ���ڵ� ����
	 */
	int insert(NoticeBoardDTO boardDTO);

	/**
	 * �����ȣ�� �ش��ϴ� ���ڵ� ����
	 */
	int delete(int noticeNum) throws Exception;

	/**
	 * �����ȣ�� �ش��ϴ� ���ڵ� ����
	 */
	/* int update(NoticeBoardDTO boardDTO) throws Exception; */
	
	/**
	 * ����¡
	 */
	int countNoticeList();

	
}
