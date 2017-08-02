package dothing.web.dto;

/**
 * �ش��ϴ� ä�ù��� ������ ��ġ DTO
 */
public class ChatPosDTO {
	private String latitude; // ����
	private String longitude; // �浵
	private String time; // �ð�
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "ChatPosDTO [" + (latitude != null ? "latitude=" + latitude + ", " : "")
				+ (longitude != null ? "longitude=" + longitude + ", " : "") + (time != null ? "time=" + time : "")
				+ "]";
	}
	
	
}
