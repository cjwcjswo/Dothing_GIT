package dothing.web.dto;

import java.io.Serializable;

/**
 * 포인트 DTO
 */
public class PointDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private MemberDTO user; // 해당 유저
	private int currentPoint; // 현재 포인트
	private int requestPoint; // 신청 포인트
	
	
	
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
