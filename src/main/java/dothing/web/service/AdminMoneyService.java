package dothing.web.service;

import java.util.List;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.PointDTO;

public interface AdminMoneyService {
	
	/**
	 * ������ �Ա� ��û ����Ʈ ��������
	 * @param int page: �ش��ϴ� ������
	 * @return List<PointDTO>: �ش��ϴ� ����Ʈ DTO�� ����Ʈ
	 */
	List<PointDTO> selectAll(int page);
	
	/**
	 * ��û ����Ʈ�� ������ ����Ʈ�� ��ȯ���ִ� �޼ҵ�
	 * @param String userId: �ش��ϴ� ���� ���̵�
	 * @return int: ���� ����
	 */
	int changePoint(String userId) throws Exception;
	
	/**
	 * �Աݾȵ� ��û ��û ����Ʈ �����ϰ� ����Ʈ���� �����ϱ�
	 * @param String userId: �ش��ϴ� ���� ���̵�
	 * @return int: ���� ����
	 */
	int pointCancel(String userId) throws Exception;
	
	/**
	 * ��û����Ʈ�� ��ü count�� ��������
	 * @return int: ��ü count ��
	 */
	int countPointList();
	
	/**
	 * ������ �Ա� �� �ش��ϴ� ������ requestPoint�� �÷��ֱ�
	 * @param String userId: �ش��ϴ� ���� id
	 * @param int select: �ø��� ���� ��
	 * @return int: ���� ����
	 */
	int pointChargeBandBook(String userId, int select);
	
	/**
	 * ī�� �Ա� �� �ٷ� �ش��ϴ� ������ currentPoint�� ���������ֱ�
	 * @param String userId: �ش��ϴ� ���� id
	 * @param int select: �ø��� ���� ��
	 * @return int: ���� ����
	 */
	int pointChargeCard(String userId, int select);

	/**
	 * �ش��ϴ� ������ requestPoint�� ��������
	 * @return int: requestPoint ��
	 */
	int getRequestPoint(String id);
	
	/**
	 * �ش��ϴ� ������ ����Ʈ ���� ��������
	 * @param String userId: �ش��ϴ� ���� id
	 * @return List<ErrandsDTO>: �ش��ϴ� ����Ʈ ������ �ɺθ� ����Ʈ
	 */
	List<ErrandsDTO> pointList(String userId);
	
	/**
	 * �ɺθ� �������� ���� ����Ʈ ���� ��������
	 * @param String userId: �ش��ϴ� ���� id
	 * @return List<ErrandsDTO>: �ش��ϴ� ����Ʈ ������ �ɺθ� ����Ʈ
	 */
	List<ErrandsDTO> searchPointSuccess(String userId);
}
