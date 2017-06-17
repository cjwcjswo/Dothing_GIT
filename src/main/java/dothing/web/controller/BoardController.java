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

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private NoticeBoardService noticeService;
	
	
    /////////////////////////////////////////////////////////////////
    ///////////////////////////1��1�Խ���////////////////////////////
    /////////////////////////////////////////////////////////////////
	/**
	 * 1��1�Խ��� ����
	 */
	@RequestMapping("/inquiryBoardList")
	public ModelAndView list() {

		List<BoardDTO> list = boardService.selectAll();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/inquiryBoardList");
		mv.addObject("list", list);

		return mv;
	}

	/**
	 * 1��1�Խ��� �� �����
	 */
	@RequestMapping("/inquiryBoardWrite")
	public void write() {

	}

	/**
	 * 1��1�Խ��� �۾��� ���
	 */
	@RequestMapping(value = "/insert", produces = "text/html;charset=UTF-8")
	public String insert(Authentication auth, BoardDTO boardDTO) throws Exception {
		String userId = ((MemberDTO) auth.getPrincipal()).getUserId();
		String re = "";
		if (userId == null) {
			throw new Exception("�α����� �̿��ϼ���");
		} else {
			boardDTO.setUserId(userId);

			int result = boardService.insert(boardDTO);

			if (result > 0) {
				re = "redirect:/board/inquiryBoardList";
			} else {
				throw new Exception("�� ��� ����");
			}
		}
		return re;
	}

	/**
	 * 1��1�Խ��� �󼼺���
	 */
	@RequestMapping("/inquiryBoardReadNew/{inquiryNum}")
	public ModelAndView read(@PathVariable int inquiryNum) throws Exception {
		BoardDTO boardDTO = boardService.selectByBoardNum(inquiryNum, true);
		List<BoardReplyDTO> replyList = boardService.selectReply(inquiryNum);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/inquiryBoardReadNew");
		mv.addObject("board", boardDTO);  //�Խ��� ����
		mv.addObject("reply", replyList); //���
		System.out.println("���û�����" + replyList.size());
		return mv;
	}

	/**
	 * 1��1�Խ��� ����
	 */
	/*
	 * @RequestMapping("/updateForm") public ModelAndView
	 * updateForm(HttpServletRequest request, int inquiryNum) throws Exception{
	 * BoardDTO boardDTO = boardService.selectByBoardNum(inquiryNum, false); //
	 * return new ModelAndView("board/update","elec",elec); return new
	 * ModelAndView("board/update","boardDTO",boardDTO); }
	 */

	/**
	 * 1��1�Խ��� ����
	 */
	@RequestMapping("/delete")
	public String delete(Authentication auth, int inquiryNum) throws Exception {
		String userId = ((MemberDTO) auth.getPrincipal()).getUserId();
		String re = "";

		// if(userId==)

		boardService.delete(inquiryNum);
		return "redirect:/board/inquiryBoardList";
	}

	/*
	 * @RequestMapping("/update") public ModelAndView update(HttpServletRequest
	 * request, BoardDTO boardDTO)throws Exception{
	 * boardService.update(boardDTO);
	 * 
	 * BoardDTO dbBoard =
	 * boardService.selectByBoardNum(boardDTO.getInquiryNum(), false);
	 * 
	 * return new ModelAndView("board/read", "boardDTO", dbBoard); }
	 */

	/**
	 * 1��1�Խ��� ��ۻ���
	 */
	@RequestMapping("/insertReply")
	public String insertReply(BoardReplyDTO brDTO) throws Exception {
		boardService.insertReply(brDTO);
		return "redirect:/board/inquiryBoardRead/" + brDTO.getBoard().getInquiryNum();
	}
	
	/////////////////////////////////////////////////////////////////
	///////////////////////////�����Խ���////////////////////////////
    /////////////////////////////////////////////////////////////////
	/**
     * �����Խ��� ����
     */
	@RequestMapping("/noticeBoardList")
	public ModelAndView notice(Integer page) {
		if (page == null)
			page = new Integer(1);
		
		PageMaker pm = new PageMaker(page, noticeService.countNoticeList()/ 6 + 1);
		//System.out.println("noticeService.countNoticeList()/ 6 + 1:" + noticeService.countNoticeList()/ 6 + 1);
		pm.start();
		List<NoticeBoardDTO> list = noticeService.selectAll(page);
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("board/noticeBoardList");
		mv.addObject("pm", pm);
		mv.addObject("list", list);

		return mv;
	}
	
	/**
	 * �����Խ��� �󼼺���
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
	 * �����Խ��� ����
	 */
	@RequestMapping(value="/noticeDelete", method=RequestMethod.GET)
	public String noticeDelete(@RequestParam(value="noticeNum")int noticeNum) throws Exception {
       
		noticeService.delete(noticeNum);
		return "redirect:/board/noticeBoardList";
	}
	
	/**
	 * �����Խ��� ���� ��
	 */
	@RequestMapping("/noticeBoardWrite")
	public void noticeWrite(){
		
	}
	
	/**
	 * �����Խ��� �۾��� ���
	 */
	@RequestMapping(value = "/noticeInsert", produces = "text/html;charset=UTF-8")
	public String noticeInsert(NoticeBoardDTO boardDTO) throws Exception {

		String re = "";
		
		boardDTO.setUserId("tester");

		int result = noticeService.insert(boardDTO);
		
		if (result > 0) {
			re = "redirect:/board/noticeBoardList";
		} else {
			throw new Exception("�� ��� ����");
		}
		return re;
	}
	
	@RequestMapping("/inquiryBoardRead(new)")
	public void inquiryBoardRead(){
		
	}
}
