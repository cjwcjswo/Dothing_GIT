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
		//��й�ȣ ��ȣȭ
		String encodePass = passwordEncoder.encode(member.getPassword());
		
		member.setPassword(encodePass);
		
		memberDao.insertMember(member);
		
		
		authorityDAO.insertAuthority(new AuthorityDTO(member.getUserId(), Constants.ROLE_MEMBER));
		
		//�������� ��� ���� ���
		if(member.getUserType().equals("1")){
			authorityDAO.insertAuthority(new AuthorityDTO(member.getUserId() , Constants.ROLE_ADMIN));
		}
		
		return 1;
	}
}
