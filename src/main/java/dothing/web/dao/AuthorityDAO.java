package dothing.web.dao;

import java.util.List;

import dothing.web.dto.AuthorityDTO;

public interface AuthorityDAO {

	/**
	 * ����� ���� ���
	 * (�� USER(ID)�� �������� ������ ���� �� �ִ�.
	 * */
	int insertAuthority(AuthorityDTO authorityDTO);
	
	/**
	 * ID�� �ش��ϴ� ���� �˻�
	 * */
	List<AuthorityDTO> selectAuthorityByUserName(String userName);
	
	
	
	
}
