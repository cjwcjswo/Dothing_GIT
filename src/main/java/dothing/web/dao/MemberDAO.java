package dothing.web.dao;

import dothing.web.dto.MemberDTO;

public interface MemberDAO {

	/**
	 * ȸ������
	 * */
	int insertMember(MemberDTO memberDTO);
	
	/**
	 * ID�� �ش��ϴ� ȸ������ �˻�
	 * */
	MemberDTO selectMemberById(String id);
	
}
