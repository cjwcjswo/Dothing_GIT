package dothing.web.dao;

import java.util.List;

import dothing.web.dto.BoardDTO;
import dothing.web.dto.BoardReplyDTO;
import dothing.web.dto.NoticeBoardDTO;

public interface NoticeBoardDAO {
	/**
	 * 레코드 전체 검색
	 **/
	List<NoticeBoardDTO> selectAll(int page);

	/**
	 * 게시판번호에 해당하는 레코드 검색
	 */
	NoticeBoardDTO selectByBoardNum(int noticeNum);

	/**
	 * 게시판번호에 해당하는 레코드 조회수 증가하기
	 */
	int readnumUpdate(int noticeNum);

	/**
	 * 레코드 삽입
	 */
	int insert(NoticeBoardDTO boardDTO);

	/**
	 * 게시판번호에 해당하는 레코드 삭제
	 */
	int delete(int noticeNum);

	/**
	 * 게시판번호에 해당하는 레코드 수정
	 */
	/* int update(BoardDTO boardDTO); */
	
	/**
	 * 페이징
	 */
	int countNoticeList();

}
