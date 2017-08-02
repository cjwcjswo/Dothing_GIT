package dothing.web.dto;

/**
 * 해당하는 채팅방의 유저의 위치 DTO
 */
public class ChatPosDTO {
	private String latitude; // 위도
	private String longitude; // 경도
	private String time; // 시간
	
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
