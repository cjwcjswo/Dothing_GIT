package dothing.web.dao;

import java.util.List;

import dothing.web.dto.ChatPosDTO;
import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.MemberDTO;

public interface AndroidDAO {
	
	
	/**
	 * �ȵ���̵忡���� ȸ������ó��
	 * @param memberDTO ȸ���� ���� DTO
	 * @return ���� ����
	 */
	int androidSignIn(MemberDTO memberDTO);
	
	
	/**
	 * �ش��ϴ� �������� FCM ��ū �� ����
	 * @param memberId �ش��ϴ� ������ id
	 * @param token ��ū ��
	 * @return ���� ����
	 */
	int insertToken(String memberId, String token);
	
	/**
	 * ���� / �浵 / �Ÿ��� �ݰ濡 �ش��ϴ� �������� ��ū���� ��������
	 * @param latitude ����
	 * @param longitude �浵
	 * @param distance �Ÿ�
	 * @return ��ū���� ����Ʈ
	 */
	List<String> selectTokenByDistance(String latitude, String longitude, Integer distance);
	
	/**
	 * �ش��ϴ� �ɺθ��� �ֹ��� id ��������
	 * @param errandNum �ش��ϴ� �ɺθ� ��ȣ
	 * @return �ֹ��� id
	 */
	String selectRequesterId(int errandNum);
	
	/**
	 * �ش��ϴ� ������ �ֹ� Ƚ�� ��������
	 * @param memberId �ش��ϴ� ������ id
	 * @return �ֹ� Ƚ��
	 */
	int selectRequestCount(String memberId);
	
	/**
	 * �ش��ϴ� ������ �ֹ� ���� ��������
	 * @param memberId �ش��ϴ� ������ id
	 * @return �ֹ� ����
	 */
	int selectRequestGPA(String memberId);
	
	/**
	 * �ش��ϴ� ������ �ؽ��±� ��������
	 * @param memberId �ش��ϴ� ������ id
	 * @return �ؽ��±��� ����Ʈ
	 */
	List<Object> selectMemberHashtag(String memberId);
	
	/**
	 * �ش��ϴ� ������ ä�� ����Ʈ ��������
	 * @param memberId �ش��ϴ� ������ id
	 * @return �ؽ��±��� ����Ʈ
	 */
	List<ErrandsDTO> selectChatList(String memberId);
	
	/**
	 * �ش��ϴ� ������ �ɺθ��� ��ġ ���� �ʱ�ȭ
	 * @param memberId �ش��ϴ� ������ id
	 * @param errandsNum �ɺθ� ��ȣ
	 * @return ���� ����
	 */
	int initLocation(int errandsNum, String memberId);
	
	/**
	 * �ش��ϴ� ������ ���� ��ġ���� ������Ʈ
	 * @param memberId �ش��ϴ� ������ id
	 * @param latitude ����
	 * @param longitude �浵
	 * @return ���� ����
	 */
	int updateLocation(String memberId, String latitude, String longitude);
	
	/**
	 * �ش��ϴ� ������ �ɺθ��� ��ġ���� �ҷ�����
	 * @param errandsNum �ش��ϴ� �ɺθ� ��ȣ
	 * @param memberId �ش��ϴ� ������ id
	 * @return ä�� ��ġ���� DTO
	 */
	ChatPosDTO selectLocation(int errandsNum, String memberId);
	
	/**
	 * �ش��ϴ� ������ FCM ��ū �� ��������
	 * @param memberId �ش��ϴ� ������ id
	 * @return ��ū ��
	 */
	String selectTokenById(String memberId);
}
