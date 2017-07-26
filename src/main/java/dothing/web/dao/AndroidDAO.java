package dothing.web.dao;

import java.util.List;

import dothing.web.dto.ChatPosDTO;
import dothing.web.dto.ErrandsDTO;
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
	
	/**
	 * 심부름번호로 주문자의 ID조회
	 * */
	String selectRequesterId(int errandNum);
	
	/**
	 * 회원ID로 주문횟수조회 
	 * */
	int selectRequestCount(String memberId);
	
	/**
	 * 회원ID로 주문평점조회
	 * */
	int selectRequestGPA(String memberId);
	
	/**
	 * 회원ID로 해당 회원에게 달린 해시태그들 조회
	 * */
	List<Object> selectMemberHashtag(String memberId);
	
	/**
	 * 채팅 리스트 조회
	 */
	List<ErrandsDTO> selectChatList(String memberId);
	
	/**
	 * 내 위치 초기화(매칭될때 최초 등록)
	 */
	int initLocation(int errandsNum, String memberId);
	
	/**
	 * 위치 업데이트
	 */
	int updateLocation(String memberId, String latitude, String longitude);
	
	/**
	 * 위치 불러오기
	 */
	ChatPosDTO selectLocation(int errandsNum, String memberId);
	
	String selectTokenById(String memberId);
}
