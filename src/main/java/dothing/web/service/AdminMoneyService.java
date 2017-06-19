package dothing.web.service;

import java.util.List;

import dothing.web.dto.PointDTO;

public interface AdminMoneyService {
	
	/**
	 * 무통장 입금 신청 리스트(전환 안된 리스트만)
	 */
	List<PointDTO> selectAll();

}
