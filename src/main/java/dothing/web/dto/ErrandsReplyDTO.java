package dothing.web.dto;

/**
 * 심부름 댓글 DTO
 */
public class ErrandsReplyDTO {
	private int replyNum; // 댓글 번호
	private ErrandsDTO errands; // 댓글 달린 심부름 DTO
	private String replyContent; // 댓글 내용
	private String arrivalTime; // 예상 도착 시간
	private String replyDate; // 날짜
	private MemberDTO user; // 댓글 쓴 사람
	
	
	@Override
	public String toString() {
		return "ErrandsReplyDTO [replyNum=" + replyNum + ", errands=" + errands + ", replyContent=" + replyContent
				+ ", arrivalTime=" + arrivalTime + ", replyDate=" + replyDate + ", user=" + user + "]";
	}
	public int getReplyNum() {
		return replyNum;
	}
	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}
	public ErrandsDTO getErrands() {
		return errands;
	}
	public void setErrands(ErrandsDTO errands) {
		this.errands = errands;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}
	public MemberDTO getUser() {
		return user;
	}
	public void setUser(MemberDTO user) {
		this.user = user;
	}
	
	
}
