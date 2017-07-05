package dothing.web.security.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dothing.web.dao.AuthorityDAO;
import dothing.web.dao.MemberDAO;
import dothing.web.dto.AuthorityDTO;
import dothing.web.dto.MemberDTO;

@Service //id="memberAuthenticationProvider"
public class MemberAuthenticationProvider implements AuthenticationProvider{
		
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private AuthorityDAO authorityDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		if(!supports(auth.getClass())){
			return null;
		}
		
		// 1. �μ��� ���� ���� ������ ������ ��� �����ϴ��� üũ
		
		String id = auth.getName();
		MemberDTO member = memberDAO.selectMemberById(id);
		if(member==null){// id�� ���� ��� 
			throw new UsernameNotFoundException(id + "�� ���� ȸ���Դϴ�.");
		}
		
		// 2. �����ϸ� ��й�ȣ ��
		String password = (String)auth.getCredentials();
		
		
		if(!passwordEncoder.matches(password, member.getPassword())){
			throw new BadCredentialsException("�н����� �����Դϴ�.");
		}
		if(member.getState() > 0) {
			
			throw new BadCredentialsException("�̸��� ������ Ȯ�����ּ���.:"+member.getUserId()+":"+member.getState());
		}
		////////////// ������ ������ �� ./////////////////
		// 3. ��� ��ġ�ϸ� Authentication�� ���� ���� 
		List<AuthorityDTO> list = authorityDAO.selectAuthorityByUserName(id);
		
		if(list.isEmpty()){
			//�ƹ� ������ ���� ���
			throw new UsernameNotFoundException(id + "�� �ƹ� ������ �����ϴ�.");
		}
		
		// db���� ������ �� ������ GrantedAuthority�� ��ȯ �ؾ���
		List<SimpleGrantedAuthority> authList = new ArrayList<>();
		for(AuthorityDTO authority : list){
			authList.add(new SimpleGrantedAuthority(authority.getRole()));
		}
		
		return new UsernamePasswordAuthenticationToken(member, null, authList);
	}

	/**
	 * �ش� Ÿ���� authentication ��ü�� �̿��ؼ� ���� ó����
	 * �� �� �ִ��� ���θ� �������ִ� �޼ҵ�
	 * */
	@Override
	public boolean supports(Class<?> authentication) {
		
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
