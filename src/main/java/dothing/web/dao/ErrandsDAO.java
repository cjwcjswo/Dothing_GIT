package dothing.web.dao;

import java.util.List;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsHashtagDTO;
import dothing.web.dto.ErrandsPosDTO;
import dothing.web.dto.ErrandsReplyDTO;
import dothing.web.dto.GPADTO;

public interface ErrandsDAO {
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
	List<ErrandsDTO> searchErrands(String hash, Integer minPrice, Integer maxPrice,
			Integer distance, String latitude, String longitude);
	
	/**
	 * �ɺθ� �˻��ϱ�(���� ���� ��ü �ɺθ�)
	 * @param String hash: �ؽ��±׷� �˻����� ��� �ؽ��±� ��
	 * @param Integer minPrice: �ּ� ����
	 * @param Integer maxPrice: �ִ� ����
	 * @param Integer distance: �˻� �Ÿ� �ݰ�
	 * @param String latitude: �Ÿ��ݰ��� �߽� ����
	 * @param String longitude: �˻��ݰ��� �߽� �浵
	 * @return List<ErrandsDTO> �ɺθ� DTO ����Ʈ
	 * */
	List<ErrandsDTO> searchErrandsAll(String hash, Integer minPrice, Integer maxPrice,
			Integer distance, String latitude, String longitude);
	
	/**
	 * ���� ��û �ɺθ� ����Ʈ �ҷ�����
	 * @param String userId: �ش��ϴ� ����
	 * @param int page: �ش��ϴ� ������(0�ϰ�� ��ü ����Ʈ)
	 * @return List<ErrandsDTO>: �ɺθ� DTO ����Ʈ
	 * */
	List<ErrandsDTO> myRequestErrands(String userId, int page);
	
	/**
	 * ���� ���� �ɺθ� ����Ʈ �ҷ�����
	 * @param String userId: �ش��ϴ� ����
	 * @param int page: �ش��ϴ� ������(0�ϰ�� ��ü ����Ʈ)
	 * @return List<ErrandsDTO>: �ɺθ� DTO ����Ʈ
	 * */
	List<ErrandsDTO> myResponseErrands(String userId, int page);
	
	/**
	 * ���� ��ü ��û �ɺθ� ����Ʈ ���� ��������
	 * @param String userId: �ش��ϴ� ����
	 * @return int: �ɺθ� ����Ʈ�� count ��
	 * */
	int countMyRequest(String id);
	
	/**
	 * ���� ��ü ���� �ɺθ� ����Ʈ ���� ��������
	 * @param String userId: �ش��ϴ� ����
	 * @return int: �ɺθ� ����Ʈ�� count ��
	 * */
	int countMyResponse(String id);
	
	/**
	 * �ɺθ� ��ȣ�� �ش��ϴ� �ɺθ� ���� ��������
	 * @param int errandsNum: �ɺθ� ��ȣ
	 * @return ErrandsDTO: �ɺθ� DTO
	 * */
	ErrandsDTO selectErrands(int errandsNum);
	
	/**
	 * �ɺθ� ����ϱ�
	 * @param ErrandsDTO dto: �ɺθ� DTO
	 * @return int: ���� ����
	 * */
	int insertErrands(ErrandsDTO dto);
	
	/**
	 * �ɺθ� ��ġ���� ����ϱ�
	 * @param ErrandsPosDTO dto: �ɺθ� ��ġ���� DTO
	 * @return int: ���� ����
	 * */
	int insertErrandsPos(ErrandsPosDTO dto);
	
	
	/**
	 * �ش��ϴ� �ɺθ� ��ȣ�� �ɺθ� ��ġ���� ��������
	 * @param int errandsNum: �ɺθ� ��ȣ
	 * @return ErrandsPosDTO: �ɺθ� ��ġ ����
	 * */
	ErrandsPosDTO selectErrandsPos(int errandsNum);
	
	/**
	 * �ɺθ� �����ϱ�
	 * @param num: �����ϰ��� �ϴ� �ɺθ� ��ȣ
	 * @return int: ���� ����
	 */
	int deleteErrands(int num);
	
	/**
	 * ��ϵ� �ɺθ��� ���� ��������
	 * @return int: ��ü �ɺθ��� count ��
	 */
	int countErrands();
	
	/**
	 * DB���� currval�� �ش��ϴ� ��(�ɺθ� ��ȣ) ��������
	 * @return int: �ɺθ� ��ȣ
	 */
	int selectNum();
	
	/**
	 * �ش��ϴ� �ɺθ��� ���� �����ϱ�
	 * @param int errandsNum: �ش��ϴ� �ɺθ� ��ȣ
	 * @param String responseId: �߰� �� �ɺθ� ��޲� id
	 * @param String startTime: �ɺθ� ���� �ð� �߰�
	 * @param String arrivalTime: �ɺθ��� ���� �ð� �߰�
	 * @param String finishTime: ��û�ڰ� ��޹��� �ð� �߰�
	 * @return int: ���� ����
	 * */
	int updateErrands(int errandsNum, String responseId, String startTime,
			String arrivalTime, String finishTime);
	
	
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
	 * �ش��ϴ� �ɺθ��� ��� ����Ʈ ��������
	 * @param int num: �ش��ϴ� �ɺθ� ��ȣ
	 * @return List<ErrandsReplyDTO>: �ɺθ� ��� DTO ����Ʈ
	 */
	List<ErrandsReplyDTO> selectByErrands(int num);
	
	/**
	 * �ش��ϴ� �ɺθ��� ���� ���� ��������
	 * @param int num: �ش��ϴ� �ɺθ� ��ȣ
	 * @return List<GPADTO>: �ɺθ� ���� ����Ʈ
	 */
	List<GPADTO> selectGPA(int num);
	
	/**
	 * �ش��ϴ� �������� ���� ���� ��������
	 * @param String id: �ش��ϴ� ������ ���̵�
	 * @return List<GPADTO>: ���� ���� ����Ʈ
	 */
	List<GPADTO> selectGPAById(String id);
	
	/**
	 * ���� ���� �߰��ϱ�
	 * @param GPADTO dto: ���� ���� dto
	 * @return int: ���� ����
	 */
	int insertGPA(GPADTO dto);
	
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
	

	/**
	 * ���� �ð� �������� ��û �ɺθ� �ð��� ���� �ɺθ��� ��� ����
	 * @return int ��������(������ �ɺθ��� ����)
	 */
	int deleteTimeErrands();
	
	/**
	 * �ɺθ��� �ؽ��±� �����ϱ�
	 * @param String hashtag: �ؽ��±� ��
	 * @return int: ���� ����
	 */
	int insertErrandsHashtag(String hashtag);
	
	/**
	 * �ش��ϴ� �ɺθ��� �ؽ��±׵� ��������
	 * @param int errandsNum: �ش��ϴ� �ɺθ��� ��ȣ
	 * @return
	 */
	List<String> selectErrandsHashtag(int errandsNum);
	
	/**
	 * �ؽ��±׷� �˻����� �� ������ �ܾ�� �����ֱ�
	 * @param String keyword: ����ڰ� �Է��� ��
	 * @return List<ErrandsHashtagDTO> ������ hashtagDTO ����Ʈ
	 */
	List<ErrandsHashtagDTO> serachErrandsHashtag(String keyword);
}
