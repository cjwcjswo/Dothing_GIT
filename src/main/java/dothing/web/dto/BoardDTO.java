package dothing.web.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardDTO {
	private int inquiryNum;      //게시판 번호
	private String userId;       //글쓴이
	private String boardTitle;   //게시판제목
	private String boardContent; //게시판내용
	private String boardDate;    //글쓴 날짜
	private int readNum;         //조회수
	
	
	private List<BoardReplyDTO> reply;  //댓글

	public int getInquiryNum() {
		return inquiryNum;
	}

	public void setInquiryNum(int inquiryNum) {
		this.inquiryNum = inquiryNum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}

	public List<BoardReplyDTO> getReply() {
		return reply;
	}

	public void setReply(List<BoardReplyDTO> reply) {
		this.reply = reply;
	}

	public int getReadNum() {
		return readNum;
	}

	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}
	
	
}
