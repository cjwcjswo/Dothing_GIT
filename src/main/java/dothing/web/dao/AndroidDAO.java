package dothing.web.dao;

import dothing.web.dto.MemberDTO;

public interface AndroidDAO {
	
	/**
	 * �ȵ���̵� �α���
	 */
	String androidLogin(String email);
	
	/**
	 * ȸ������
	 */
	int androidSignIn(MemberDTO memberDTO);
}
