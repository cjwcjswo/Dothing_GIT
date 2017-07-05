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
		
		// 1. 인수로 받은 유저 정보를 가지고 디비에 존재하는지 체크
		
		String id = auth.getName();
		MemberDTO member = memberDAO.selectMemberById(id);
		if(member==null){// id가 없는 경우 
			throw new UsernameNotFoundException(id + "는 없는 회원입니다.");
		}
		
		// 2. 존재하면 비밀번호 비교
		String password = (String)auth.getCredentials();
		
		
		if(!passwordEncoder.matches(password, member.getPassword())){
			throw new BadCredentialsException("패스워드 오류입니다.");
		}
		if(member.getState() > 0) {
			
			throw new BadCredentialsException("이메일 인증을 확인해주세요.:"+member.getUserId()+":"+member.getState());
		}
		////////////// 인증에 성공한 후 ./////////////////
		// 3. 모두 일치하면 Authentication를 만들어서 리턴 
		List<AuthorityDTO> list = authorityDAO.selectAuthorityByUserName(id);
		
		if(list.isEmpty()){
			//아무 권한이 없는 경우
			throw new UsernameNotFoundException(id + "는 아무 권한이 없습니다.");
		}
		
		// db에서 가지고 온 권한을 GrantedAuthority로 변환 해야함
		List<SimpleGrantedAuthority> authList = new ArrayList<>();
		for(AuthorityDTO authority : list){
			authList.add(new SimpleGrantedAuthority(authority.getRole()));
		}
		
		return new UsernamePasswordAuthenticationToken(member, null, authList);
	}

	/**
	 * 해당 타입의 authentication 객체를 이용해서 인증 처리를
	 * 할 수 있는지 여부를 리턴해주는 메소드
	 * */
	@Override
	public boolean supports(Class<?> authentication) {
		
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
