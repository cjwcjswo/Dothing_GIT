package dothing.web.service;

import java.util.List;

import dothing.web.dto.BoardDTO;

public interface BoardService {
	/**
	  * 레코드 전체 검색
	  * */
	  List<BoardDTO> selectAll() ;
	  
	  /**
		  * 보드번호에 해당하는 레코드 검색
		  * @param: state true이면 조회수증가, false이면 조회증가안함.
		  * */
	  BoardDTO selectByBoardNum(int inquiryNum , boolean state) throws Exception ;
	  
	 
	  
	/**
	 * 레코드 삽입
	 * */
	  int insert(BoardDTO boardDTO);
	  
	  /**
	   * 보드번호에 해당하는 레코드 삭제
	   * */
	  int delete(int inquiryNum) throws Exception;
	  
	   /**
	    * 모델번호에 해당하는 레코드 수정
	    * */
	   /*int update(BoardDTO boardDTO) throws Exception;*/
}
