package dothing.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import dothing.web.dao.AndroidDAO;
import dothing.web.dao.AuthorityDAO;
import dothing.web.dao.MemberDAO;
import dothing.web.dto.AuthorityDTO;
import dothing.web.dto.ChatPosDTO;
import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.MemberDTO;
import dothing.web.util.Constants;

@Service
@Transactional
public class AndroidServiceImpl implements AndroidService {

	@Autowired
	AndroidDAO androidDAO;
	
	@Autowired
	MemberDAO memberDAO;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthorityDAO authorityDAO;
	

	@Override
	public int androidSignIn(MemberDTO memberDTO) {
		String encodePass = passwordEncoder.encode(memberDTO.getPassword()); // 비밀번호 암호화
		memberDTO.setPassword(encodePass);
		memberDTO.setState(0); // 안드로이드로 회원 가입 시 state(웹에서의 인증값)가 0이다.
		
		int result = androidDAO.androidSignIn(memberDTO);
		memberDAO.createPoint(memberDTO.getUserId());
		authorityDAO.insertAuthority(new AuthorityDTO(memberDTO.getUserId(), Constants.ROLE_MEMBER));
		
		return result;
	}

	@Override
	public int insertToken(String memberId, String token) {
		return androidDAO.insertToken(memberId, token);
	}

	@Override
	public List<String> selectTokenByDistance(String latitude, String longitude, Integer distance) {
		return androidDAO.selectTokenByDistance(latitude, longitude, distance);
	}
	
	@Override
	public String androidSendEmail(String email) {
		int authNum = (int)((Math.random() * 99998) + 1); // 랜덤한 인증번호 생성
		
		// 인증 메일 정보 셋팅
		String host = Constants.SMTP_HOST;
		String subject = Constants.SMTP_TITLE;
		String fromName = "DoThing";
		String from = Constants.SMTP_FROM;
		String to1 = email;
		String content = "[DoThing] Email 인증번호는 "+authNum+" 입니다. 정확히 입력해주세요.";
		
		try {
			Properties props = new Properties();
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", host);
			props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.auth", "true");
			
			Session mailSession = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("doothing123", "dvorakdoothing");
				}
			});
			
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName, "UTF-8", "B")));
			InternetAddress[] address1 = { new InternetAddress(to1) };
			msg.setRecipients(Message.RecipientType.TO, address1);
			msg.setSubject(subject);
			msg.setSentDate(new java.util.Date());
			msg.setContent(content, "text/html;charset=UTF-8");
			Transport.send(msg); // 메일 보내기
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return authNum+"";
	}
	
	@Override
	public Map<String, Object> selectRequesterDetail(int errandNum) {
		Map<String, Object> map = new HashMap<>();
		
		String requesterId = androidDAO.selectRequesterId(errandNum);
		System.out.println("requesterId : " + requesterId);
		int requestCount = androidDAO.selectRequestCount(requesterId);
		int grade = androidDAO.selectRequestGPA(requesterId);
		List<Object> hashtagList = androidDAO.selectMemberHashtag(requesterId);

		System.out.println("length : " + hashtagList.size());
		
		// map에 주문자 정보 추가
		map.put("requesterId", requesterId);
		map.put("requestCount", requestCount);
		map.put("grade", grade);
		map.put("hashtagList", hashtagList);
		
		return map;
	}

	@Override
	public List<ErrandsDTO> selectChatList(String memberId) {
		return androidDAO.selectChatList(memberId);
	}

	@Override
	public int initLocation(int errandsNum, String memberId) {
		return androidDAO.initLocation(errandsNum, memberId);
	}

	@Override
	public int updateLocation(String memberId, String latitude, String longitude) {
		return androidDAO.updateLocation(memberId, latitude, longitude);
	}

	@Override
	public ChatPosDTO selectLocation(int errandsNum, String memberId) {
		return androidDAO.selectLocation(errandsNum, memberId);
	}

	@Override
	public String selectTokenById(String memberId) {
		return androidDAO.selectTokenById(memberId);
	}
	
	
	
}
