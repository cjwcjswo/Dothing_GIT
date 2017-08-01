package dothing.web.dao;

import java.util.List;

import dothing.web.dto.GPADTO;
import dothing.web.dto.MemberDTO;
import dothing.web.dto.MemberHashDTO;
import dothing.web.dto.NotificationDTO;

public interface MemberDAO {
	/**
	 * �ش��ϴ� ���� ���� Ż�� ��Ű��
	 * @param String id: �ش��ϴ� ���� ���̵�
	 * @return int: ���� ����
	 */
	int deleteUser(String id);
	
	/**
	 * �������� �ش��ϴ� ���� ����Ʈ �ҷ�����
	 * @param int page: �ش��ϴ� ������
	 * @param String id: null�� �ƴҰ�� �ش��ϴ� ���� ���̵�� �˻�
	 * @return List<MemberDTO> ����dto ����Ʈ
	 */
	List<MemberDTO> selectAll(int page, String id);
	
	/**
	 * ��ü �������� ���� ��������
	 * @param String id: null�� �ƴ� ��� ���� ���̵�� �˻����� ���� ����
	 * @return int: �������� ����
	 */
	int countAll(String id);


	/**
	 * ���� ���� ����ϱ�(ȸ������)
	 * @param MemberDTO memberDTO: ����ϰ��� �ϴ� ������ dto
	 * @return int: ���� ����
	 */
	int insertMember(MemberDTO memberDTO);
	
	/**
	 * id�� �ش��ϴ� ���� ���� �˻�
	 * @param String id: �ش��ϴ� ���� id
	 * @return MemberDTO: ���� ���� dto
	 */
	MemberDTO selectMemberById(String id);

	/**
	 * ���� id �ߺ�üũ�ϱ�
	 * @param String userId: �ߺ�üũ�ϰ��� �ϴ� ���� id
	 * @return MemberDTO: ���� ���� dto
	 */
	MemberDTO selectSearch(String userId);
	
	/**
	 * ȸ�� ���Խ� �ʱ� ���� ����Ʈ �����ϱ�
	 * @param String userId: �ش��ϴ� ���� id
	 * @return int: ���� ����
	 */
	int createPoint(String userId);
	
	/**
	 * �ش��ϴ� ������ ���� �����ϱ�
	 * @param MemberDTO member: �����ϰ��� �ϴ� ���� ���� dto
	 * @return int: ���� ����
	 */
	int updateMember(MemberDTO member);
	
	/**
	 * �ش��ϴ� ������ ����Ʈ �����ϱ�
	 * @param Integer point: �����ϰ��� �ϴ� point
	 * @param String id: �ش��ϴ� ������ id
	 * @return int: ���� ����
	 */
	int updatePoint(Integer point, String id);
	
	/**
	 * �ش��ϴ� �������� �ؽ��±� �߰�
	 * @param MemberHashDTO dto: ������ �ؽ��±׿� ���� dto
	 * @return int: ���� ����
	 */
	int insertHashtag(MemberHashDTO dto);
	
	/**
	 * �ش��ϴ� ������ �ؽ��±� �˻�
	 * @param String id: �ش��ϴ� ������ ���̵�
	 * @return List<MemberHashDTO> �ؽ��±�DTO ����Ʈ
	 */
	List<MemberHashDTO> selectHashtag(String id);
	
	/**
	 * �ش��ϴ� ������ ���ѵ� ��������
	 * @param String id: �ش��ϴ� ������ id
	 * @return List<String>: ���ѵ��� List
	 */
	List<String> selectAuth(String id);

	/**
	 * �����ɺθ��� ����ϱ�
	 * @param MemberDTO dto: ����ϰ��� �ϴ� �������� dto
	 * @return int: ���� ����
	 */
	int updateSafety(MemberDTO dto);
	
	
	/**
	 * �ش��ϴ� �������� ���� ���� �ֱ�
	 * @param String id: �ش��ϴ� ���� id
	 * @return int: ���� ����
	 */
	int insertSafety(String id);
	
	
	/**
	 * ������ �������� ���� ��� �����ɺθ��� ��� ��ҽ�Ű��
	 * @param String id: �ش��ϴ� ���� id
	 * @return int: ���� ����
	 */
	int cancleSafety(String id);
	
	/**
	 * �ֹε�ϻ����� �÷����� �����ɺθ����� �ƴ� ���� ��ȸ�ϱ�
	 * @param int page: �ش��ϴ� page
	 * @return List<MemberDTO> �������� ����Ʈ
	 */
	List<MemberDTO> selectNotSafety(int page);
	
	/**
	 * �ֹε�ϻ����� �÷����� �����ɺθ����� �ƴ� �������� ���� ��������
	 * @return int: �����ɺθ����� �ƴ� �������� ����
	 */
	int countNotSafety();

	/**
	 * �ش��ϴ� ������ ��� ���� ������ ��������
	 * @param String id: �ش��ϴ� ���� id
	 * @return List<GPADTO> �������� ����Ʈ
	 */
	List<GPADTO> averageGPA(String id);
	
	/**
	 * �ش��ϴ� ������ �����ɺθ����ΰ� ��ȸ�ϱ�
	 * @param String id: �ش��ϴ� ���� id
	 * @return boolean: �����ɺθ����� ��� true, �ƴҰ�� false
	 */
	boolean isSafety(String id);
	
	/**
	 * �ش��ϴ� ���������� �˸�����Ʈ ��������
	 * @param String id: �ش��ϴ� ���� id
	 * @param int page: �ش��ϴ� ������
	 * @return List<NotificationDTO>: �˸� DTO ����Ʈ
	 */
	List<NotificationDTO> selectNotificationById(String id, int page);
	
	/**
	 * �ش��ϴ� �������� �˸� ������
	 * @param String id: �ش��ϴ� ���� ���̵�
	 * @param String content: �˸� ����
	 * @return int: ���� ����
	 */
	int insertNotification(String id, String content);
	
	/**
	 * �ش��ϴ� ������ �� ���� �˸� ���� ��������
	 * @param String id: �ش��ϴ� ���� id
	 * @return int: ������ �˸��� ����
	 */
	int notReadNoti(String id);
	
	/**
	 * �ش��ϴ� ������ ��� �˸� �б���·� ������Ʈ
	 * @param String id: �ش��ϴ� ���� id
	 * @return int: ���� ����
	 */
	int allRead(String id);
	
	/**
	 * �ش��ϴ� ������ �˸� ���� ��������
	 * @param String id: �ش��ϴ� ���� id
	 * @return int: �˸� ����
	 */
	int countNotification(String id);
	
	/**
	 * �ش��ϴ� ���� �̸��� ���� �Ϸ� ó���ϱ�
	 * @param String id: �ش��ϴ� ���� id
	 * @return int: ���� ����
	 */
	int finishEmail(String id);
}
