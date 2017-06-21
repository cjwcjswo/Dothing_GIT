package dothing.web.dao;

import java.util.List;

import dothing.web.dto.PointDTO;

public interface AdminMoneyDAO {
	
	/**
	 * ������ �Ա� ��û ����Ʈ(��ȯ �ȵ� ����Ʈ��)
	 */
	List<PointDTO> selectAll(int page);
	
	/**
	 * ����Ʈ�� ��ȯ
	 */
	int changePoint(String userId);
	
	
	/**
	 * �Աݾȵ� ��û ��û ����Ʈ �����ϰ� ����Ʈ���� �����ϱ�
	 */
	int pointCancel(String userId);
	
	/**
	 * ����¡
	 */
	int countPointList();

	/**
	 * ������Ʈ����Ʈ ��������
	 */
	int getRequestPoint(String id);

	
	/**
	 * ������ �Ա� ����Ʈ ��û�� �ø���
	 */
	int pointChargeBandBook(String userId, int select);
	
	/**
	 * ī�� �Ա� ����Ʈ �� �������ֱ�
	 */
	int pointChargeCard(String userId, int select);


}
