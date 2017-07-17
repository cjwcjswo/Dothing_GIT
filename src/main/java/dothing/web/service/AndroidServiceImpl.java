package dothing.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dothing.web.dao.AndroidDAO;
import dothing.web.dto.MemberDTO;

@Service
public class AndroidServiceImpl implements AndroidService {

	@Autowired
	AndroidDAO androidDAO;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public String androidLogin(String email,String password) {
		String dbPassword = androidDAO.androidLogin(email);
		String result="";
		
		//ID가 존재하지 않을때
		if(dbPassword == null){
			result = "fail";
		} else { //ID가 존재할때
		
			if(passwordEncoder.matches(password, dbPassword)){
				result = "success";
			} else {
				result = "fail";
			}
		}
		return result;
	}

	@Override
	public int androidSignIn(MemberDTO memberDTO) {
		String encodePass = passwordEncoder.encode(memberDTO.getPassword());
		memberDTO.setPassword(encodePass);
		
		return androidDAO.androidSignIn(memberDTO);
	}

	@Override
	public int insertToken(String memberId, String token) {
		return androidDAO.insertToken(memberId, token);
	}

	@Override
	public List<String> selectTokenByDistance(String latitude, String longitude, Integer distance) {
		return androidDAO.selectTokenByDistance(latitude, longitude, distance);
	}
	
	
	
}
