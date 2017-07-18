package dothing.web.service;

import java.util.List;
import java.util.Map;

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
}
