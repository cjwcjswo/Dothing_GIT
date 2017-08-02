package dothing.web.dao;

import java.util.List;

import dothing.web.dto.BoardDTO;
import dothing.web.dto.BoardReplyDTO;


public interface BoardDAO{
	/**
	 * �Խñ� ��ü ��������(��ڱ������� ����)
	 * @param page �ش��ϴ� ������
	 * @return �Խñ� DTO ����Ʈ
	 * */
	List<BoardDTO> selectAll(int page);
	
	/**
	 * �Խñ� ��ü ��������(���� �������� ����)
	 * @param page �ش��ϴ� ������
	 * @param userId ���� ���̵�
	 * @return �Խñ� DTO ����Ʈ
	 * */
	List<BoardDTO> selectAllMember(int page, String userId);

	/**
	 * �Խñ� ��ȣ�� �ش��ϴ� �Խñ� ��������
	 * @param inquiryNum �Խñ� ��ȣ
	 * @return �Խñ� DTO
	 * */
	BoardDTO selectByBoardNum(int inquiryNum);

	/**
	 * �Խ��� ��ȣ�� �ش��ϴ� ��ȸ�� ������Ű��
	 * @param inquiryNum �Խñ� ��ȣ
	 * @return ���� ����
	 * */
	int readnumUpdate(int inquiryNum);

	/**
	 * �Խñ� ����ϱ�
	 * @param boardDTO �Խñ� DTO
	 * @return ���� ����
	 * */
	int insert(BoardDTO boardDTO);

	/**
	 * �Խñ� �����ϱ�
	 * @param inquiryNum �Խñ� ��ȣ
	 * @return ���� ����
	 * */
	int delete(int inquiryNum);

	/**
	 * �Խñۿ� ��� ����ϱ�
	 * @param brDTO ��� DTO
	 * @return ���� ����
	 * */
	int insertReply(BoardReplyDTO brDTO);

	/**
	 * �Խñۿ� �ش��ϴ� ��� ����Ʈ ��������
	 * @param inquiryNum �Խñ� ��ȣ
	 * @return ��� DTO ����Ʈ
	 * */
	List<BoardReplyDTO> selectReply(int inquiryNum);
	
	/**
	 * ��ü �Խñ� ���� ��������
	 * @return ��ü �����Խñ� count
	 * */
	int countList();

}