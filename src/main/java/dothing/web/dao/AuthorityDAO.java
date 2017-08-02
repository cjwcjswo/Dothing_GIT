package dothing.web.dao;

import java.util.List;

import dothing.web.dto.AuthorityDTO;

public interface AuthorityDAO {

	/**
	 * 회원에게 권한 등록
	 * @param authorityDTO 권한정보에 대한 DTO
	 * @return 성공 여부
	 * */
	int insertAuthority(AuthorityDTO authorityDTO);
	
	/**
	 * 해당하는 회원에 대한 권한 정보 불러오기
	 * @param userId 회원 아이디
	 * @return 권한정보에 대한 DTO 리스트
	 * */
	List<AuthorityDTO> selectAuthorityByUserName(String userId);
	
	
	
	
}
