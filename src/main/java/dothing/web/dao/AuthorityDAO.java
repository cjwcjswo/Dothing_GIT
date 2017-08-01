package dothing.web.dao;

import java.util.List;

import dothing.web.dto.AuthorityDTO;

public interface AuthorityDAO {

	/**
	 * ȸ������ ���� ���
	 * @param AuthorityDTO authorityDTO: ���������� ���� DTO
	 * @return int: ���� ����
	 * */
	int insertAuthority(AuthorityDTO authorityDTO);
	
	/**
	 * �ش��ϴ� ȸ���� ���� ���� ���� �ҷ�����
	 * @param String userName: ȸ�� ���̵�
	 * @return List<AuthorityDTO>: ���������� ���� DTO ����Ʈ
	 * */
	List<AuthorityDTO> selectAuthorityByUserName(String userId);
	
	
	
	
}
