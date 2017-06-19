package dothing.web.service;

import dothing.web.dto.MemberDTO;
import dothing.web.dto.MemberHashDTO;

public interface MemberService {

	
	/**
	 * 회원가입
	 * */
	int joinMember(MemberDTO member);
	
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
	int insertHashtag(int errandsNum, String id, String evalTag);
	
}
