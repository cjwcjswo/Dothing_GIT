package dothing.web.service;

import java.util.List;
import java.util.Map;

import dothing.web.dto.ChatPosDTO;
import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.MemberDTO;

public interface AndroidService {
	
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
	 * �ȵ���̵�� �ش��ϴ� �������� ���� �̸��� ������
	 * @param email �ش��ϴ� ������ email
	 * @return ������ȣ ����
	 */
	String androidSendEmail(String email);
	
	/**
	 * �ش��ϴ� �ɺθ��� �ֹ��� ������ ��ȸ�ϱ�
	 * @param errandNum �ش��ϴ� �ɺθ� ��ȣ
	 * @return �ɺθ� �ֹ��ڿ����� ������ ���� Map
	 */
	Map<String, Object> selectRequesterDetail(int errandNum);
	
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
