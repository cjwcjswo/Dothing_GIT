package dothing.web.dao;

import java.util.List;

import dothing.web.dto.GPADTO;
import dothing.web.dto.MemberDTO;
import dothing.web.dto.MemberHashDTO;
import dothing.web.dto.NotificationDTO;

public interface MemberDAO {
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
	 * ���� ���� ����ϱ�(ȸ������)
	 * @param memberDTO ����ϰ��� �ϴ� ������ dto
	 * @return ���� ����
	 */
	int insertMember(MemberDTO memberDTO);
	
	/**
	 * id�� �ش��ϴ� ���� ���� �˻�
	 * @param id �ش��ϴ� ���� id
	 * @return ���� ���� dto
	 */
	MemberDTO selectMemberById(String id);

	/**
	 * ���� id �ߺ�üũ�ϱ�
	 * @param userId �ߺ�üũ�ϰ��� �ϴ� ���� id
	 * @return ���� ���� dto
	 */
	MemberDTO selectSearch(String userId);
	
	/**
	 * ȸ�� ���Խ� �ʱ� ���� ����Ʈ �����ϱ�
	 * @param userId �ش��ϴ� ���� id
	 * @return ���� ����
	 */
	int createPoint(String userId);
	
	/**
	 * �ش��ϴ� ������ ���� �����ϱ�
	 * @param member �����ϰ��� �ϴ� ���� ���� dto
	 * @return ���� ����
	 */
	int updateMember(MemberDTO member);
	
	/**
	 * �ش��ϴ� ������ ����Ʈ �����ϱ�
	 * @param point �����ϰ��� �ϴ� point
	 * @param id �ش��ϴ� ������ id
	 * @return ���� ����
	 */
	int updatePoint(Integer point, String id);
	
	/**
	 * �ش��ϴ� �������� �ؽ��±� �߰�
	 * @param dto ������ �ؽ��±׿� ���� dto
	 * @return ���� ����
	 */
	int insertHashtag(MemberHashDTO dto);
	
	/**
	 * �ش��ϴ� ������ �ؽ��±� �˻�
	 * @param id �ش��ϴ� ������ ���̵�
	 * @return �ؽ��±�DTO ����Ʈ
	 */
	List<MemberHashDTO> selectHashtag(String id);
	
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
	 * @return �����ɺθ����� �ƴ� �������� ����
	 */
	int countNotSafety();

	/**
	 * �ش��ϴ� ������ ��� ���� ������ ��������
	 * @param id �ش��ϴ� ���� id
	 * @return �������� ����Ʈ
	 */
	List<GPADTO> averageGPA(String id);
	
	/**
	 * �ش��ϴ� ������ �����ɺθ����ΰ� ��ȸ�ϱ�
	 * @param id �ش��ϴ� ���� id
	 * @return �����ɺθ����� ��� true, �ƴҰ�� false
	 */
	boolean isSafety(String id);
	
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
	 * �ش��ϴ� ���� �̸��� ���� �Ϸ� ó���ϱ�
	 * @param id �ش��ϴ� ���� id
	 * @return ���� ����
	 */
	int finishEmail(String id);
	
	/**
	  * App ȸ�� ���� ����
	  * */
	 int myInfoUpdate(MemberDTO memberDTO);
}
