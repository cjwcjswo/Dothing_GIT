package dothing.web.dao;

import java.util.List;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsHashtagDTO;
import dothing.web.dto.ErrandsPosDTO;
import dothing.web.dto.ErrandsReplyDTO;
import dothing.web.dto.GPADTO;

public interface ErrandsDAO {
	List<ErrandsDTO> selectAll();
	List<ErrandsDTO> searchErrands(String hash, Integer minPrice, Integer maxPrice,
			Integer distance, String latitude, String longitude);
	List<ErrandsDTO> searchErrandsAll(String hash, Integer minPrice, Integer maxPrice,
			Integer distance, String latitude, String longitude);
	List<ErrandsDTO> myRequestErrands(String userId, int page);
	int countMyRequest(String id);
	int countMyResponse(String id);
	List<ErrandsDTO> myResponseErrands(String userId, int page);
	ErrandsDTO selectErrands(int errandsNum);
	int insertErrands(ErrandsDTO dto);
	int insertErrandsPos(ErrandsPosDTO dto);
	/**
	 * 해당하는 num의 위치정보 불러오기
	 */
	ErrandsPosDTO selectErrandsPos(int errandsNum);
	int deleteErrands(int num);
	int countErrands();
	int selectNum();
	int updateErrands(int errandsNum, String responseId, String startTime,
			String arrivalTime, String finishTime);
	/**
	 * 돈되는 심부름
	 * */
	List<ErrandsDTO> moneyErrands();
	
	int insertReply(ErrandsReplyDTO dto);
	int deleteReply(int num);
	List<ErrandsReplyDTO> selectByErrands(int num);
	
	List<GPADTO> selectGPA(int num);
	List<GPADTO> selectGPAById(String id);
	int insertGPA(GPADTO dto);
	
	/**
	 * 리스트에서 검색
	 */
	List<ErrandsDTO> selectList(Integer sort, String addr, int page);
	int countList(String addr);
	
	/**
	 * 시간지나고 응답안한 심부름 삭제
	 */
	int deleteTimeErrands();
	
	/**
	 * 심부름에 해쉬태그 삽입
	 */
	int insertErrandsHashtag(String hashtag);
	/**
	 * 심부름 해쉬태그 불러오기
	 */
	List<String> selectErrandsHashtag(int errandsNum);
	/**
	 * 해시태그 단어검색
	 */
	List<ErrandsHashtagDTO> serachErrandsHashtag(String keyword);
}
