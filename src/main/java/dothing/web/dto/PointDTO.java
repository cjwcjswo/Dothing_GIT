package dothing.web.dto;

public class PointDTO {
	private MemberDTO user;
	private int currentPoint;
	private int requestPoint;
	
	
	
	public MemberDTO getUser() {
		return user;
	}
	public void setUser(MemberDTO user) {
		this.user = user;
	}
	public int getCurrentPoint() {
		return currentPoint;
	}
	public void setCurrentPoint(int currentPoint) {
		this.currentPoint = currentPoint;
	}
	public int getRequestPoint() {
		return requestPoint;
	}
	public void setRequestPoint(int requestPoint) {
		this.requestPoint = requestPoint;
	}
	
	
}
