package dothing.web.service;

import java.util.List;

import dothing.web.dto.BoardDTO;
import dothing.web.dto.NoticeBoardDTO;

public interface NoticeBoardService {
	
	/**
	 * 페이지에 해당하는 공지게시판 게시글 불러오기
	 * @param page 해당하는 페이지
	 * @return 공지게시글 리스트
	 */
	List<NoticeBoardDTO> selectAll(int page);

	/**
	 * 공지게시글 번호에 해당하는 게시글 정보 가져오기
	 * @param noticeNum 공기게시글 번호
	 * @param state true = 조회수 증가, false = 그대로
	 * @return 게시글 정보
	 */
	NoticeBoardDTO selectByBoardNum(int noticeNum, boolean state) throws Exception;

	/**
	 * 공지게시글 등록하기
	 * @param boardDTO: 등록하고자하는 공지게시글 정보 dto
	 * @return 성공여부
	 */
	int insert(NoticeBoardDTO boardDTO);

	/**
	 * 공지게시글 삭제하기
	 * @param noticeNum 해당하는 공지 게시글 번호
	 * @return 성공 여부
	 */
	int delete(int noticeNum) throws Exception;

	
	/**
	 * 공지게시글 갯수 가져오기
	 * @return 공지게시글 갯수
	 * 	 */
	int countNoticeList();

	
}
