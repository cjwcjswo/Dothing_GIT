package dothing.web.dao;

import java.util.List;

import dothing.web.dto.BoardDTO;


public interface BoardDAO{
   /**
     * ���ڵ� ��ü �˻�
     **/
     List<BoardDTO> selectAll() ;
     
    /**
        * �Խ��ǹ�ȣ�� �ش��ϴ� ���ڵ� �˻�
        * */
     BoardDTO selectByBoardNum(int inquiryNum) ;
     
     /**
      * �Խ��ǹ�ȣ�� �ش��ϴ� ���ڵ� ��ȸ�� �����ϱ�
      * */
     int readnumUpdate(int inquiryNum);
     
   /**
    * ���ڵ� ����
    * */
     int insert(BoardDTO boardDTO);
     
     /**
      * �Խ��ǹ�ȣ�� �ش��ϴ� ���ڵ� ����
      * */
     int delete(int inquiryNum);
     
      /**
       * �Խ��ǹ�ȣ�� �ش��ϴ� ���ڵ� ����
       * */
      /*int update(BoardDTO boardDTO);*/

}