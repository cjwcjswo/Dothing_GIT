package dothing.web.dto;

public class NotificationDTO {
	private int notificationNum;
	private String memberId;
	private String content;
	private String writeDate;
	private int isRead;
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
