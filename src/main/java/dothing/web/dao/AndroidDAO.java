package dothing.web.dao;

import java.util.List;

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
	
	/**
	 * 푸시메세지 토큰 추카
	 */
	int insertToken(String memberId, String token);
	
	/**
	 * 등록된 심부름에 위도/경도/거리에따른 토큰 조회
	 */
	List<String> selectTokenByDistance(String latitude, String longitude, Integer distance);
}
