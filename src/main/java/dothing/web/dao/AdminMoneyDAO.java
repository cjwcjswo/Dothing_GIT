package dothing.web.dao;

import java.util.List;

import dothing.web.dto.PointDTO;

public interface AdminMoneyDAO {
	
	/**
	 * 무통장 입금 신청 리스트(전환 안된 리스트만)
	 */
	List<PointDTO> selectAll(int page);
	
	/**
	 * 포인트로 전환
	 */
	int changePoint(String userId);
	
	
	/**
	 * 입금안된 신청 요청 포인트 리셋하고 리스트에서 삭제하기
	 */
	int pointCancel(String userId);
	
	/**
	 * 페이징
	 */
	int countPointList();

	/**
	 * 리퀘스트포인트 가져오기
	 */
	int getRequestPoint(String id);

	
	/**
	 * 무통장 입금 포인트 요청값 올리기
	 */
	int pointChargeBandBook(String userId, int select);
	
	/**
	 * 카드 입금 포인트 값 변경해주기
	 */
	int pointChargeCard(String userId, int select);


}
