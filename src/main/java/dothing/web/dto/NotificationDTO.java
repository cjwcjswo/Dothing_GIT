package dothing.web.dto;

/**
 * 알림 DTO
 *
 */
public class NotificationDTO {
	private int notificationNum; // 알림 번호
	private String memberId; // 유저 아이디
	private String content; // 알림 내용
	private String writeDate; // 알림 날짜
	private int isRead; // 읽었는지 안읽었는지
	
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
