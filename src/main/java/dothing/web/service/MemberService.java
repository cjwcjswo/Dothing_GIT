package dothing.web.service;

import dothing.web.dto.MemberDTO;
import dothing.web.dto.MemberHashDTO;

public interface MemberService {

	
	/**
	 * ȸ������
	 * */
	int joinMember(MemberDTO member);
	
	/**
	 * ID�ߺ�üũ
	 * */
	String selectSearch(String userId);
	
	/**
	 * ��������
	 */
	int updateMember(MemberDTO member, MemberDTO original);
	
	
	/**
	 * ��� ��������
	 */
	MemberDTO selectMemberById(String id);
	
	/**
	 * ����Ʈ ����
	 */
	int updatePoint(Integer point, String id);
	
	/**
	 * �ؽ��±� ����
	 */
	int insertHashtag(int errandsNum, String id, String evalTag);
	
}
