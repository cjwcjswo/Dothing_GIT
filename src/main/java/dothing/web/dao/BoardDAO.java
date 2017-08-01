package dothing.web.dao;

import java.util.List;

import dothing.web.dto.BoardDTO;
import dothing.web.dto.BoardReplyDTO;


public interface BoardDAO{
	/**
	 * �Խñ� ��ü ��������(��ڱ������� ����)
	 * @param int page: �ش��ϴ� ������
	 * @return List<BoardDTO>: �Խñ� DTO ����Ʈ
	 * */
	List<BoardDTO> selectAll(int page);
	
	/**
	 * �Խñ� ��ü ��������(���� �������� ����)
	 * @param int page: �ش��ϴ� ������
	 * @param String userId: ���� ���̵�
	 * @return List<BoardDTO>: �Խñ� DTO ����Ʈ
	 * */
	List<BoardDTO> selectAllMember(int page, String userId);

	/**
	 * �Խñ� ��ȣ�� �ش��ϴ� �Խñ� ��������
	 * @param int inquiryNum: �Խñ� ��ȣ
	 * @return BoardDTO: �Խñ� DTO
	 * */
	BoardDTO selectByBoardNum(int inquiryNum);

	/**
	 * �Խ��� ��ȣ�� �ش��ϴ� ��ȸ�� ������Ű��
	 * @param int inquiryNum: �Խñ� ��ȣ
	 * @return int: ���� ����
	 * */
	int readnumUpdate(int inquiryNum);

	/**
	 * �Խñ� ����ϱ�
	 * @param BoardDTO boardDTO: �Խñ� DTO
	 * @return int: ���� ����
	 * */
	int insert(BoardDTO boardDTO);

	/**
	 * �Խñ� �����ϱ�
	 * @param int inquiryNum: �Խñ� ��ȣ
	 * @return int: ���� ����
	 * */
	int delete(int inquiryNum);

	/**
	 * �Խñۿ� ��� ����ϱ�
	 * @param BoardReplyDTO brDTO: ��� DTO
	 * @return int: ���� ����
	 * */
	int insertReply(BoardReplyDTO brDTO);

	/**
	 * �Խñۿ� �ش��ϴ� ��� ����Ʈ ��������
	 * @param int inquiryNum: �Խñ� ��ȣ
	 * @return List<BoardReplyDTO> ��� DTO ����Ʈ
	 * */
	List<BoardReplyDTO> selectReply(int inquiryNum);
	
	/**
	 * ��ü �Խñ� ���� ��������
	 * @return int: ��ü �����Խñ� count
	 * */
	int countList();

}