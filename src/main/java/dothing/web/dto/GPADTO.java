package dothing.web.dto;

import java.io.Serializable;
/**
 * 평점 DTO
 */
public class GPADTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int errandsNum; // 평점이 달린 심부름 번호
	private String userId; // 평가 받은 유저 아이디
	private Integer responseAccuracy; // 배달 정확성
	private Integer responseSpeed; // 배달 속도
	private Integer responseKindness; // 배달 친절함
	private Integer requestManners; // 요청 친절함

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