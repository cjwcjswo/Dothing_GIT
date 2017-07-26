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
import dothing.web.dao.MemberDAO;
import dothing.web.dto.ChatPosDTO;
import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.MemberDTO;

@Service
public class AndroidServiceImpl implements AndroidService {

	@Autowired
	AndroidDAO androidDAO;
	
	@Autowired
	MemberDAO memberDAO;
	
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
		
		memberDTO.setState(0);
		
		int result = androidDAO.androidSignIn(memberDTO);
		memberDAO.createPoint(memberDTO.getUserId());
		
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
		int authNum = (int)((Math.random() * 99998) + 1);
		
		String host = "smtp.gmail.com";
		String subject = "Dothing 인증확인 이메일입니다.";
		String fromName = "DoThing";
		String from = "doothing123@gmail.com";
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
			Transport.send(msg);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return authNum+"";
	}
	@Transactional
	public Map<String, Object> selectRequesterDetail(int errandNum) {
		Map<String, Object> map = new HashMap<>();
		
		String requesterId = androidDAO.selectRequesterId(errandNum);
		System.out.println("requesterId : " + requesterId);
		int requestCount = androidDAO.selectRequestCount(requesterId);
		int grade = androidDAO.selectRequestGPA(requesterId);
		List<Object> hashtagList = androidDAO.selectMemberHashtag(requesterId);

		System.out.println("length : " + hashtagList.size());
		
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
