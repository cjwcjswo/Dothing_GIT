package dothing.web.service;

import dothing.web.dto.MemberDTO;

public interface AndroidService {
	String androidLogin(String email,String password);
	
	int androidSignIn(MemberDTO memberDTO);
}
