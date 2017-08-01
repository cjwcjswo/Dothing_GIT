package dothing.web.service;

import java.util.List;
import java.util.Map;

import dothing.web.dto.ChatPosDTO;
import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.MemberDTO;

public interface AndroidService {
	
	/**
	 * �ȵ���̵忡���� ȸ������ó��
	 * @param MemberDTO: ȸ���� ���� DTO
	 * @return int: ���� ����
	 */
	int androidSignIn(MemberDTO memberDTO);
	
	/**
	 * �ش��ϴ� �������� FCM ��ū �� ����
	 * @param memberId: �ش��ϴ� ������ id
	 * @param token: ��ū ��
	 * @return int: ���� ����
	 */
	int insertToken(String memberId, String token);
	
	/**
	 * ���� / �浵 / �Ÿ��� �ݰ濡 �ش��ϴ� �������� ��ū���� ��������
	 * @param String latitude: ����
	 * @param String longitude: �浵
	 * @param String distance: �Ÿ�
	 * @return List<String> ��ū���� ����Ʈ
	 */
	List<String> selectTokenByDistance(String latitude, String longitude, Integer distance);
	
	/**
	 * �ȵ���̵�� �ش��ϴ� �������� ���� �̸��� ������
	 * @param String email: �ش��ϴ� ������ email
	 * @return String: ������ȣ ����
	 */
	String androidSendEmail(String email);
	
	/**
	 * �ش��ϴ� �ɺθ��� �ֹ��� ������ ��ȸ�ϱ�
	 * @param int errandNum: �ش��ϴ� �ɺθ� ��ȣ
	 * @return Map<String, Object> �ɺθ� �ֹ��ڿ����� ������ ���� Map
	 */
	Map<String, Object> selectRequesterDetail(int errandNum);
	
	/**
	 * �ش��ϴ� ������ ä�� ����Ʈ ��������
	 * @param String memberId: �ش��ϴ� ������ id
	 * @return List<ErrandsDTO>: �ؽ��±��� ����Ʈ
	 */
	List<ErrandsDTO> selectChatList(String memberId);
	
	/**
	 * �ش��ϴ� ������ �ɺθ��� ��ġ ���� �ʱ�ȭ
	 * @param String memberId: �ش��ϴ� ������ id
	 * @param int errandsNum: �ɺθ� ��ȣ
	 * @return int: ���� ����
	 */
	int initLocation(int errandsNum, String memberId);
	
	/**
	 * �ش��ϴ� ������ ���� ��ġ���� ������Ʈ
	 * @param String memberId: �ش��ϴ� ������ id
	 * @param String latitude: ����
	 * @param String longitude: �浵
	 * @return int: ���� ����
	 */
	int updateLocation(String memberId, String latitude, String longitude);
	
	
	/**
	 * �ش��ϴ� ������ �ɺθ��� ��ġ���� �ҷ�����
	 * @param int errandsNum: �ش��ϴ� �ɺθ� ��ȣ
	 * @param String memberId: �ش��ϴ� ������ id
	 * @return ChatPosDTO: ä�� ��ġ���� DTO
	 */
	ChatPosDTO selectLocation(int errandsNum, String memberId);
	
	
	/**
	 * �ش��ϴ� ������ FCM ��ū �� ��������
	 * @param String memberId: �ش��ϴ� ������ id
	 * @return String: ��ū ��
	 */
	String selectTokenById(String memberId);
}
