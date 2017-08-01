package dothing.web.service;

import java.util.List;
import java.util.Map;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsHashtagDTO;
import dothing.web.dto.ErrandsReplyDTO;
import dothing.web.dto.GPADTO;

public interface ErrandsService {
	/**
	 * ��ü �ɺθ� ����Ʈ ��������
	 * @return List<ErrandsDTO>: �ɺθ� DTO ����Ʈ
	 * */
	List<ErrandsDTO> selectAll();

	/**
	 * �ɺθ� �˻��ϱ�(�ɺθ� ���� ��Ī �ȉ�� �ð��� ��ȿ�� ����Ʈ��)
	 * @param String hash: �ؽ��±׷� �˻����� ��� �ؽ��±� ��
	 * @param Integer minPrice: �ּ� ����
	 * @param Integer maxPrice: �ִ� ����
	 * @param Integer distance: �˻� �Ÿ� �ݰ�
	 * @param String latitude: �Ÿ��ݰ��� �߽� ����
	 * @param String longitude: �˻��ݰ��� �߽� �浵
	 * @return List<ErrandsDTO> �ɺθ� DTO ����Ʈ
	 * */
	List<ErrandsDTO> searchErrands(String hash, Integer minPrice, Integer maxPrice, Integer distance, String latitude, String longitude);

	/**
	 * �ɺθ� ��ȣ�� �ش��ϴ� �ɺθ� ���� ��������
	 * @param int errandsNum: �ɺθ� ��ȣ
	 * @return ErrandsDTO: �ɺθ� DTO
	 * */
	ErrandsDTO selectErrands(int errandsNum);

	/**
	 * ���� ��û �ɺθ� ����Ʈ �ҷ�����
	 * @param String userId: �ش��ϴ� ����
	 * @param int page: �ش��ϴ� ������(0�ϰ�� ��ü ����Ʈ)
	 * @return List<ErrandsDTO>: �ɺθ� DTO ����Ʈ
	 * */
	List<ErrandsDTO> myErrandsRequest(String userId, int page);
	
	/**
	 * ���� ���� �ɺθ� ����Ʈ �ҷ�����
	 * @param String userId: �ش��ϴ� ����
	 * @param int page: �ش��ϴ� ������(0�ϰ�� ��ü ����Ʈ)
	 * @return List<ErrandsDTO>: �ɺθ� DTO ����Ʈ
	 * */
	List<ErrandsDTO> myErrandsResponse(String userId, int page);
	
	/**
	 * ���� ��ü ��û �ɺθ� ����Ʈ ���� ��������
	 * @param String id: �ش��ϴ� ����
	 * @return int: �ɺθ� ����Ʈ�� count ��
	 * */
	int countMyRequest(String id);
	/**
	 * ���� ��ü ���� �ɺθ� ����Ʈ ���� ��������
	 * @param String id: �ش��ϴ� ����
	 * @return int: �ɺθ� ����Ʈ�� count ��
	 * */
	int countMyResponse(String id);
	
	/**
	 * �ɺθ� ����ϱ�
	 * @param ErrandsDTO dto: �ɺθ� DTO
	 * @return int: ���� ����
	 * */
	int insertErrands(ErrandsDTO dto);

	/**
	 * �ɺθ� �����ϱ�
	 * @param num: �����ϰ��� �ϴ� �ɺθ� ��ȣ
	 * @return int: ���� ����
	 */
	int deleteErrands(int num);
	
	/**
	 * �ɺθ� ����ϱ�
	 * @param int num: ����ϰ��� �ϴ� �ɺθ� ��ȣ
	 * @param point: �ش��ϴ� �ɺθ��� point
	 * @param id: �ٽ� ����Ʈ�� �������� ������ id
	 * @return
	 */
	int cancelErrands(int num, int point,String id);
	
