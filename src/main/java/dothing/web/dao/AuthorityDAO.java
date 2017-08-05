package dothing.web.dao;

import java.util.List;

import dothing.web.dto.AuthorityDTO;

public interface AuthorityDAO {

	/**
	 * ȸ������ ���� ���
	 * @param authorityDTO ���������� ���� DTO
	 * @return ���� ����
	 * */
	int insertAuthority(AuthorityDTO authorityDTO);
	
	/**
	 * �ش��ϴ� ȸ���� ���� ���� ���� �ҷ�����
	 * @param userId ȸ�� ���̵�
	 * @return ���������� ���� DTO ����Ʈ
	 * */
	List<AuthorityDTO> selectAuthorityByUserName(String userId);
	
	
	
	
}
