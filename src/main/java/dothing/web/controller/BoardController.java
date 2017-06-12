package dothing.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dothing.web.dto.BoardDTO;
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
	
	/*@RequestMapping("/inquiryBoardList")
	public String inquiryBoardList(){
		return "/board/inquiryBoardList" ;
	}*/
	
	@RequestMapping("/inquiryBoardWrite")
	public void write(HttpServletRequest request){
		
	}
	
	@RequestMapping(value="/insert", produces="text/html;charset=UTF-8")
	public String insert(HttpServletRequest request, BoardDTO boardDTO){

		boardService.insert(boardDTO);
		
		return "redirect:list";
	}
	  
	  /**
	   * 상세보기
	   */
	  @RequestMapping("/inquiryBoardRead/{modelNum}")
	  public ModelAndView read(HttpServletRequest request,@PathVariable int inquiryNum) throws Exception{
		  BoardDTO boardDTO = boardService.selectByBoardNum(inquiryNum, true);
		  ModelAndView mv = new ModelAndView();
		  mv.setViewName("board/read");
		  mv.addObject("board",boardDTO);
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
	  
	 /* @RequestMapping("/downLoad")
	  public ModelAndView downLoad(HttpServletRequest request, String fName){
		  
		  return new ModelAndView("downLoadView", "fname", new File(path+"/"+fName));  //downLoadView.jsp 이동
	  }*/
	  
	  /**
	   * 삭제
	   */
	 /* @RequestMapping("/delete")
	  public String delete(HttpServletRequest request, int inquiryNum, String password)throws Exception{
		  boardService.delete(inquiryNum, password);
		  return "redirect:list";
	  }*/
	  
	/* @RequestMapping("/update")
	  public ModelAndView update(HttpServletRequest request, BoardDTO boardDTO)throws Exception{
		  boardService.update(boardDTO);

	      BoardDTO dbBoard = boardService.selectByBoardNum(boardDTO.getInquiryNum(), false);

		  return new ModelAndView("board/read", "boardDTO", dbBoard);
	  }*/
	
}
