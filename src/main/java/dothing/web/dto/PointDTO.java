package dothing.web.dto;

import java.io.Serializable;

public class PointDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