	/**
	 * �ɺθ� ����
	 * @param errandsNum : ������ �ɺθ� ��ȣ
	 * @param responseId : �ɺθ��� ���ۉ����� �ɺθ����� ���̵�
	 * @param requestId : �ɺθ� ��û���� ���̵�(����Ʈ����)
	 * @param startTime : �ɺθ� ���۽ð� ����
	 * @param arrivalTime : �ɺθ����� Ȯ���������� �ð�
	 * @param finishTime : ��û�ڰ� Ȯ���������� �ð�
	 * @param point : ������ ����
	 * @return
	 */
	int updateErrands(int errandsNum, String responseId, String requestId, String startTime,
			String arrivalTime, String finishTime, int point);
	
	
	/**
	 * DB���� currval�� �ش��ϴ� ��(�ɺθ� ��ȣ) ��������
	 * @return int: �ɺθ� ��ȣ
	 */
	int selectNum();

	/**
	 * ��ϵ� �ɺθ��� ���� ��������
	 * @return int: ��ü �ɺθ��� count ��
	 */
	int countErrands();
	
	/**
	 * �ɺθ� ���� �ݾ��� ���� ���� ���� �ɺθ� ����Ʈ ��������
	 * @return List<ErrandsDTO>: �ɺθ� ����Ʈ
	 */
	List<ErrandsDTO> moneyErrands();

	/**
	 * �ɺθ��� ��� ����ϱ�
	 * @param ErrandsReplyDTO dto: ����ϰ��� �ϴ� ��� DTO
	 * @return int: ���� ����
	 */
	int insertReply(ErrandsReplyDTO dto);
	
	/**
	 * �ش��ϴ� ��� �����ϱ�
	 * @param int num: �����ϰ��� �ϴ� ��� ��ȣ
	 * @return int: ���� ����
	 */
	int deleteReply(int num);
	
	/**
	 * �ؽ��±׷� �˻����� �� ������ �ܾ�� �����ֱ�
	 * @param String hash: ����ڰ� �Է��� ��
	 * @return List<ErrandsHashtagDTO> ������ hashtagDTO ����Ʈ
	 */
	List<ErrandsHashtagDTO> requestHash(String hash);
	
	/**
	 * ���� ���� �߰��ϱ�
	 * @param GPADTO dto: ���� ���� dto
	 * @return int: ���� ����
	 */
	int insertGPA(GPADTO dto);
	
	/**
	 * ����ڰ� �ɺθ� �ϷḦ �������� ���μ���
	 * @param GPADTO gpaDTO: ����ڰ� ���濡�� �� ���� DTO
	 * @param String id: ������ ���̵�
	 * @param String evalTag: �� �ؽ��±�
	 * @param boolean isAndroid: �ȵ���̵忡�� ��������� ���� true = �ȵ���̵�, false = ��
	 * @return
	 */
	int okRequest(GPADTO gpaDTO, String id, String evalTag, boolean isAndroid);
	
	/**
	 * �ش��ϴ� �������� ���� ���� ��������
	 * @param String id: �ش��ϴ� ������ ���̵�
	 * @return List<GPADTO>: ���� ���� ����Ʈ
	 */
	List<GPADTO> selectGPAById(String id);
	
	/**
	 * ����Ʈ�� ���⿡�� �˻����� ��� �ɺθ� ����Ʈ ��������
	 * @param Integer sort: ���� ����(1= �ֽż� 2=���� �������� 3=���� ��������)
	 * @param String addr: �ּҷ� �˻�
	 * @param String title: �������� �˻�
	 * @param int page: �ش��ϴ� ������
	 * @return List<ErrandsDTO>: �ش��ϴ� �ɺθ� ����Ʈ
	 */
	List<ErrandsDTO> selectList(Integer sort, String addr, String title, int page);
	
	/**
	 * �ش��ϴ� �ּ��� �ɺθ� ���� ���ϱ�
	 * @param String addr: �˻� �ּ�
	 * @return int: �ɺθ��� ����
	 */
	int countList(String addr);
}
