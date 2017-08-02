package dothing.web.service;

import java.util.List;

import dothing.web.dto.GPADTO;
import dothing.web.dto.MemberDTO;
import dothing.web.dto.MemberHashDTO;
import dothing.web.dto.NotificationDTO;

public interface MemberService {
	/**
	 * 해당하는 유저 강제 탈퇴 시키기
	 * @param id 해당하는 유저 아이디
	 * @return 성공 여부
	 */
	int deleteUser(String id);
	
	/**
	 * 페이지에 해당하는 유저 리스트 불러오기
	 * @param page 해당하는 페이지
	 * @param id null이 아닐경우 해당하는 유저 아이디로 검색
	 * @return 유저dto 리스트
	 */
	List<MemberDTO> selectAll(int page, String id);
	
	/**
	 * 전체 유저들의 갯수 가져오기
	 * @param id null이 아닐 경우 유저 아이디로 검색했을 때의 갯수
	 * @return 유저들의 갯수
	 */
	int countAll(String id);
	
	/**
	 * 회원 가입하기
	 * @param member 회원가입하고자 하는 유저정보dto
	 * @param isAPI true = Facebook API 사용해서 가입, false = 일반 가입
	 * @return 성공 여부
	 */
	int joinMember(MemberDTO member, boolean isAPI);
	
	/**
	 * 유저 id 중복체크하기
	 * @param userId 중복체크하고자 하는 유저 id
	 * @return 사용 가능한지 가능하지 않은지 String 값
	 */
	String selectSearch(String userId);
	
	/**
	 * 해당하는 유저의 정보 수정하기
	 * @param member 수정하고자 하는 유저 정보 dto
	 * @param original 수정하기 전 기존 유저 정보 dto
	 * @return 성공 여부
	 */
	int updateMember(MemberDTO member, MemberDTO original);
	
	
	/**
	 * id에 해당하는 유저 정보 검색
	 * @param id 해당하는 유저 id
	 * @return 유저 정보 dto
	 */
	MemberDTO selectMemberById(String id);
	
	/**
	 * 해당하는 유저의 포인트 수정하기
	 * @param point 수정하고자 하는 point
	 * @param id 해당하는 유저의 id
	 * @return 성공 여부
	 */
	int updatePoint(Integer point, String id);
	
	/**
	 * 해당하는 유저에게 해시태그 추가
	 * @param errandsNum 해당하는 심부름 번호
	 * @param id 해당하는 유저 아이디
	 * @param evalTag 해시태그
	 * @param isAndroid 안드로이드 여부
	 * @return 성공 여부
	 */
	int insertHashtag(int errandsNum, String id, String evalTag, boolean isAndroid);
	
	/**
	 * 해당하는 유저의 권한들 가져오기
	 * @param id 해당하는 유저의 id
	 * @return 권한들의 List
	 */
	List<String> selectAuth(String id);
	
	/**
	 * 안전심부름꾼 등록하기
	 * @param dto 등록하고자 하는 유저정보 dto
	 * @return 성공 여부
	 */
	int updateSafety(MemberDTO dto);
	
	/**
	 * 해당하는 유저에게 안전 권한 주기
	 * @param id 해당하는 유저 id
	 * @return 성공 여부
	 */
	int insertSafety(String id);
	
	
	/**
	 * 조건이 만족하지 않을 경우 안전심부름꾼 등록 취소시키기
	 * @param id 해당하는 유저 id
	 * @return 성공 여부
	 */
	int cancleSafety(String id);
	
	/**
	 * 주민등록사진은 올렸지만 안전심부름꾼이 아닌 유저 조회하기
	 * @param page 해당하는 page
	 * @return 유저들의 리스트
	 */
	List<MemberDTO> selectNotSafety(int page);
	
	/**
	 * 주민등록사진은 올렸지만 안전심부름꾼이 아닌 유저들의 갯수 가져오기
	 * @return  안전심부름꾼이 아닌 유저들의 갯수
	 */
	int countNotSafety();
	
	/**
	 * 평점이 높은 유저들 순서대로 정렬하여 조회
	 * @return 멤버정보dto
	 */
	List<MemberDTO> selectRanked();
	
	/**
	 * 해당하는 유저에대한 알림리스트 가져오기
	 * @param id 해당하는 유저 id
	 * @param page 해당하는 페이지
	 * @return 알림 DTO 리스트
	 */
	List<NotificationDTO> selectNotificationById(String id, int page);
	
	/**
	 * 해당하는 유저에게 알림 보내기
	 * @param id 해당하는 유저 아이디
	 * @param content 알림 내용
	 * @return 성공 여부
	 */
	int insertNotification(String id, String content);
	
	/**
	 * 해당하는 유저의 안 읽은 알림 갯수 가져오기
	 * @param id 해당하는 유저 id
	 * @return 안읽은 알림의 갯수
	 */
	int notReadNoti(String id);
	
	/**
	 * 해당하는 유저의 모든 알림 읽기상태로 업데이트
	 * @param id 해당하는 유저 id
	 * @return 성공 여부
	 */
	int allRead(String id);
	
	/**
	 * 해당하는 유저의 알림 갯수 가져오기
	 * @param id 해당하는 유저 id
	 * @return 알림 갯수
	 */
	int countNotification(String id);
	
	/**
	 * 해당하는 유저의 평균 평점 정보들 가져오기
	 * @param id 해당하는 유저 id
	 * @return 평점정보 리스트
	 */
	List<GPADTO> averageGPA(String id);
	 
	 /**
		 * 해당하는 유저의 해시태그 검색
		 * @param id 해당하는 유저의 아이디
		 * @return 해시태그DTO 리스트
		 */
	 List<MemberHashDTO> selectHashtag(String id);
	 
	 /**
	  * 해당하는 유저에게 인증 메일 보내기
	  * @param email 해당하는 유저 이메일
	  * @param authNum 인증 번호
	  */
	 void sendEmail(String email, Integer authNum);


	 /**
		 * 해당하는 유저 이메일 인증 완료 처리하기
		 * @param id 해당하는 유저 id
		 * @return 성공 여부
		 * */
	 int finishEmail(String id);
}
