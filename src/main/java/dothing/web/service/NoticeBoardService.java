package dothing.web.service;

import java.util.List;

import dothing.web.dto.BoardDTO;
import dothing.web.dto.NoticeBoardDTO;

public interface NoticeBoardService {
	/**
	 * 레코드 전체 검색
	 */
	List<NoticeBoardDTO> selectAll(int page);

	/**
	 * 보드번호에 해당하는 레코드 검색
	 * 
	 * @param: state
	 *             true이면 조회수증가, false이면 조회증가안함.
	 */
	NoticeBoardDTO selectByBoardNum(int noticeNum, boolean state) throws Exception;

	/**
	 * 레코드 삽입
	 */
	int insert(NoticeBoardDTO boardDTO);

	/**
	 * 보드번호에 해당하는 레코드 삭제
	 */
	int delete(int noticeNum) throws Exception;

	/**
	 * 보드번호에 해당하는 레코드 수정
	 */
	/* int update(NoticeBoardDTO boardDTO) throws Exception; */
	
	/**
	 * 페이징
	 */
	int countNoticeList();

	
}
