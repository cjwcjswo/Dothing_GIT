package dothing.web.service;

import java.util.List;

import dothing.web.dto.GPADTO;
import dothing.web.dto.MemberDTO;
import dothing.web.dto.MemberHashDTO;
import dothing.web.dto.NotificationDTO;

public interface MemberService {
	/**
	 * ȸ�� ����
	 */
	int deleteUser(String id);
	/**
	 * ȸ�� ��� �ҷ�����
	 */
	List<MemberDTO> selectAll(int page, String id);
	int countAll(String id);
	/**
	 * ȸ������
	 * isAPI => true: API false: �Ϲ�
	 * */
	int joinMember(MemberDTO member, boolean isAPI);
	
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
	int insertHashtag(int errandsNum, String id, String evalTag, boolean isAndroid);
	/**
	 * ���� ��������
	 */
	List<String> selectAuth(String id);
	
	/**
	 * �����ɺθ��� �̹��� ���
	 */
	int updateSafety(MemberDTO dto);
	/**
	 * �������� �߰�
	 */
	int insertSafety(String id);
	/**
	 * �����������
	 */
	int cancleSafety(String id);
	/**
	 * ���ȢK�ǵ�
	 */
	List<MemberDTO> selectNotSafety(int page);
	int countNotSafety();
	/**
	 * ��ŷ�ǵ�
	 */
	List<MemberDTO> selectRanked();
	
	/**
	 * �ش��ϴ� ���������� �˸� ��������
	 */
	List<NotificationDTO> selectNotificationById(String id, int page);
	
	/**
	 * �˸�������
	 */
	int insertNotification(String id, String content);
	
	/**
	 * ������ �˶� �ִ�?
	 */
	int notReadNoti(String id);
	
	/**
	 * �ش��ϴ� ���̵� �˶� �б�
	 * 
	 */
	int allRead(String id);
	int countNotification(String id);
	
	 List<GPADTO> averageGPA(String id);
	 List<MemberHashDTO> selectHashtag(String id);
	 
	 /**
	  * �̸��� ������
	  */
	 void sendEmail(String email, Integer authNum);
	 /**
	  * �̸��� ���� �Ϸ�
	  */
	 int finishEmail(String id);
}
