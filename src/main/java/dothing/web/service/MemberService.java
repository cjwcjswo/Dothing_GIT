package dothing.web.service;

import java.util.List;

import dothing.web.dto.GPADTO;
import dothing.web.dto.MemberDTO;
import dothing.web.dto.MemberHashDTO;
import dothing.web.dto.NotificationDTO;

public interface MemberService {
	/**
	 * �ش��ϴ� ���� ���� Ż�� ��Ű��
	 * @param id �ش��ϴ� ���� ���̵�
	 * @return ���� ����
	 */
	int deleteUser(String id);
	
	/**
	 * �������� �ش��ϴ� ���� ����Ʈ �ҷ�����
	 * @param page �ش��ϴ� ������
	 * @param id null�� �ƴҰ�� �ش��ϴ� ���� ���̵�� �˻�
	 * @return ����dto ����Ʈ
	 */
	List<MemberDTO> selectAll(int page, String id);
	
	/**
	 * ��ü �������� ���� ��������
	 * @param id null�� �ƴ� ��� ���� ���̵�� �˻����� ���� ����
	 * @return �������� ����
	 */
	int countAll(String id);
	
	/**
	 * ȸ�� �����ϱ�
	 * @param member ȸ�������ϰ��� �ϴ� ��������dto
	 * @param isAPI true = Facebook API ����ؼ� ����, false = �Ϲ� ����
	 * @return ���� ����
	 */
	int joinMember(MemberDTO member, boolean isAPI);
	
	/**
	 * ���� id �ߺ�üũ�ϱ�
	 * @param userId �ߺ�üũ�ϰ��� �ϴ� ���� id
	 * @return ��� �������� �������� ������ String ��
	 */
	String selectSearch(String userId);
	
	/**
	 * �ش��ϴ� ������ ���� �����ϱ�
	 * @param member �����ϰ��� �ϴ� ���� ���� dto
	 * @param original �����ϱ� �� ���� ���� ���� dto
	 * @return ���� ����
	 */
	int updateMember(MemberDTO member, MemberDTO original);
	
	
	/**
	 * id�� �ش��ϴ� ���� ���� �˻�
	 * @param id �ش��ϴ� ���� id
	 * @return ���� ���� dto
	 */
	MemberDTO selectMemberById(String id);
	
	/**
	 * �ش��ϴ� ������ ����Ʈ �����ϱ�
	 * @param point �����ϰ��� �ϴ� point
	 * @param id �ش��ϴ� ������ id
	 * @return ���� ����
	 */
	int updatePoint(Integer point, String id);
	
	/**
	 * �ش��ϴ� �������� �ؽ��±� �߰�
	 * @param errandsNum �ش��ϴ� �ɺθ� ��ȣ
	 * @param id �ش��ϴ� ���� ���̵�
	 * @param evalTag �ؽ��±�
	 * @param isAndroid �ȵ���̵� ����
	 * @return ���� ����
	 */
	int insertHashtag(int errandsNum, String id, String evalTag, boolean isAndroid);
	
	/**
	 * �ش��ϴ� ������ ���ѵ� ��������
	 * @param id �ش��ϴ� ������ id
	 * @return ���ѵ��� List
	 */
	List<String> selectAuth(String id);
	
	/**
	 * �����ɺθ��� ����ϱ�
	 * @param dto ����ϰ��� �ϴ� �������� dto
	 * @return ���� ����
	 */
	int updateSafety(MemberDTO dto);
	
	/**
	 * �ش��ϴ� �������� ���� ���� �ֱ�
	 * @param id �ش��ϴ� ���� id
	 * @return ���� ����
	 */
	int insertSafety(String id);
	
	
	/**
	 * ������ �������� ���� ��� �����ɺθ��� ��� ��ҽ�Ű��
	 * @param id �ش��ϴ� ���� id
	 * @return ���� ����
	 */
	int cancleSafety(String id);
	
	/**
	 * �ֹε�ϻ����� �÷����� �����ɺθ����� �ƴ� ���� ��ȸ�ϱ�
	 * @param page �ش��ϴ� page
	 * @return �������� ����Ʈ
	 */
	List<MemberDTO> selectNotSafety(int page);
	
	/**
	 * �ֹε�ϻ����� �÷����� �����ɺθ����� �ƴ� �������� ���� ��������
	 * @return  �����ɺθ����� �ƴ� �������� ����
	 */
	int countNotSafety();
	
	/**
	 * ������ ���� ������ ������� �����Ͽ� ��ȸ
	 * @return �������dto
	 */
	List<MemberDTO> selectRanked();
	
	/**
	 * �ش��ϴ� ���������� �˸�����Ʈ ��������
	 * @param id �ش��ϴ� ���� id
	 * @param page �ش��ϴ� ������
	 * @return �˸� DTO ����Ʈ
	 */
	List<NotificationDTO> selectNotificationById(String id, int page);
	
	/**
	 * �ش��ϴ� �������� �˸� ������
	 * @param id �ش��ϴ� ���� ���̵�
	 * @param content �˸� ����
	 * @return ���� ����
	 */
	int insertNotification(String id, String content);
	
	/**
	 * �ش��ϴ� ������ �� ���� �˸� ���� ��������
	 * @param id �ش��ϴ� ���� id
	 * @return ������ �˸��� ����
	 */
	int notReadNoti(String id);
	
	/**
	 * �ش��ϴ� ������ ��� �˸� �б���·� ������Ʈ
	 * @param id �ش��ϴ� ���� id
	 * @return ���� ����
	 */
	int allRead(String id);
	
	/**
	 * �ش��ϴ� ������ �˸� ���� ��������
	 * @param id �ش��ϴ� ���� id
	 * @return �˸� ����
	 */
	int countNotification(String id);
	
	/**
	 * �ش��ϴ� ������ ��� ���� ������ ��������
	 * @param id �ش��ϴ� ���� id
	 * @return �������� ����Ʈ
	 */
	List<GPADTO> averageGPA(String id);
	 
	 /**
		 * �ش��ϴ� ������ �ؽ��±� �˻�
		 * @param id �ش��ϴ� ������ ���̵�
		 * @return �ؽ��±�DTO ����Ʈ
		 */
	 List<MemberHashDTO> selectHashtag(String id);
	 
	 /**
	  * �ش��ϴ� �������� ���� ���� ������
	  * @param email �ش��ϴ� ���� �̸���
	  * @param authNum ���� ��ȣ
	  */
	 void sendEmail(String email, Integer authNum);


	 /**
		 * �ش��ϴ� ���� �̸��� ���� �Ϸ� ó���ϱ�
		 * @param id �ش��ϴ� ���� id
		 * @return ���� ����
		 * */
	 int finishEmail(String id);
}
