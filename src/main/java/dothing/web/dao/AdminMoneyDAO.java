package dothing.web.dao;

import java.util.List;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.PointDTO;

public interface AdminMoneyDAO {
	
	/**
	 * ������ �Ա� ��û ����Ʈ ��������
	 * @param page �ش��ϴ� ������
	 * @return �ش��ϴ� ����Ʈ DTO�� ����Ʈ
	 */
	List<PointDTO> selectAll(int page);
	
	/**
	 * ��û ����Ʈ�� ������ ����Ʈ�� ��ȯ���ִ� �޼ҵ�
	 * @param userId �ش��ϴ� ���� ���̵�
	 * @return ���� ����
	 */
	int changePoint(String userId);
	
	
	/**
	 * �Աݾȵ� ��û ��û ����Ʈ �����ϰ� ����Ʈ���� �����ϱ�
	 * @param userId �ش��ϴ� ���� ���̵�
	 * @return ���� ����
	 */
	int pointCancel(String userId);
	
	/**
	 * ��û����Ʈ�� ��ü count�� ��������
	 * @return ��ü count ��
	 */
	int countPointList();

	/**
	 * �ش��ϴ� ������ requestPoint�� ��������
	 * @return requestPoint ��
	 */
	int getRequestPoint(String id);

	
	/**
	 * ������ �Ա� �� �ش��ϴ� ������ requestPoint�� �÷��ֱ�
	 * @param userId �ش��ϴ� ���� id
	 * @param select �ø��� ���� ��
	 * @return ���� ����
	 */
	int pointChargeBandBook(String userId, int select);
	
	/**
	 * ī�� �Ա� �� �ٷ� �ش��ϴ� ������ currentPoint�� ���������ֱ�
	 * @param userId �ش��ϴ� ���� id
	 * @param select �ø��� ���� ��
	 * @return ���� ����
	 */
	int pointChargeCard(String userId, int select);
	
	/**
	 * �ش��ϴ� ������ ����Ʈ ���� ��������
	 * @param userId �ش��ϴ� ���� id
	 * @return �ش��ϴ� ����Ʈ ������ �ɺθ� ����Ʈ
	 */
	List<ErrandsDTO> pointList(String userId);
	
	/**
	 * �ɺθ� �������� ���� ����Ʈ ���� ��������
	 * @param userId �ش��ϴ� ���� id
	 * @return �ش��ϴ� ����Ʈ ������ �ɺθ� ����Ʈ
	 */
	List<ErrandsDTO> searchPointSuccess(String userId);


}
