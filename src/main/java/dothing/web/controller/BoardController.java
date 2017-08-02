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
 * 1:1 ���ǰԽ��� / �����Խ��� / FAQ �Խ��� ��Ʈ�ѷ�
 */
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
	 * 1:1 �Խ��� ����Ʈ �ҷ�����
	 * @param auth �����ΰ� ����ΰ� ���� üũ
	 * @param page ������
	 */
	@RequestMapping("/inquiryBoardList")
	public ModelAndView list(Authentication auth, Integer page) throws Exception{
		
		if (page == null) page = new Integer(1);
		
		// 5���� �Խñ��� ���������� ����
		PageMaker pm = new PageMaker(page, boardService.countList()/ 6 + 1);
		pm.start();

		String userId = ((MemberDTO) auth.getPrincipal()).getUserId();
		
		if(userId == null) throw new Exception("�α����ϼ���.");
		
		ModelAndView mv = new ModelAndView();

		// ����� ���
		if (userId.equals("cjwcjswo@hufs.ac.kr")) {
			List<BoardDTO> list = boardService.selectAll(page);
			mv.setViewName("board/inquiryBoardList");
			mv.addObject("pm", pm);
			mv.addObject("list", list);
		}
		// �Ϲ� ������ ���
		else{
			List<BoardDTO> list = boardService.selectAllMember(page, userId);
			mv.setViewName("board/inquiryBoardList");
			mv.addObject("pm", pm);
			mv.addObject("list", list);
		}

		return mv;
	}

	/**
	 * 1:1 �Խ��� �۾��� �������� �̵�
	 */
	@RequestMapping("/inquiryBoardWriteNew")
	public void write() {}

	/**
	 * 1:1 �Խ��� �۾��� ���μ��� ��Ʈ�ѷ�
	 * @param auth �α��� �� ������ ���̵� �������� ����
	 * @param boardDTO �� �� �����
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
	 * 1:1 ���� �Խ��� �󼼺��� ������
	 * @param inquiryNum �󼼺���� �� �� ��ȣ
	 */
	@RequestMapping("/inquiryBoardReadNew/{inquiryNum}")
	public ModelAndView read(@PathVariable int inquiryNum) throws Exception {
		BoardDTO boardDTO = boardService.selectByBoardNum(inquiryNum, true);
		List<BoardReplyDTO> replyList = boardService.selectReply(inquiryNum);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/inquiryBoardReadNew");
		mv.addObject("board", boardDTO);  //�Խ��� ����
		mv.addObject("reply", replyList); //���
		return mv;
	}


	/**
	 * 1:1 ���ǰԽ��� ������� ��Ʈ�ѷ�
	 * @param inquiryNum ���� ��ų �Խù� ��ȣ
	 */
	@RequestMapping(value="/inquiryDelete", method=RequestMethod.GET)
	public String delete(@RequestParam(value="inquiryNum")int inquiryNum) throws Exception {
		boardService.delete(inquiryNum);
		return "redirect:/board/inquiryBoardList";
	}
	

	/**
	 * 1:1 ���ǰԽ��� ��� �ޱ� ��Ʈ�ѷ�
	 * @param auth �α��� �� ����(��� �ٴ� ���)
	 * @param brDTO ��� ������� ��� DTO
	 */
	@RequestMapping("/insertReply")
	public String insertReply(Authentication auth, BoardReplyDTO brDTO) throws Exception {
		boardService.insertReply(brDTO);
		return "redirect:/board/inquiryBoardReadNew/" + brDTO.getBoard().getInquiryNum();

	}
	
	/////////////////////////////////////////////////////////////////
	///////////////////////////�����Խ���////////////////////////////
    /////////////////////////////////////////////////////////////////
	/**
	 * �����Խ��� ����Ʈ �ҷ�����
	 * @param page ������
	 */
	@RequestMapping("/noticeBoardList")
	public ModelAndView notice(Integer page) {
		if (page == null) page = new Integer(1);
		
		// 5���� �Խù��� �� �������� ����
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
	 * �����Խ��� �󼼺���
	 * @param noticeNum �󼼺����� �����Խñ� ��ȣ
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
	 * ���� �Խñ� �����ϱ�
	 * @param noticeNum ������ �����Խñ� ��ȣ
	 */
	@RequestMapping(value="/noticeDelete", method=RequestMethod.GET)
	public String noticeDelete(@RequestParam(value="noticeNum")int noticeNum) throws Exception {
		
		noticeService.delete(noticeNum);
		return "redirect:/board/noticeBoardList";
	}
	
	/**
	 * �����Խ��� ���� �������� �̵��ϴ� ��Ʈ�ѷ�
	 */
	@RequestMapping("/noticeBoardWrite")
	public void noticeWrite(){}
	
	/**
	 * �����Խ��� �۾��� ���
	 * @param boardDTO �����Խñ� ���� DTO
	 */
	@RequestMapping(value = "/noticeInsert", produces = "text/html;charset=UTF-8")
	public String noticeInsert(NoticeBoardDTO boardDTO) throws Exception {

		String re = "";
	
		boardDTO.setUserId("cjwcjswo@hufs.ac.kr");
		int result = noticeService.insert(boardDTO);
		
		if (result > 0) {
			re = "redirect:/board/noticeBoardList";
		} else {
			throw new Exception("�� ��� ����");
		}
		return re;
	}
	
	/**
	 * FAQ�Խ��� �̵� ��Ʈ�ѷ�
	 */
	@RequestMapping("/faq")
	public void faq(){}
}
