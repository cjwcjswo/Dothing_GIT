package dothing.web.service;

import java.util.List;
import java.util.Map;

import dothing.web.dto.ChatPosDTO;
import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.MemberDTO;

public interface AndroidService {
	
	/**
	 * 안드로이드에서의 회원가입처리
	 * @param memberDTO 회원에 대한 DTO
	 * @return 성공 여부
	 */
	int androidSignIn(MemberDTO memberDTO);
	
	/**
	 * 해당하는 유저에게 FCM 토큰 값 삽입
	 * @param memberId 해당하는 유저의 id
	 * @param token 토큰 값
	 * @return 성공 여부
	 */
	int insertToken(String memberId, String token);
	
	/**
	 * 위도 / 경도 / 거리의 반경에 해당하는 유저들의 토큰들을 가져오기
	 * @param latitude 위도
	 * @param longitude 경도
	 * @param distance 거리
	 * @return 토큰들의 리스트
	 */
	List<String> selectTokenByDistance(String latitude, String longitude, Integer distance);
	
	/**
	 * 안드로이드로 해당하는 유저에게 인증 이메일 보내기
	 * @param email 해당하는 유저의 email
	 * @return 인증번호 숫자
	 */
	String androidSendEmail(String email);
	
	/**
	 * 해당하는 심부름의 주문자 상세정보 조회하기
	 * @param errandNum 해당하는 심부름 번호
	 * @return 심부름 주문자에대한 정보를 담은 Map
	 */
	Map<String, Object> selectRequesterDetail(int errandNum);
	
	/**
	 * 해당하는 유저의 채팅 리스트 가져오기
	 * @param memberId 해당하는 유저의 id
	 * @return 해시태그의 리스트
	 */
	List<ErrandsDTO> selectChatList(String memberId);
	
	/**
	 * 해당하는 유저와 심부름의 위치 정보 초기화
	 * @param memberId 해당하는 유저의 id
	 * @param errandsNum 심부름 번호
	 * @return 성공 여부
	 */
	int initLocation(int errandsNum, String memberId);
	
	/**
	 * 해당하는 유저와 현재 위치정보 업데이트
	 * @param memberId 해당하는 유저의 id
	 * @param latitude 위도
	 * @param longitude 경도
	 * @return 성공 여부
	 */
	int updateLocation(String memberId, String latitude, String longitude);
	
	
	/**
	 * 해당하는 유저와 심부름의 위치정보 불러오기
	 * @param errandsNum 해당하는 심부름 번호
	 * @param memberId 해당하는 유저의 id
	 * @return 채팅 위치정보 DTO
	 */
	ChatPosDTO selectLocation(int errandsNum, String memberId);
	
	
	/**
	 * 해당하는 유저의 FCM 토큰 값 가져오기
	 * @param memberId 해당하는 유저의 id
	 * @return 토큰 값
	 */
	String selectTokenById(String memberId);
}
