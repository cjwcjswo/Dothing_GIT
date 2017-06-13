package dothing.web.service;

import java.util.List;

import dothing.web.dto.BoardDTO;

public interface BoardService {
	/**
	  * ���ڵ� ��ü �˻�
	  * */
	  List<BoardDTO> selectAll() ;
	  
	  /**
		  * �����ȣ�� �ش��ϴ� ���ڵ� �˻�
		  * @param: state true�̸� ��ȸ������, false�̸� ��ȸ��������.
		  * */
	  BoardDTO selectByBoardNum(int inquiryNum , boolean state) throws Exception ;
	  
	 
	  
	/**
	 * ���ڵ� ����
	 * */
	  int insert(BoardDTO boardDTO);
	  
	  /**
	   * �����ȣ�� �ش��ϴ� ���ڵ� ����
	   * */
	  int delete(int inquiryNum) throws Exception;
	  
	   /**
	    * �𵨹�ȣ�� �ش��ϴ� ���ڵ� ����
	    * */
	   /*int update(BoardDTO boardDTO) throws Exception;*/
}
