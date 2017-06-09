package dothing.web.dao;

import dothing.web.dto.MemberDTO;

public interface MemberDAO {

	/**
	 * 회원가입
	 * */
	int insertMember(MemberDTO memberDTO);
	
	/**
	 * ID에 해당하는 회원정보 검색
	 * */
	MemberDTO selectMemberById(String id);
	
}
