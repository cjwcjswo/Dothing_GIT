package dothing.web.service;

import java.util.List;

import dothing.web.dto.PointDTO;

public interface AdminMoneyService {
	
	/**
	 * ������ �Ա� ��û ����Ʈ(��ȯ �ȵ� ����Ʈ��)
	 */
	List<PointDTO> selectAll();

}
