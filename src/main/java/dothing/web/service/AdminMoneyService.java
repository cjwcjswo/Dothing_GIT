package dothing.web.service;

import java.util.List;

import dothing.web.dto.PointDTO;

public interface AdminMoneyService {
	
	/**
	 * ������ �Ա� ��û ����Ʈ(��ȯ �ȵ� ����Ʈ��)
	 */
	List<PointDTO> selectAll(int page);
	
	/**
	 * ����Ʈ�� ��ȯ
	 */
	int changePoint(String userId) throws Exception;
	
	/**
	 * �Աݾȵ� ��û ����Ʈ���� �����ϱ�
	 */
	int pointCancel(String userId) throws Exception;
	
	/**
	 * ����¡
	 */
	int countPointList();

}
