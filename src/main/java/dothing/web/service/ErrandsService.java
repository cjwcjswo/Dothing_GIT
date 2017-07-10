package dothing.web.service;

import java.util.List;
import java.util.Map;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsHashtagDTO;
import dothing.web.dto.ErrandsReplyDTO;
import dothing.web.dto.GPADTO;

public interface ErrandsService {
	List<ErrandsDTO> selectAll();

	List<ErrandsDTO> searchErrands(String hash, Integer minPrice, Integer maxPrice, Integer distance, String latitude, String longitude);

	ErrandsDTO selectErrands(int errandsNum);

	List<ErrandsDTO> myErrandsRequest(String userId, int page);
	
	List<ErrandsDTO> myErrandsResponse(String userId, int page);
	
	int countMyRequest(String id);
	int countMyResponse(String id);
	int insertErrands(ErrandsDTO dto, String path);

	int deleteErrands(int num);
	int cancleErrands(int num, int point,String id);
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
	
	int selectNum();

	int countErrands();
	
	/**
	 * ���Ǵ� �ɺθ�
	 */
	List<ErrandsDTO> moneyErrands();

	int insertReply(ErrandsReplyDTO dto);
	int deleteReply(int num);
	List<ErrandsHashtagDTO> requestHash(String hash);
	/**
	 * GPA����
	 */
	int insertGPA(GPADTO dto);
	
	/**
	 * ��û�� Ȯ��
	 */
	int okRequest(GPADTO gpaDTO, String id, String evalTag);
	
	List<GPADTO> selectGPAById(String id);
	
	List<ErrandsDTO> selectList(Integer sort, String addr, int page);
	int countList(String addr);
}
