package dothing.web.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsReplyDTO;

public interface ErrandsService {
	List<ErrandsDTO> selectAll();

	List<ErrandsDTO> searchErrands(String hash, Integer minPrice, Integer maxPrice, Integer distance, String latitude, String longitude);

	ErrandsDTO selectErrands(int errandsNum);

	List<ErrandsDTO> myErrandsRequest(String userId, int page);
	
	List<ErrandsDTO> myErrandsResponse(String userId, int page);
	
	int countMyRequest();
	int countMyResponse();
	int insertErrands(ErrandsDTO dto, String path) throws FileNotFoundException, IOException;

	int deleteErrands(int num);
	
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

	int insertReply(ErrandsReplyDTO dto);
	int deleteReply(int num);
	Map<String, Integer> requestHash(String hash);
	
	void calHashes(List<ErrandsDTO> list);
}
