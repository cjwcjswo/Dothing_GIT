package dothing.web.dao;

import java.util.List;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.PointDTO;

public interface AdminMoneyDAO {
	
	/**
	 * 무통장 입금 신청 리스트 가져오기
	 * @param int page: 해당하는 페이지
	 * @return List<PointDTO>: 해당하는 포인트 DTO의 리스트
	 */
	List<PointDTO> selectAll(int page);
	
	/**
	 * 신청 포인트를 실질적 포인트로 전환해주는 메소드
	 * @param String userId: 해당하는 유저 아이디
	 * @return int: 성공 여부
	 */
	int changePoint(String userId);
	
	
	/**
	 * 입금안된 신청 요청 포인트 리셋하고 리스트에서 삭제하기
	 * @param String userId: 해당하는 유저 아이디
	 * @return int: 성공 여부
	 */
	int pointCancel(String userId);
	
	/**
	 * 신청포인트의 전체 count값 가져오기
	 * @return int: 전체 count 값
	 */
	int countPointList();

	/**
	 * 해당하는 유저의 requestPoint값 가져오기
	 * @return int: requestPoint 값
	 */
	int getRequestPoint(String id);

	
	/**
	 * 무통장 입금 시 해당하는 유저의 requestPoint값 올려주기
	 * @param String userId: 해당하는 유저 id
	 * @param int select: 올리고 싶은 값
	 * @return int: 성공 여부
	 */
	int pointChargeBandBook(String userId, int select);
	
	/**
	 * 카드 입금 시 바로 해당하는 유저의 currentPoint값 증가시켜주기
	 * @param String userId: 해당하는 유저 id
	 * @param int select: 올리고 싶은 값
	 * @return int: 성공 여부
	 */
	int pointChargeCard(String userId, int select);
	
	/**
	 * 해당하는 유저의 포인트 내역 가져오기
	 * @param String userId: 해당하는 유저 id
	 * @return List<ErrandsDTO>: 해당하는 포인트 내역의 심부름 리스트
	 */
	List<ErrandsDTO> pointList(String userId);
	
	/**
	 * 심부름 수행으로 받은 포인트 내역 가져오기
	 * @param String userId: 해당하는 유저 id
	 * @return List<ErrandsDTO>: 해당하는 포인트 내역의 심부름 리스트
	 */
	List<ErrandsDTO> searchPointSuccess(String userId);


}
