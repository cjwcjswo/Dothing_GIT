package dothing.web.dao;

import java.util.List;

import dothing.web.dto.MemberDTO;

public interface AndroidDAO {
	
	/**
	 * �ȵ���̵� �α���
	 */
	String androidLogin(String email);
	
	/**
	 * ȸ������
	 */
	int androidSignIn(MemberDTO memberDTO);
	
	/**
	 * Ǫ�ø޼��� ��ū ��ī
	 */
	int insertToken(String memberId, String token);
	
	/**
	 * ��ϵ� �ɺθ��� ����/�浵/�Ÿ������� ��ū ��ȸ
	 */
	List<String> selectTokenByDistance(String latitude, String longitude, Integer distance);
	
	/**
	 * �ɺθ���ȣ�� �ֹ����� ID��ȸ
	 * */
	String selectRequesterId(int errandNum);
	
	/**
	 * ȸ��ID�� �ֹ�Ƚ����ȸ 
	 * */
	int selectRequestCount(String memberId);
	
	/**
	 * ȸ��ID�� �ֹ�������ȸ
	 * */
	int selectRequestGPA(String memberId);
	
	/**
	 * ȸ��ID�� �ش� ȸ������ �޸� �ؽ��±׵� ��ȸ
	 * */
	List<Object> selectMemberHashtag(String memberId);

}
