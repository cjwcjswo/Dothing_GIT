package dothing.web.dto;

/**
 * 1:1 ¹®ÀÇ °Ô½ÃÆÇ ´ñ±Û DTO
 */
public class BoardReplyDTO {
	private int replyNum; // ´ñ±Û ¹øÈ£
	private BoardDTO board; // ´ñ±ÛÀÌ ´Þ¸° °Ô½Ã±Û
	private String replyContent; // ´ñ±Û ³»¿ë
	
	public int getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}
	public BoardDTO getBoard() {
		return board;
	}
	public void setBoard(BoardDTO board) {
		this.board = board;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	
	
}
