package dothing.web.dto;

/**
 * �ɺθ� ��� DTO
 */
public class ErrandsReplyDTO {
	private int replyNum; // ��� ��ȣ
	private ErrandsDTO errands; // ��� �޸� �ɺθ� DTO
	private String replyContent; // ��� ����
	private String arrivalTime; // ���� ���� �ð�
	private String replyDate; // ��¥
	private MemberDTO user; // ��� �� ���
	
	
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
