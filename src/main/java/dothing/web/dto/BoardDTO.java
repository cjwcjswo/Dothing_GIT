package dothing.web.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class BoardDTO {
	private int inquiryNum;      //�Խ��� ��ȣ
	private String userId;       //�۾���
	private String boardTitle;   //�Խ�������
	private String boardContent; //�Խ��ǳ���
	private String boardDate;    //�۾� ��¥
	private int readNum;         //��ȸ��
	
	
	private List<BoardReplyDTO> reply;  //���

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
