package dothing.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dothing.web.dto.BoardDTO;
import dothing.web.dto.BoardReplyDTO;
import dothing.web.dto.MemberDTO;
import dothing.web.dto.NoticeBoardDTO;
import dothing.web.service.BoardService;
import dothing.web.service.NoticeBoardService;
import dothing.web.util.PageMaker;

/**
 * 1:1 문의게시판 / 공지게시판 / FAQ 게시판 컨트롤러
 */
@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private NoticeBoardService noticeService;
	
	
    /////////////////////////////////////////////////////////////////
    ///////////////////////////1대1게시판////////////////////////////
    /////////////////////////////////////////////////////////////////
	/**
	 * 1:1 게시판 리스트 불러오기
	 * @param auth 유저인가 운영자인가 권한 체크
	 * @param page 페이지
	 */
	@RequestMapping("/inquiryBoardList")
	public ModelAndView list(Authentication auth, Integer page) throws Exception{
		
		if (page == null) page = new Integer(1);
		
		// 5개의 게시글을 한페이지로 묶음
		PageMaker pm = new PageMaker(page, boardService.countList()/ 6 + 1);
		pm.start();

		String userId = ((MemberDTO) auth.getPrincipal()).getUserId();
		
		if(userId == null) throw new Exception("로그인하세요.");
		
		ModelAndView mv = new ModelAndView();

		// 운영자일 경우
		if (userId.equals("cjwcjswo@hufs.ac.kr")) {
			List<BoardDTO> list = boardService.selectAll(page);
			mv.setViewName("board/inquiryBoardList");
			mv.addObject("pm", pm);
			mv.addObject("list", list);
		}
		// 일반 유저일 경우
		else{
			List<BoardDTO> list = boardService.selectAllMember(page, userId);
			mv.setViewName("board/inquiryBoardList");
			mv.addObject("pm", pm);
			mv.addObject("list", list);
		}

		return mv;
	}

	/**
	 * 1:1 게시판 글쓰는 페이지로 이동
	 */
	@RequestMapping("/inquiryBoardWriteNew")
	public void write() {}

	/**
	 * 1:1 게시판 글쓰기 프로세스 컨트롤러
	 * @param auth 로그인 된 유저의 아이디를 가져오기 위함
	 * @param boardDTO 글 쓴 내용들
	 */
	@RequestMapping(value = "/insert", produces = "text/html;charset=UTF-8")
	public String insert(Authentication auth, BoardDTO boardDTO) throws Exception {
		String userId = ((MemberDTO) auth.getPrincipal()).getUserId();
		String re = "";
		
		if (userId == null) {
			throw new Exception("로그인후 이용하세요");
		} else {
			boardDTO.setUserId(userId);
			int result = boardService.insert(boardDTO);
			
			if (result > 0) {
				re = "redirect:/board/inquiryBoardList";
			} else {
				throw new Exception("글 등록 실패");
			}
		}
		return re;
	}

	/**
	 * 1:1 문의 게시판 상세보기 페이지
	 * @param inquiryNum 상세보기로 볼 글 번호
	 */
	@RequestMapping("/inquiryBoardReadNew/{inquiryNum}")
	public ModelAndView read(@PathVariable int inquiryNum) throws Exception {
		BoardDTO boardDTO = boardService.selectByBoardNum(inquiryNum, true);
		List<BoardReplyDTO> replyList = boardService.selectReply(inquiryNum);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/inquiryBoardReadNew");
		mv.addObject("board", boardDTO);  //게시판 내용
		mv.addObject("reply", replyList); //댓글
		return mv;
	}


	/**
	 * 1:1 문의게시판 삭제기능 컨트롤러
	 * @param inquiryNum 삭제 시킬 게시물 번호
	 */
	@RequestMapping(value="/inquiryDelete", method=RequestMethod.GET)
	public String delete(@RequestParam(value="inquiryNum")int inquiryNum) throws Exception {
		boardService.delete(inquiryNum);
		return "redirect:/board/inquiryBoardList";
	}
	

	/**
	 * 1:1 문의게시판 댓글 달기 컨트롤러
	 * @param auth 로그인 한 유저(댓글 다는 사람)
	 * @param brDTO 댓글 내용들이 담긴 DTO
	 */
	@RequestMapping("/insertReply")
	public String insertReply(Authentication auth, BoardReplyDTO brDTO) throws Exception {
		boardService.insertReply(brDTO);
		return "redirect:/board/inquiryBoardReadNew/" + brDTO.getBoard().getInquiryNum();

	}
	
	/////////////////////////////////////////////////////////////////
	///////////////////////////공지게시판////////////////////////////
    /////////////////////////////////////////////////////////////////
	/**
	 * 공지게시판 리스트 불러오기
	 * @param page 페이지
	 */
	@RequestMapping("/noticeBoardList")
	public ModelAndView notice(Integer page) {
		if (page == null) page = new Integer(1);
		
		// 5개의 게시물을 한 페이지로 묶음
		PageMaker pm = new PageMaker(page, noticeService.countNoticeList()/ 6 + 1);
		pm.start();
		
		List<NoticeBoardDTO> list = noticeService.selectAll(page);
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("board/noticeBoardList");
		mv.addObject("pm", pm);
		mv.addObject("list", list);

		return mv;
	}
	
	/**
	 * 공지게시판 상세보기
	 * @param noticeNum 상세보기할 공지게시글 번호
	 */
	@RequestMapping("/noticeBoardRead/{noticeNum}")
	public ModelAndView noticeRead(@PathVariable int noticeNum) throws Exception {
		NoticeBoardDTO boardDTO = noticeService.selectByBoardNum(noticeNum, true);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/noticeBoardRead");
		mv.addObject("board", boardDTO);
		return mv;
	}
	
	/**
	 * 공지 게시글 삭제하기
	 * @param noticeNum 삭제할 공지게시글 번호
	 */
	@RequestMapping(value="/noticeDelete", method=RequestMethod.GET)
	public String noticeDelete(@RequestParam(value="noticeNum")int noticeNum) throws Exception {
		
		noticeService.delete(noticeNum);
		return "redirect:/board/noticeBoardList";
	}
	
	/**
	 * 공지게시판 쓰기 페이지로 이동하는 컨트롤러
	 */
	@RequestMapping("/noticeBoardWrite")
	public void noticeWrite(){}
	
	/**
	 * 공지게시판 글쓰기 기능
	 * @param boardDTO 공지게시글 내용 DTO
	 */
	@RequestMapping(value = "/noticeInsert", produces = "text/html;charset=UTF-8")
	public String noticeInsert(NoticeBoardDTO boardDTO) throws Exception {

		String re = "";
	
		boardDTO.setUserId("cjwcjswo@hufs.ac.kr");
		int result = noticeService.insert(boardDTO);
		
		if (result > 0) {
			re = "redirect:/board/noticeBoardList";
		} else {
			throw new Exception("글 등록 실패");
		}
		return re;
	}
	
	/**
	 * FAQ게시판 이동 컨트롤러
	 */
	@RequestMapping("/faq")
	public void faq(){}
}
