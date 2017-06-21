package dothing.web.dao;

import java.util.List;

import dothing.web.dto.GPADTO;
import dothing.web.dto.MemberDTO;
import dothing.web.dto.MemberHashDTO;
import dothing.web.dto.NotificationDTO;

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
	
	/**
	 * ���� ��������
	 */
	List<String> selectAuth(String id);
	
	/**
	 * �����ɺθ��� �̹��� ���
	 */
	int updateSafety(MemberDTO dto);
	/**
	 * �������ѵ��
	 */
	int insertSafety(String id);
	/**
	 * ���������� �÷ȴµ� ������ ������
	 */
	List<MemberDTO> selectNotSafety(int page);
	int countNotSafety();
	/**
	 * ��� ����
	 */
	List<GPADTO> averageGPA(String id);
	
	boolean isSafety(String id);
	
	/**
	 * �ش��ϴ� ���������� �˸� ��������
	 */
	List<NotificationDTO> selectNotificationById(String id, int page);
	
	/**
	 * �˸� ������
	 */
	int insertNotification(String id, String content);
	
	/**
	 * ������ �˶� �ּ��ϰ�?
	 */
	int notReadNoti(String id);
	
	/**
	 * �ش��ϴ� ���̵� �˶� �б�
	 * 
	 */
	int allRead(String id);
	int countNotification(String id);
}
