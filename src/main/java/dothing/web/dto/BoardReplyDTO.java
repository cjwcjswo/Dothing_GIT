package dothing.web.dto;

/**
 * 1:1 ���� �Խ��� ��� DTO
 */
public class BoardReplyDTO {
	private int replyNum; // ��� ��ȣ
	private BoardDTO board; // ����� �޸� �Խñ�
	private String replyContent; // ��� ����
	
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
