package dothing.web.service;

import java.util.List;
import java.util.Map;

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
	
	/**
	 * 심부름번호를 통한 주문자의 상세정보조회
	 * */
	Map<String, Object> selectRequesterDetail(int errandNum);
}
