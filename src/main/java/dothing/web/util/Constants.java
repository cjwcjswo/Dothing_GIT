package dothing.web.util;


/**
 * 권한 관련 상수필드 인터페이스
 * */
public interface Constants {
	
	/**
	 * 관리자 권한
	 * */
	String ROLE_ADMIN = "ROLE_ADMIN";
	
	/**
	 * 회원 권한
	 * */
	String ROLE_MEMBER = "ROLE_MEMBER";		
	
	/**
	 * SMTP 호스트
	 */
	String SMTP_HOST = "smtp.gmail.com";
	
	/**
	 * 인증메일 제목
	 */
	String SMTP_TITLE = "Dothing 인증 이메일입니다";
	
	/**
	 * 인증메일 보내는사람
	 */
	String SMTP_FROM = "doothing123@gmail.com";

}
