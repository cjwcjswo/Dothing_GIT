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
	 * @return �ɺθ� DTO ����Ʈ
	 * */
	List<ErrandsDTO> selectAll();
	
	/**
	 * �ɺθ� �˻��ϱ�(�ɺθ� ���� ��Ī �ȉ�� �ð��� ��ȿ�� ����Ʈ��)
	 * @param hash �ؽ��±׷� �˻����� ��� �ؽ��±� ��
	 * @param minPrice �ּ� ����
	 * @param maxPrice �ִ� ����
	 * @param distance:�˻� �Ÿ� �ݰ�
	 * @param latitude �Ÿ��ݰ��� �߽� ����
	 * @param longitude �˻��ݰ��� �߽� �浵
	 * @return �ɺθ� DTO ����Ʈ
	 * */
	List<ErrandsDTO> searchErrands(String hash, Integer minPrice, Integer maxPrice,
			Integer distance, String latitude, String longitude);
	
	/**
	 * �ɺθ� �˻��ϱ�(���� ���� ��ü �ɺθ�)
	 * @param hash �ؽ��±׷� �˻����� ��� �ؽ��±� ��
	 * @param minPrice �ּ� ����
	 * @param maxPrice �ִ� ����
	 * @param distance �˻� �Ÿ� �ݰ�
	 * @param latitude �Ÿ��ݰ��� �߽� ����
	 * @param longitude �˻��ݰ��� �߽� �浵
	 * @return �ɺθ� DTO ����Ʈ
	 * */
	List<ErrandsDTO> searchErrandsAll(String hash, Integer minPrice, Integer maxPrice,
			Integer distance, String latitude, String longitude);
	
	/**
	 * ���� ��û �ɺθ� ����Ʈ �ҷ�����
	 * @param userId �ش��ϴ� ����
	 * @param page �ش��ϴ� ������(0�ϰ�� ��ü ����Ʈ)
	 * @return �ɺθ� DTO ����Ʈ
	 * */
	List<ErrandsDTO> myRequestErrands(String userId, int page);
	
	/**
	 * ���� ���� �ɺθ� ����Ʈ �ҷ�����
	 * @param userId �ش��ϴ� ����
	 * @param page �ش��ϴ� ������(0�ϰ�� ��ü ����Ʈ)
	 * @return �ɺθ� DTO ����Ʈ
	 * */
	List<ErrandsDTO> myResponseErrands(String userId, int page);
	
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
	 * �ɺθ� ��ȣ�� �ش��ϴ� �ɺθ� ���� ��������
	 * @param errandsNum �ɺθ� ��ȣ
	 * @return �ɺθ� DTO
	 * */
	ErrandsDTO selectErrands(int errandsNum);
	
	/**
	 * �ɺθ� ����ϱ�
	 * @param dto �ɺθ� DTO
	 * @return ���� ����
	 * */
	int insertErrands(ErrandsDTO dto);
	
	/**
	 * �ɺθ� ��ġ���� ����ϱ�
	 * @param dto �ɺθ� ��ġ���� DTO
	 * @return ���� ����
	 * */
	int insertErrandsPos(ErrandsPosDTO dto);
	
	
	/**
	 * �ش��ϴ� �ɺθ� ��ȣ�� �ɺθ� ��ġ���� ��������
	 * @param errandsNum �ɺθ� ��ȣ
	 * @return �ɺθ� ��ġ ����
	 * */
	ErrandsPosDTO selectErrandsPos(int errandsNum);
	
	/**
	 * �ɺθ� �����ϱ�
	 * @param num �����ϰ��� �ϴ� �ɺθ� ��ȣ
	 * @return ���� ����
	 */
	int deleteErrands(int num);
	
	/**
	 * ��ϵ� �ɺθ��� ���� ��������
	 * @return ��ü �ɺθ��� count ��
	 */
	int countErrands();
	
	/**
	 * DB���� currval�� �ش��ϴ� ��(�ɺθ� ��ȣ) ��������
	 * @return �ɺθ� ��ȣ
	 */
	int selectNum();
	
	/**
	 * �ش��ϴ� �ɺθ��� ���� �����ϱ�
	 * @param errandsNum �ش��ϴ� �ɺθ� ��ȣ
	 * @param responseId �߰� �� �ɺθ� ��޲� id
	 * @param startTime �ɺθ� ���� �ð� �߰�
	 * @param arrivalTime �ɺθ��� ���� �ð� �߰�
	 * @param finishTime ��û�ڰ� ��޹��� �ð� �߰�
	 * @return ���� ����
	 * */
	int updateErrands(int errandsNum, String responseId, String startTime,
			String arrivalTime, String finishTime);
	
	
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
	 * �ش��ϴ� �ɺθ��� ��� ����Ʈ ��������
	 * @param num �ش��ϴ� �ɺθ� ��ȣ
	 * @return �ɺθ� ��� DTO ����Ʈ
	 */
	List<ErrandsReplyDTO> selectByErrands(int num);
	
	/**
	 * �ش��ϴ� �ɺθ��� ���� ���� ��������
	 * @param num �ش��ϴ� �ɺθ� ��ȣ
	 * @return �ɺθ� ���� ����Ʈ
	 */
	List<GPADTO> selectGPA(int num);
	
	/**
	 * �ش��ϴ� �������� ���� ���� ��������
	 * @param id �ش��ϴ� ������ ���̵�
	 * @return ���� ���� ����Ʈ
	 */
	List<GPADTO> selectGPAById(String id);
	
	/**
	 * ���� ���� �߰��ϱ�
	 * @param dto ���� ���� dto
	 * @return ���� ����
	 */
	int insertGPA(GPADTO dto);
	
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
	

	/**
	 * ���� �ð� �������� ��û �ɺθ� �ð��� ���� �ɺθ��� ��� ����
	 * @return ��������(������ �ɺθ��� ����)
	 */
	int deleteTimeErrands();
	
	/**
	 * �ɺθ��� �ؽ��±� �����ϱ�
	 * @param hashtag �ؽ��±� ��
	 * @return ���� ����
	 */
	int insertErrandsHashtag(String hashtag);
	
	/**
	 * �ش��ϴ� �ɺθ��� �ؽ��±׵� ��������
	 * @param errandsNum �ش��ϴ� �ɺθ��� ��ȣ
	 * @return �ɺθ��� �ؽ��±׵�
	 */
	List<String> selectErrandsHashtag(int errandsNum);
	
	/**
	 * �ؽ��±׷� �˻����� �� ������ �ܾ�� �����ֱ�
	 * @param keyword ����ڰ� �Է��� ��
	 * @return ������ hashtagDTO ����Ʈ
	 */
	List<ErrandsHashtagDTO> serachErrandsHashtag(String keyword);
}
