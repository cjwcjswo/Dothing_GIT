package dothing.web.dao;

import java.util.List;

import dothing.web.dto.MemberDTO;
import dothing.web.dto.MemberHashDTO;

public interface MemberDAO {
	int deleteUser(String id);
	/**
	 * 모든 유저 불러오기
	 */
	List<MemberDTO> selectAll(int page, String id);
	int countAll(String id);
	/**
	 * 회원가입
	 * */
	int insertMember(MemberDTO memberDTO);
	
	/**
	 * ID에 해당하는 회원정보 검색
	 * */
	MemberDTO selectMemberById(String id);
	
	/**
	 * ID 중복체크
	 * */
	MemberDTO selectSearch(String userId);
	
	/**
	 * 초기 포인트 생성
	 */
	int createPoint(String userId);
	
	/**
	 * 정보 수정
	 */
	int updateMember(MemberDTO member);
	
	/**
	 * 포인트 수정
	 */
	int updatePoint(Integer point, String id);
	
	/**
	 * 유저 해쉬태그 추가
	 */
	int insertHashtag(MemberHashDTO dto);
	
	/**
	 * 유저 해쉬태그 검색
	 */
	List<MemberHashDTO> selectHashtag(String id);
	
	/**
	 * 권한 가져오기
	 */
	List<String> selectAuth(String id);
	
	/**
	 * 안전심부름꾼 이미지 등록
	 */
	int updateSafety(MemberDTO dto);
	/**
	 * 안전권한등록
	 */
	int insertSafety(String id);
	/**
	 * 안전사진은 올렸는데 권한이 없는자
	 */
	List<MemberDTO> selectNotSafety(int page);
	int countNotSafety();
}
