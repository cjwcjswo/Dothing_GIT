package dothing.web.service;

import dothing.web.dto.MemberDTO;

public interface MemberService {

	
	/**
	 * ȸ������
	 * */
	int joinMember(MemberDTO member);
	
	/**
	 * ID�ߺ�üũ
	 * */
	String selectSearch(String userId);
	
	/**
	 * ��������
	 */
	int updateMember(MemberDTO member);
}
