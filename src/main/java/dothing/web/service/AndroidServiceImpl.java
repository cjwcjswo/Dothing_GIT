package dothing.web.service;

import java.util.List;
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

import dothing.web.dao.AndroidDAO;
import dothing.web.dao.MemberDAO;
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
		
		int authNum = (int)((Math.random() * 99998) + 1);
		memberDTO.setState(authNum);
		androidSendEmail(memberDTO.getUserId(), authNum);
		
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
	public void androidSendEmail(String email, Integer authNum) {
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
	}
	
	
	
}
