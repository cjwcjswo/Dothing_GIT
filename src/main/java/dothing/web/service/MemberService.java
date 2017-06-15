package dothing.web.service;

import dothing.web.dto.MemberDTO;

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
	int updateMember(MemberDTO member);
}
