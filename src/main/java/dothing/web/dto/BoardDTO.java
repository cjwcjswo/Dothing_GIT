package dothing.web.dto;

import java.util.List;

public class BoardDTO {
	private int boardNum;
	private UserDTO user;
	private String boardTitle;
	private String boardContent;
	private String boardDate;
	
	private List<BoardReplyDTO> reply;

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
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
	
	
}
