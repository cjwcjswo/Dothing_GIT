package dothing.web.dao;

import java.util.List;

import dothing.web.dto.MemberDTO;
import dothing.web.dto.MemberHashDTO;

public interface MemberDAO {
	int deleteUser(String id);
	/**
	 * ��� ���� �ҷ�����
	 */
	List<MemberDTO> selectAll(int page, String id);
	int countAll(String id);
	/**
	 * ȸ������
	 * */
	int insertMember(MemberDTO memberDTO);
	
	/**
	 * ID�� �ش��ϴ� ȸ������ �˻�
	 * */
	MemberDTO selectMemberById(String id);
	
	/**
	 * ID �ߺ�üũ
	 * */
	MemberDTO selectSearch(String userId);
	
	/**
	 * �ʱ� ����Ʈ ����
	 */
	int createPoint(String userId);
	
	/**
	 * ���� ����
	 */
	int updateMember(MemberDTO member);
	
	/**
	 * ����Ʈ ����
	 */
	int updatePoint(Integer point, String id);
	
	/**
	 * ���� �ؽ��±� �߰�
	 */
	int insertHashtag(MemberHashDTO dto);
	
	/**
	 * ���� �ؽ��±� �˻�
	 */
	List<MemberHashDTO> selectHashtag(String id);
}
