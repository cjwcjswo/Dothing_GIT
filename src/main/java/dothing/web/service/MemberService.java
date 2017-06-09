package dothing.web.service;

import dothing.web.dto.MemberDTO;

public interface MemberService {

	
	/**
	 * 회원가입
	 * */
	int joinMember(MemberDTO member);
}
