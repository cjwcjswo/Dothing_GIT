package dothing.web.service;

import java.util.List;

import dothing.web.dto.MemberDTO;

public interface AndroidService {
	String androidLogin(String email,String password);
	
	int androidSignIn(MemberDTO memberDTO);
	
	/**
	 * 푸시메세지 토큰 추가
	 */
	int insertToken(String memberId, String token);
	
	/**
	 * 위도/경도/거리에따른 토큰조회
	 */
	List<String> selectTokenByDistance(String latitude, String longitude, Integer distance);
}
