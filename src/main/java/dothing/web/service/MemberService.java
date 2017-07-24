package dothing.web.service;

import java.util.List;

import dothing.web.dto.GPADTO;
import dothing.web.dto.MemberDTO;
import dothing.web.dto.MemberHashDTO;
import dothing.web.dto.NotificationDTO;

public interface MemberService {
	/**
	 * 회원 강퇴
	 */
	int deleteUser(String id);
	/**
	 * 회원 목록 불러오기
	 */
	List<MemberDTO> selectAll(int page, String id);
	int countAll(String id);
	/**
	 * 회원가입
	 * isAPI => true: API false: 일반
	 * */
	int joinMember(MemberDTO member, boolean isAPI);
	
	/**
	 * ID중복체크
	 * */
	String selectSearch(String userId);
	
	/**
	 * 정보수정
	 */
	int updateMember(MemberDTO member, MemberDTO original);
	
	
	/**
	 * 멤버 가져오기
	 */
	MemberDTO selectMemberById(String id);
	
	/**
	 * 포인트 수정
	 */
	int updatePoint(Integer point, String id);
	
	/**
	 * 해쉬태그 삽입
	 */
	int insertHashtag(int errandsNum, String id, String evalTag, boolean isAndroid);
	/**
	 * 권한 가져오기
	 */
	List<String> selectAuth(String id);
	
	/**
	 * 안전심부름꾼 이미지 등록
	 */
	int updateSafety(MemberDTO dto);
	/**
	 * 안전권한 추가
	 */
	int insertSafety(String id);
	/**
	 * 안전승인취소
	 */
	int cancleSafety(String id);
	/**
	 * 낫안줜맨들
	 */
	List<MemberDTO> selectNotSafety(int page);
	int countNotSafety();
	/**
	 * 랭킹맨들
	 */
	List<MemberDTO> selectRanked();
	
	/**
	 * 해당하는 유저에대한 알림 가져오기
	 */
	List<NotificationDTO> selectNotificationById(String id, int page);
	
	/**
	 * 알림보내기
	 */
	int insertNotification(String id, String content);
	
	/**
	 * 안읽은 알람 있니?
	 */
	int notReadNoti(String id);
	
	/**
	 * 해당하는 아이디 알람 읽기
	 * 
	 */
	int allRead(String id);
	int countNotification(String id);
	
	 List<GPADTO> averageGPA(String id);
	 List<MemberHashDTO> selectHashtag(String id);
	 
	 /**
	  * 이메일 보내기
	  */
	 void sendEmail(String email, Integer authNum);
	 /**
	  * 이메일 인증 완료
	  */
	 int finishEmail(String id);
}
