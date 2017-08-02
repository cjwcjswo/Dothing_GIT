package dothing.web.dto;

/**
 * 유저 권한 DTO
 */
public class AuthorityDTO {
	private String username; // 유저 아이디
	private String role; // 권한 내용
	
	public AuthorityDTO() {
		super();
	}

	public AuthorityDTO(String username, String role) {
		super();
		this.username = username;
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	
	
}
