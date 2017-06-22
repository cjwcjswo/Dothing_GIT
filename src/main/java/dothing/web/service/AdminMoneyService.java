package dothing.web.service;

import java.util.List;

import dothing.web.dto.ErrandsDTO;
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
	
	/**
	 * ������ �Ա� ����Ʈ ��û�� �ø���
	 */
	int pointChargeBandBook(String userId, int select);
	
	/**
	 * ī�� �Ա� ����Ʈ �� �������ֱ�
	 */
	int pointChargeCard(String userId, int select);

	int getRequestPoint(String id);
	
	/**
	 * ����Ʈ ��볻��
	 */
	List<ErrandsDTO> pointList(String userId);
	
	/**
	 * �ɺθ� �������� ����Ʈ ���� ����
	 */
	List<ErrandsDTO> searchPointSuccess(String userId);
}
