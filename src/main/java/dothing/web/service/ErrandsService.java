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
	 * 심부름 수정
	 * @param errandsNum : 수정할 심부름 번호
	 * @param responseId : 심부름이 시작됬을때 심부름꾼의 아이디
	 * @param requestId : 심부름 요청자의 아이디(포인트감소)
	 * @param startTime : 심부름 시작시간 여부
	 * @param arrivalTime : 심부름꾼이 확인했을때의 시간
	 * @param finishTime : 요청자가 확인했을때의 시간
	 * @param point : 가격을 감소
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
