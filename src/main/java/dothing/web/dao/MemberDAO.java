package dothing.web.dao;

import java.util.List;

import dothing.web.dto.GPADTO;
import dothing.web.dto.MemberDTO;
import dothing.web.dto.MemberHashDTO;
import dothing.web.dto.NotificationDTO;

public interface MemberDAO {
	/**
	 * 해당하는 유저 강제 탈퇴 시키기
	 * @param String id: 해당하는 유저 아이디
	 * @return int: 성공 여부
	 */
	int deleteUser(String id);
	
	/**
	 * 페이지에 해당하는 유저 리스트 불러오기
	 * @param int page: 해당하는 페이지
	 * @param String id: null이 아닐경우 해당하는 유저 아이디로 검색
	 * @return List<MemberDTO> 유저dto 리스트
	 */
	List<MemberDTO> selectAll(int page, String id);
	
	/**
	 * 전체 유저들의 갯수 가져오기
	 * @param String id: null이 아닐 경우 유저 아이디로 검색했을 때의 갯수
	 * @return int: 유저들의 갯수
	 */
	int countAll(String id);


	/**
	 * 유저 정보 등록하기(회원가입)
	 * @param MemberDTO memberDTO: 등록하고자 하는 유저의 dto
	 * @return int: 성공 여부
	 */
	int insertMember(MemberDTO memberDTO);
	
	/**
	 * id에 해당하는 유저 정보 검색
	 * @param String id: 해당하는 유저 id
	 * @return MemberDTO: 유저 정보 dto
	 */
	MemberDTO selectMemberById(String id);

	/**
	 * 유저 id 중복체크하기
	 * @param String userId: 중복체크하고자 하는 유저 id
	 * @return MemberDTO: 유저 정보 dto
	 */
	MemberDTO selectSearch(String userId);
	
	/**
	 * 회원 가입시 초기 유저 포인트 생성하기
	 * @param String userId: 해당하는 유저 id
	 * @return int: 성공 여부
	 */
	int createPoint(String userId);
	
	/**
	 * 해당하는 유저의 정보 수정하기
	 * @param MemberDTO member: 수정하고자 하는 유저 정보 dto
	 * @return int: 성공 여부
	 */
	int updateMember(MemberDTO member);
	
	/**
	 * 해당하는 유저의 포인트 수정하기
	 * @param Integer point: 수정하고자 하는 point
	 * @param String id: 해당하는 유저의 id
	 * @return int: 성공 여부
	 */
	int updatePoint(Integer point, String id);
	
	/**
	 * 해당하는 유저에게 해시태그 추가
	 * @param MemberHashDTO dto: 유저와 해시태그에 대한 dto
	 * @return int: 성공 여부
	 */
	int insertHashtag(MemberHashDTO dto);
	
	/**
	 * 해당하는 유저의 해시태그 검색
	 * @param String id: 해당하는 유저의 아이디
	 * @return List<MemberHashDTO> 해시태그DTO 리스트
	 */
	List<MemberHashDTO> selectHashtag(String id);
	
	/**
	 * 해당하는 유저의 권한들 가져오기
	 * @param String id: 해당하는 유저의 id
	 * @return List<String>: 권한들의 List
	 */
	List<String> selectAuth(String id);

	/**
	 * 안전심부름꾼 등록하기
	 * @param MemberDTO dto: 등록하고자 하는 유저정보 dto
	 * @return int: 성공 여부
	 */
	int updateSafety(MemberDTO dto);
	
	
	/**
	 * 해당하는 유저에게 안전 권한 주기
	 * @param String id: 해당하는 유저 id
	 * @return int: 성공 여부
	 */
	int insertSafety(String id);
	
	
	/**
	 * 조건이 만족하지 않을 경우 안전심부름꾼 등록 취소시키기
	 * @param String id: 해당하는 유저 id
	 * @return int: 성공 여부
	 */
	int cancleSafety(String id);
	
	/**
	 * 주민등록사진은 올렸지만 안전심부름꾼이 아닌 유저 조회하기
	 * @param int page: 해당하는 page
	 * @return List<MemberDTO> 유저들의 리스트
	 */
	List<MemberDTO> selectNotSafety(int page);
	
	/**
	 * 주민등록사진은 올렸지만 안전심부름꾼이 아닌 유저들의 갯수 가져오기
	 * @return int: 안전심부름꾼이 아닌 유저들의 갯수
	 */
	int countNotSafety();

	/**
	 * 해당하는 유저의 평균 평점 정보들 가져오기
	 * @param String id: 해당하는 유저 id
	 * @return List<GPADTO> 평점정보 리스트
	 */
	List<GPADTO> averageGPA(String id);
	
	/**
	 * 해당하는 유저가 안전심부름꾼인가 조회하기
	 * @param String id: 해당하는 유저 id
	 * @return boolean: 안전심부름꾼일 경우 true, 아닐경우 false
	 */
	boolean isSafety(String id);
	
	/**
	 * 해당하는 유저에대한 알림리스트 가져오기
	 * @param String id: 해당하는 유저 id
	 * @param int page: 해당하는 페이지
	 * @return List<NotificationDTO>: 알림 DTO 리스트
	 */
	List<NotificationDTO> selectNotificationById(String id, int page);
	
	/**
	 * 해당하는 유저에게 알림 보내기
	 * @param String id: 해당하는 유저 아이디
	 * @param String content: 알림 내용
	 * @return int: 성공 여부
	 */
	int insertNotification(String id, String content);
	
	/**
	 * 해당하는 유저의 안 읽은 알림 갯수 가져오기
	 * @param String id: 해당하는 유저 id
	 * @return int: 안읽은 알림의 갯수
	 */
	int notReadNoti(String id);
	
	/**
	 * 해당하는 유저의 모든 알림 읽기상태로 업데이트
	 * @param String id: 해당하는 유저 id
	 * @return int: 성공 여부
	 */
	int allRead(String id);
	
	/**
	 * 해당하는 유저의 알림 갯수 가져오기
	 * @param String id: 해당하는 유저 id
	 * @return int: 알림 갯수
	 */
	int countNotification(String id);
	
	/**
	 * 해당하는 유저 이메일 인증 완료 처리하기
	 * @param String id: 해당하는 유저 id
	 * @return int: 성공 여부
	 */
	int finishEmail(String id);
}
