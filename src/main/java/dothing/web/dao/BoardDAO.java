package dothing.web.dao;

import java.util.List;

import dothing.web.dto.BoardDTO;


public interface BoardDAO{
   /**
     * 레코드 전체 검색
     **/
     List<BoardDTO> selectAll() ;
     
    /**
        * 게시판번호에 해당하는 레코드 검색
        * */
     BoardDTO selectByBoardNum(int inquiryNum) ;
     
     /**
      * 게시판번호에 해당하는 레코드 조회수 증가하기
      * */
     int readnumUpdate(int inquiryNum);
     
   /**
    * 레코드 삽입
    * */
     int insert(BoardDTO boardDTO);
     
     /**
      * 게시판번호에 해당하는 레코드 삭제
      * */
     int delete(int inquiryNum);
     
      /**
       * 게시판번호에 해당하는 레코드 수정
       * */
      /*int update(BoardDTO boardDTO);*/

}