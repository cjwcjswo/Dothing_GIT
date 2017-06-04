package dothing.web.dto;

public class PointDTO {
	private UserDTO user;
	private int currentPoint;
	private int requestPoint;
	
	
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
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
