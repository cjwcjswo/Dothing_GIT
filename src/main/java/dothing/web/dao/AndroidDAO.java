package dothing.web.dao;

import java.util.List;

import dothing.web.dto.ChatPosDTO;
import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.MemberDTO;

public interface AndroidDAO {
	
	
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
	 * �ش��ϴ� �ɺθ��� �ֹ��� id ��������
	 * @param int errandNum: �ش��ϴ� �ɺθ� ��ȣ
	 * @return String: �ֹ��� id
	 */
	String selectRequesterId(int errandNum);
	
	/**
	 * �ش��ϴ� ������ �ֹ� Ƚ�� ��������
	 * @param String memberId: �ش��ϴ� ������ id
	 * @return int: �ֹ� Ƚ��
	 */
	int selectRequestCount(String memberId);
	
	/**
	 * �ش��ϴ� ������ �ֹ� ���� ��������
	 * @param String memberId: �ش��ϴ� ������ id
	 * @return int: �ֹ� ����
	 */
	int selectRequestGPA(String memberId);
	
	/**
	 * �ش��ϴ� ������ �ؽ��±� ��������
	 * @param String memberId: �ش��ϴ� ������ id
	 * @return List<Object>: �ؽ��±��� ����Ʈ
	 */
	List<Object> selectMemberHashtag(String memberId);
	
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
