package dothing.web.service;

import java.util.List;

import dothing.web.dto.BoardDTO;
import dothing.web.dto.BoardReplyDTO;

public interface BoardService {
	/**
	 * 게시글 전체 가져오기(운영자권한으로 보기)
	 * @param page 해당하는 페이지
	 * @return 게시글 DTO 리스트
	 * */
	List<BoardDTO> selectAll(int page);
	
	/**
	 * 게시글 전체 가져오기(유저 권한으로 보기)
	 * @param page 해당하는 페이지
	 * @param userId 유저 아이디
	 * @return 게시글 DTO 리스트
	 * */
	List<BoardDTO> selectAllMember(int page, String userId);

	/**
	 * 게시글 번호에 해당하는 게시글 가져오기
	 * @param inquiryNum 게시글 번호
	 * @param state true일 경우 조회수 증가, false일 경우 그대로
	 * @return 게시글 DTO
	 * */
	BoardDTO selectByBoardNum(int inquiryNum, boolean state) throws Exception;

	/**
	 * 게시글 등록하기
	 * @param boardDTO 게시글 DTO
	 * @return 성공 여부
	 * */
	int insert(BoardDTO boardDTO);

	/**
	 * 게시글 삭제하기
	 * @param inquiryNum 게시글 번호
	 * @return 성공 여부
	 * */
	int delete(int inquiryNum) throws Exception;
	
	/**
	 * 게시글에 댓글 등록하기
	 * @param brDTO 댓글 DTO
	 * @return 성공 여부
	 * */
	int insertReply(BoardReplyDTO brDTO)throws Exception;

	/**
	 * 게시글에 해당하는 댓글 리스트 가져오기
	 * @param inquiryNum 게시글 번호
	 * @return 댓글 DTO 리스트
	 * */
	List<BoardReplyDTO> selectReply(int inquiryNum);
	
	/**
	 * 전체 게시글 갯수 가져오기
	 * @return 전체 공지게시글 count
	 * */
	int countList();
}
