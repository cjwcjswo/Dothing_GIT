package dothing.web.dto;

public class NotificationDTO {
	private int notificationNum;
	private String memberId;
	private String content;
	private String writeDate;
	private int isRead;
	
	public NotificationDTO(){}
	public NotificationDTO(int notificationNum, String memberId, String content, String writeDate, int isRead) {
		super();
		this.notificationNum = notificationNum;
		this.memberId = memberId;
		this.content = content;
		this.writeDate = writeDate;
		this.isRead = isRead;
	}
	public int getNotificationNum() {
		return notificationNum;
	}
	public void setNotificationNum(int notificationNum) {
		this.notificationNum = notificationNum;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	
	
}
