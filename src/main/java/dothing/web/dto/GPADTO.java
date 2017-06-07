package dothing.web.dto;

public class GPADTO {
	private ErrandsDTO errands;
	private MemberDTO user;
	private int responseAccuracy;
	private int responseSpeed;
	private int responseKindness;
	private int requestManners;
	
	
	
	public ErrandsDTO getErrands() {
		return errands;
	}
	public void setErrands(ErrandsDTO errands) {
		this.errands = errands;
	}
	public MemberDTO getUser() {
		return user;
	}
	public void setUser(MemberDTO user) {
		this.user = user;
	}
	public int getResponseAccuracy() {
		return responseAccuracy;
	}
	public void setResponseAccuracy(int responseAccuracy) {
		this.responseAccuracy = responseAccuracy;
	}
	public int getResponseSpeed() {
		return responseSpeed;
	}
	public void setResponseSpeed(int responseSpeed) {
		this.responseSpeed = responseSpeed;
	}
	public int getResponseKindness() {
		return responseKindness;
	}
	public void setResponseKindness(int responseKindness) {
		this.responseKindness = responseKindness;
	}
	public int getRequestManners() {
		return requestManners;
	}
	public void setRequestManners(int requestManners) {
		this.requestManners = requestManners;
	}
	
	
}
