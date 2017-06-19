package dothing.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dothing.web.dao.AuthorityDAO;
import dothing.web.dao.MemberDAO;
import dothing.web.dto.AuthorityDTO;
import dothing.web.dto.MemberDTO;
import dothing.web.dto.MemberHashDTO;
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
		memberDao.createPoint(member.getUserId());
		
		authorityDAO.insertAuthority(new AuthorityDTO(member.getUserId(), Constants.ROLE_MEMBER));
		
		return 1;
	}

	@Override
	public String selectSearch(String userId) {
		MemberDTO dto = memberDao.selectSearch(userId);
		if(dto==null)return "��밡���մϴ�.";
		else return "������Դϴ�";
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
	 * ����Ʈ ����
	 */
	public int updatePoint(Integer point, String id){
		return memberDao.updatePoint(point, id);
	}

	/**
	 * �ؽ��±� ����
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
		return memberDao.selectNotSafety(page);
	}
	
	@Override
	public int countNotSafety(){
		return memberDao.countNotSafety();
	}
}
