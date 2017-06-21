package dothing.web.service;

import java.util.ArrayList;
import java.util.List;

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
		memberDao.createPoint(member.getUserId());
		
		authorityDAO.insertAuthority(new AuthorityDTO(member.getUserId(), Constants.ROLE_MEMBER));
		
		return 1;
	}

	@Override
	public String selectSearch(String userId) {
		MemberDTO dto = memberDao.selectSearch(userId);
		if(dto==null)return "사용가능합니다.";
		else return "사용중입니다";
	}

	@Override
	public int updateMember(MemberDTO member, MemberDTO original) {
		if(!(member.getPassword() == null || member.getPassword().equals(""))){
		String encodePass = passwordEncoder.encode(member.getPassword());
		member.setPassword(encodePass);
		original.setPassword(encodePass);
		} else{
			member.setPassword(null);
		}
		if(member.getPassword() == null && member.getSelfImg() == null && member.getAddr() == null){
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
	public int updatePoint(Integer point, String id){
		return memberDao.updatePoint(point, id);
	}

	/**
	 * 해쉬태그 삽입
	 */
	@Override
	public int insertHashtag(int errandsNum, String id, String evalTag) {
		String[] tagList = evalTag.split(",");
		for(String tag : tagList){
			memberDao.insertHashtag(new MemberHashDTO(errandsNum, id, tag.trim()));
		}
		return 1;
	}

	@Override
	public List<MemberDTO> selectAll(int page, String id) {
		return memberDao.selectAll(page, id);
	}
	@Override
	public int countAll(String id){
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

	@Override
	public int insertSafety(String id) {
		return memberDao.insertSafety(id);
	}

	@Override
	public List<MemberDTO> selectNotSafety(int page) {
		List<MemberDTO> memberList = new ArrayList<>();
		for(MemberDTO dto: memberDao.selectNotSafety(page)){
			if(memberDao.isSafety(dto.getUserId())){
				memberList.add(dto);
			}
		}
		return memberList;
	}
	
	@Override
	public int countNotSafety(){
		return memberDao.countNotSafety();
	}

	@Override
	public List<MemberDTO> selectRanked() {
		List<GPADTO> gpaList = memberDao.averageGPA(null);
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		for(GPADTO dto : gpaList){
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
	public int countNotification(String id){
		return memberDao.countNotification(id);
	}
}
