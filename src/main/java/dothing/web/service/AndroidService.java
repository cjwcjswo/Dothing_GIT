package dothing.web.service;

import java.util.List;
import java.util.Map;

import dothing.web.dto.ChatPosDTO;
import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.MemberDTO;

public interface AndroidService {
	String androidLogin(String email,String password);
	
	int androidSignIn(MemberDTO memberDTO);
	
	/**
	 * Ǫ�ø޼��� ��ū �߰�
	 */
	int insertToken(String memberId, String token);
	
	/**
	 * ����/�浵/�Ÿ������� ��ū��ȸ
	 */
	List<String> selectTokenByDistance(String latitude, String longitude, Integer distance);
	
	/**
	 * �ɺθ���ȣ�� ���� �ֹ����� ��������ȸ
	 * */
	Map<String, Object> selectRequesterDetail(int errandNum);
	
	/**
	 * ä�� ��� ��������
	 */
	List<ErrandsDTO> selectChatList(String memberId);
	
	/**
	 * ��ġ �ʱ�ȭ(��Ī������)
	 */
	int initLocation(int errandsNum, String memberId);
	
	/**
	 * ��ġ ������Ʈ
	 */
	int updateLocation(String memberId, String latitude, String longitude);
	
	/**
	 * ��ġ �ҷ�����
	 */
	ChatPosDTO selectLocation(int errandsNum, String memberId);
}
