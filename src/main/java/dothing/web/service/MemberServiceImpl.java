package dothing.web.service;

import java.util.ArrayList;
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
import org.springframework.transaction.annotation.Transactional;

import dothing.web.dao.AuthorityDAO;
import dothing.web.dao.MemberDAO;
import dothing.web.dto.AuthorityDTO;
import dothing.web.dto.GPADTO;
import dothing.web.dto.MemberDTO;
import dothing.web.dto.MemberHashDTO;
import dothing.web.dto.NotificationDTO;
import dothing.web.util.Constants;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO memberDao;

	@Autowired
	private AuthorityDAO authorityDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * 회원가입
	 * boolean -> true: API 가입 / false: 일반 가입
	 */
	@Override
	@Transactional
	public int joinMember(MemberDTO member, boolean isAPI) {
		// 비밀번호 암호화
		String encodePass = passwordEncoder.encode(member.getPassword());

		member.setPassword(encodePass);
		if(!isAPI){
		int authNum = (int)((Math.random() * 99998) + 1);
		member.setState(authNum);
		sendEmail(member.getUserId(), authNum);
		}else{
			member.setState(0);
		}
		memberDao.insertMember(member);
		memberDao.createPoint(member.getUserId());

		authorityDAO.insertAuthority(new AuthorityDTO(member.getUserId(), Constants.ROLE_MEMBER));

		return 1;
	}

	@Override
	public String selectSearch(String userId) {
		MemberDTO dto = memberDao.selectSearch(userId);
		if (dto == null)
			return "사용가능합니다.";
		else
			return "사용중입니다";
	}

	/**
	 * 회원정보 수정
	 */
	@Override
	public int updateMember(MemberDTO member, MemberDTO original) {
		if (!(member.getPassword() == null || member.getPassword().equals(""))) { // 비밀번호를
																					// 바꾸고자
																					// 한다면
			String encodePass = passwordEncoder.encode(member.getPassword());
			member.setPassword(encodePass);
			original.setPassword(encodePass);
		} else {
			member.setPassword(null);
		}
		String updatePreAddr = member.getPreAddr();
		if(updatePreAddr != null && !updatePreAddr.trim().equals("")){
			original.setPreAddr(updatePreAddr);
			original.setDetailAddr(member.getDetailAddr());
		}
		if(member.getIntroduce() != null){
			System.out.println(member.getIntroduce());
			original.setIntroduce(member.getIntroduce());
			System.out.println(original.getIntroduce());
			
		}
		if (member.getPassword() == null && member.getSelfImg() == null && member.getPreAddr() == null && member.getIntroduce() == null) { 
			return 0;
		}
		memberDao.updateMember(member);
		return 1;
	}

	@Override
	public MemberDTO selectMemberById(String id) {
		return memberDao.selectMemberById(id);
	}

	/**
	 * 포인트 수정
	 */
	public int updatePoint(Integer point, String id) {
		return memberDao.updatePoint(point, id);
	}

	/**
	 * 해쉬태그 삽입
	 */
	@Override
	public int insertHashtag(int errandsNum, String id, String evalTag) {
		String[] tagList = evalTag.split(",");
		for (String tag : tagList) {
			memberDao.insertHashtag(new MemberHashDTO(errandsNum, id, tag.trim()));
		}
		return 1;
	}

	@Override
	public List<MemberDTO> selectAll(int page, String id) {
		return memberDao.selectAll(page, id);
	}

	@Override
	public int countAll(String id) {
		return memberDao.countAll(id);
	}

	@Override
	public int deleteUser(String id) {
		return memberDao.deleteUser(id);
	}

	@Override
	public List<String> selectAuth(String id) {
		return memberDao.selectAuth(id);
	}

	@Override
	public int updateSafety(MemberDTO dto) {
		return memberDao.updateSafety(dto);
	}
	/**
	 * 안전심부름꾼 등록하기
	 */
	@Override
	public int insertSafety(String id) {
		insertNotification(id, "안전심부름꾼이 되었습니다!");
		return memberDao.insertSafety(id);
	}

	@Override
	public List<MemberDTO> selectNotSafety(int page) {
		List<MemberDTO> memberList = new ArrayList<>();
		for (MemberDTO dto : memberDao.selectNotSafety(page)) {
			if (!memberDao.isSafety(dto.getUserId())) {
				memberList.add(dto);
			}
		}
		return memberList;
	}

	@Override
	public int countNotSafety() {
		return memberDao.countNotSafety();
	}

	@Override
	public List<MemberDTO> selectRanked() {
		List<GPADTO> gpaList = memberDao.averageGPA(null);
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		for (GPADTO dto : gpaList) {
			List<GPADTO> newList = new ArrayList<GPADTO>();
			newList.add(dto);
			MemberDTO member = memberDao.selectMemberById(dto.getUserId());
			member.setGpaList(newList);
			member.setHashList(memberDao.selectHashtag(dto.getUserId()));
			memberList.add(member);
		}
		return memberList;
	}

	@Override
	public List<NotificationDTO> selectNotificationById(String id, int page) {
		return memberDao.selectNotificationById(id, page);
	}
	/**
	 * 알림보내기
	 */
	@Override
	public int insertNotification(String id, String content) {
		return memberDao.insertNotification(id, content);
	}

	@Override
	public int notReadNoti(String id) {
		return memberDao.notReadNoti(id);
	}

	@Override
	public int allRead(String id) {
		return memberDao.allRead(id);
	}

	@Override
	public int countNotification(String id) {
		return memberDao.countNotification(id);
	}

	@Override
	public List<GPADTO> averageGPA(String id) {
		return memberDao.averageGPA(id);
	}

	@Override
	public List<MemberHashDTO> selectHashtag(String id) {
		return memberDao.selectHashtag(id);
	}
	
	/**
	 * 이메일 보내기
	 */
	@Override
	public void sendEmail(String email, Integer authNum) {
		String host = "smtp.gmail.com";
		String subject = "Dothing 인증확인 이메일입니다.";
		String fromName = "DoThing";
		String from = "doothing123@gmail.com";
		String to1 = email;
		String content = "가입을 축하드립니다! 아래 링크를 누르면 인증이 자동적으로 완료됩니다!" + "<br>" + 
		"<a href='http://localhost:8000/controller/user/emailOk?email="
				+ email + "&authNum=" + authNum +"'> 인증 확인하기 </a>";
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
	
	/**
	 * 이메일 인증 완료
	 */
	@Override
	public int finishEmail(String id) {
		return memberDao.finishEmail(id);
	}
	/**
	 * 안전맨 권한 거부
	 */
	@Override
	public int cancleSafety(String id) {
		insertNotification(id, "안전심부름꾼 조건이 만족하지 않아 취소되었습니다");
		return memberDao.cancleSafety(id);
	}
}
