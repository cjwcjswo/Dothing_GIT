package dothing.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dothing.web.dao.AuthorityDAO;
import dothing.web.dao.MemberDAO;
import dothing.web.dto.AuthorityDTO;
import dothing.web.dto.MemberDTO;
import dothing.web.util.Constants;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDao;
	
	@Autowired
	private AuthorityDAO authorityDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public int joinMember(MemberDTO member) {
		//비밀번호 암호화
		String encodePass = passwordEncoder.encode(member.getPassword());
		
		member.setPassword(encodePass);
		
		memberDao.insertMember(member);
		
		
		authorityDAO.insertAuthority(new AuthorityDTO(member.getUserId(), Constants.ROLE_MEMBER));
		
		//관리자인 경우 권한 등록
		if(member.getUserType().equals("1")){
			authorityDAO.insertAuthority(new AuthorityDTO(member.getUserId() , Constants.ROLE_ADMIN));
		}
		
		return 1;
	}
}
