package dothing.web.dto;

public class GPADTO {
	private int errandsNum;
	private String userId;
	private Integer responseAccuracy;
	private Integer responseSpeed;
	private Integer responseKindness;
	private Integer requestManners;

	public Integer getResponseAccuracy() {
		return responseAccuracy;
	}
	public void setResponseAccuracy(Integer responseAccuracy) {
		this.responseAccuracy = responseAccuracy;
	}
	public Integer getResponseSpeed() {
		return responseSpeed;
	}
	public void setResponseSpeed(Integer responseSpeed) {
		this.responseSpeed = responseSpeed;
	}
	public Integer getResponseKindness() {
		return responseKindness;
	}
	public void setResponseKindness(Integer responseKindness) {
		this.responseKindness = responseKindness;
	}
	public Integer getRequestManners() {
		return requestManners;
	}
	public void setRequestManners(Integer requestManners) {
		this.requestManners = requestManners;
	}
	public int getErrandsNum() {
		return errandsNum;
	}
	public void setErrandsNum(int errandsNum) {
		this.errandsNum = errandsNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "GPADTO [errandsNum=" + errandsNum + ", userId=" + userId + ", responseAccuracy=" + responseAccuracy
				+ ", responseSpeed=" + responseSpeed + ", responseKindness=" + responseKindness + ", requestManners="
				+ requestManners + "]";
	}

	
}