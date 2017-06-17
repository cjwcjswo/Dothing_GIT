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
}
