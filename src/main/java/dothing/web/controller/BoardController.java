package dothing.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dothing.web.dto.BoardDTO;
import dothing.web.dto.BoardReplyDTO;
import dothing.web.dto.MemberDTO;
import dothing.web.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/inquiryBoardList")
	public ModelAndView list(){
		
		List<BoardDTO> list = boardService.selectAll();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/inquiryBoardList");
		mv.addObject("list",list);
		
		return mv;
	}
	
	
	@RequestMapping("/inquiryBoardWrite")
	public void write(){
		
	}
	
	@RequestMapping(value="/insert", produces="text/html;charset=UTF-8")
	public String insert(Authentication auth,BoardDTO boardDTO)throws Exception{
		String userId = ((MemberDTO)auth.getPrincipal()).getUserId();
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
	   * 상세보기
	   */
	  @RequestMapping("/inquiryBoardRead/{inquiryNum}")
	  public ModelAndView read(@PathVariable int inquiryNum) throws Exception{
		  BoardDTO boardDTO = boardService.selectByBoardNum(inquiryNum, true);
		  List<BoardReplyDTO>replyList = boardService.selectReply(inquiryNum);
		  ModelAndView mv = new ModelAndView();
		  mv.setViewName("board/inquiryBoardRead");
		  mv.addObject("board",boardDTO);
		  mv.addObject("reply",replyList);
		  return mv;
	  }
	  
	  /**
	   * 수정^^
	   */
	  /*@RequestMapping("/updateForm")
	  public ModelAndView updateForm(HttpServletRequest request, int inquiryNum) throws Exception{
		  BoardDTO boardDTO = boardService.selectByBoardNum(inquiryNum, false);
		 // return new ModelAndView("board/update","elec",elec);
		  return new ModelAndView("board/update","boardDTO",boardDTO);
	  }*/
	  
	  /**
	   * 삭제
	   */
	 @RequestMapping("/delete")
	  public String delete(Authentication auth,int inquiryNum)throws Exception{
		  String userId = ((MemberDTO)auth.getPrincipal()).getUserId();
		  String re = "";
		  
		  //if(userId==)
		  
		  boardService.delete(inquiryNum);
		  return "redirect:/board/inquiryBoardList";
	  }
	  
	/* @RequestMapping("/update")
	  public ModelAndView update(HttpServletRequest request, BoardDTO boardDTO)throws Exception{
		  boardService.update(boardDTO);

	      BoardDTO dbBoard = boardService.selectByBoardNum(boardDTO.getInquiryNum(), false);

		  return new ModelAndView("board/read", "boardDTO", dbBoard);
	  }*/
	 
	 /**
	  * 댓글삽입
	 * @throws Exception 
	  */
	 @RequestMapping("/insertReply")
	 public String insertReply(BoardReplyDTO brDTO) throws Exception{
		 boardService.insertReply(brDTO);
		 
		 return "redirect:/board/inquiryBoardRead/"+brDTO.getBoard().getInquiryNum();
	 }
	
}
