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
	 * @return �ɺθ� DTO ����Ʈ
	 * */
	List<ErrandsDTO> selectAll();

	/**
	 * �ɺθ� �˻��ϱ�(�ɺθ� ���� ��Ī �ȉ�� �ð��� ��ȿ�� ����Ʈ��)
	 * @param hash �ؽ��±׷� �˻����� ��� �ؽ��±� ��
	 * @param minPrice �ּ� ����
	 * @param maxPrice �ִ� ����
	 * @param distance �˻� �Ÿ� �ݰ�
	 * @param latitude �Ÿ��ݰ��� �߽� ����
	 * @param longitude �˻��ݰ��� �߽� �浵
	 * @return �ɺθ� DTO ����Ʈ
	 * */
	List<ErrandsDTO> searchErrands(String hash, Integer minPrice, Integer maxPrice, Integer distance, String latitude, String longitude);

	/**
	 * �ɺθ� ��ȣ�� �ش��ϴ� �ɺθ� ���� ��������
	 * @param errandsNum �ɺθ� ��ȣ
	 * @return �ɺθ� DTO
	 * */
	ErrandsDTO selectErrands(int errandsNum);

	/**
	 * ���� ��û �ɺθ� ����Ʈ �ҷ�����
	 * @param userId �ش��ϴ� ����
	 * @param page �ش��ϴ� ������(0�ϰ�� ��ü ����Ʈ)
	 * @return �ɺθ� DTO ����Ʈ
	 * */
	List<ErrandsDTO> myErrandsRequest(String userId, int page);
	
	/**
	 * ���� ���� �ɺθ� ����Ʈ �ҷ�����
	 * @param userId �ش��ϴ� ����
	 * @param page �ش��ϴ� ������(0�ϰ�� ��ü ����Ʈ)
	 * @return �ɺθ� DTO ����Ʈ
	 * */
	List<ErrandsDTO> myErrandsResponse(String userId, int page);
	
	/**
	 * ���� ��ü ��û �ɺθ� ����Ʈ ���� ��������
	 * @param id �ش��ϴ� ����
	 * @return �ɺθ� ����Ʈ�� count ��
	 * */
	int countMyRequest(String id);
	/**
	 * ���� ��ü ���� �ɺθ� ����Ʈ ���� ��������
	 * @param id �ش��ϴ� ����
	 * @return �ɺθ� ����Ʈ�� count ��
	 * */
	int countMyResponse(String id);
	
	/**
	 * �ɺθ� ����ϱ�
	 * @param dto �ɺθ� DTO
	 * @return ���� ����
	 * */
	int insertErrands(ErrandsDTO dto);

	/**
	 * �ɺθ� �����ϱ�
	 * @param num �����ϰ��� �ϴ� �ɺθ� ��ȣ
	 * @return ���� ����
	 */
	int deleteErrands(int num);
	
	/**
	 * �ɺθ� ����ϱ�
	 * @param num ����ϰ��� �ϴ� �ɺθ� ��ȣ
	 * @param point �ش��ϴ� �ɺθ��� point
	 * @param id �ٽ� ����Ʈ�� �������� ������ id
	 * @return ���� ����
	 */
	int cancelErrands(int num, int point,String id);
	
	/**
	 * �ɺθ� ����
	 * @param errandsNum ������ �ɺθ� ��ȣ
	 * @param responseId �ɺθ��� ���ۉ����� �ɺθ����� ���̵�
	 * @param requestId �ɺθ� ��û���� ���̵�(����Ʈ����)
	 * @param startTime �ɺθ� ���۽ð� ����
	 * @param arrivalTime �ɺθ����� Ȯ���������� �ð�
	 * @param finishTime ��û�ڰ� Ȯ���������� �ð�
	 * @param point ������ ����
	 * @return ���� ����
	 */
	int updateErrands(int errandsNum, String responseId, String requestId, String startTime,
			String arrivalTime, String finishTime, int point);
	
	
	/**
	 * DB���� currval�� �ش��ϴ� ��(�ɺθ� ��ȣ) ��������
	 * @return �ɺθ� ��ȣ
	 */
	int selectNum();

	/**
	 * ��ϵ� �ɺθ��� ���� ��������
	 * @return ��ü �ɺθ��� count ��
	 */
	int countErrands();
	
	/**
	 * �ɺθ� ���� �ݾ��� ���� ���� ���� �ɺθ� ����Ʈ ��������
	 * @return �ɺθ� ����Ʈ
	 */
	List<ErrandsDTO> moneyErrands();

	/**
	 * �ɺθ��� ��� ����ϱ�
	 * @param dto ����ϰ��� �ϴ� ��� DTO
	 * @return ���� ����
	 */
	int insertReply(ErrandsReplyDTO dto);
	
	/**
	 * �ش��ϴ� ��� �����ϱ�
	 * @param num �����ϰ��� �ϴ� ��� ��ȣ
	 * @return ���� ����
	 */
	int deleteReply(int num);
	
	/**
	 * �ؽ��±׷� �˻����� �� ������ �ܾ�� �����ֱ�
	 * @param hash ����ڰ� �Է��� ��
	 * @return ������ hashtagDTO ����Ʈ
	 */
	List<ErrandsHashtagDTO> requestHash(String hash);
	
	/**
	 * ���� ���� �߰��ϱ�
	 * @param dto ���� ���� dto
	 * @return ���� ����
	 */
	int insertGPA(GPADTO dto);
	
	/**
	 * ����ڰ� �ɺθ� �ϷḦ �������� ���μ���
	 * @param gpaDTO ����ڰ� ���濡�� �� ���� DTO
	 * @param id ������ ���̵�
	 * @param evalTag �� �ؽ��±�
	 * @param isAndroid �ȵ���̵忡�� ��������� ���� true = �ȵ���̵�, false = ��
	 * @return ���� ����
	 */
	int okRequest(GPADTO gpaDTO, String id, String evalTag, boolean isAndroid);
	
	/**
	 * �ش��ϴ� �������� ���� ���� ��������
	 * @param id �ش��ϴ� ������ ���̵�
	 * @return ���� ���� ����Ʈ
	 */
	List<GPADTO> selectGPAById(String id);
	
	/**
	 * ����Ʈ�� ���⿡�� �˻����� ��� �ɺθ� ����Ʈ ��������
	 * @param sort ���� ����(1= �ֽż� 2=���� �������� 3=���� ��������)
	 * @param addr �ּҷ� �˻�
	 * @param title �������� �˻�
	 * @param page �ش��ϴ� ������
	 * @return �ش��ϴ� �ɺθ� ����Ʈ
	 */
	List<ErrandsDTO> selectList(Integer sort, String addr, String title, int page);
	
	/**
	 * �ش��ϴ� �ּ��� �ɺθ� ���� ���ϱ�
	 * @param addr �˻� �ּ�
	 * @return �ɺθ��� ����
	 */
	int countList(String addr);
}
