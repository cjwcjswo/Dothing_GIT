package dothing.web.dao;

import java.util.List;

import dothing.web.dto.ErrandsDTO;
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
}
