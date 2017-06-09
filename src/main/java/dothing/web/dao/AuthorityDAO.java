package dothing.web.dao;

import java.util.List;

import dothing.web.dto.AuthorityDTO;

public interface AuthorityDAO {

	/**
	 * 사용자 권한 등록
	 * (한 USER(ID)가 여러개의 권한을 가질 수 있다.
	 * */
	int insertAuthority(AuthorityDTO authorityDTO);
	
	/**
	 * ID에 해당하는 권한 검색
	 * */
	List<AuthorityDTO> selectAuthorityByUserName(String userName);
	
	
	
	
}
