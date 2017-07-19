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
	 * �ش��ϴ� num�� ��ġ���� �ҷ�����
	 */
	ErrandsPosDTO selectErrandsPos(int errandsNum);
	int deleteErrands(int num);
	int countErrands();
	int selectNum();
	int updateErrands(int errandsNum, String responseId, String startTime,
			String arrivalTime, String finishTime);
	/**
	 * ���Ǵ� �ɺθ�
	 * */
	List<ErrandsDTO> moneyErrands();
	
	int insertReply(ErrandsReplyDTO dto);
	int deleteReply(int num);
	List<ErrandsReplyDTO> selectByErrands(int num);
	
	List<GPADTO> selectGPA(int num);
	List<GPADTO> selectGPAById(String id);
	int insertGPA(GPADTO dto);
	
	/**
	 * ����Ʈ���� �˻�
	 */
	List<ErrandsDTO> selectList(Integer sort, String addr, int page);
	int countList(String addr);
	
	/**
	 * �ð������� ������� �ɺθ� ����
	 */
	int deleteTimeErrands();
	
	/**
	 * �ɺθ��� �ؽ��±� ����
	 */
	int insertErrandsHashtag(String hashtag);
	/**
	 * �ɺθ� �ؽ��±� �ҷ�����
	 */
	List<String> selectErrandsHashtag(int errandsNum);
	/**
	 * �ؽ��±� �ܾ�˻�
	 */
	List<ErrandsHashtagDTO> serachErrandsHashtag(String keyword);
}
