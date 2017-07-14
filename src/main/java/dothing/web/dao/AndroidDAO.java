package dothing.web.dao;

import dothing.web.dto.MemberDTO;

public interface AndroidDAO {
	
	/**
	 * 안드로이드 로그인
	 */
	String androidLogin(String email);
	
	/**
	 * 회원가입
	 */
	int androidSignIn(MemberDTO memberDTO);
}
