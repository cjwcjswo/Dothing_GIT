package dothing.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dothing.web.dao.AdminMoneyDAO;
import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.PointDTO;

@Service
@Transactional
public class AdminMoneyServiceImpl implements AdminMoneyService {

	@Autowired
	private AdminMoneyDAO pointDAO;

	@Override
	public List<PointDTO> selectAll(int page) {
		List<PointDTO> list = pointDAO.selectAll(page);
		return list;
	}

	@Override
	public int changePoint(String userId) throws Exception {
		int re = pointDAO.changePoint(userId);
		if (re == 0) throw new Exception("포인트 전환하는데 오류가 발생하였습니다.");
		return re;
	}

	@Override
	public int pointCancel(String userId) throws Exception {
		int re = pointDAO.pointCancel(userId);
		if (re == 0) throw new Exception("요청 포인트 취소하는데 오류가 발생하였습니다.");
		return re;
	}

	@Override
	public int countPointList() {

		return pointDAO.countPointList();
	}

	@Override
	public int getRequestPoint(String id) {
		return pointDAO.getRequestPoint(id);
	}

	public int pointChargeBandBook(String userId, int select) {

		return pointDAO.pointChargeBandBook(userId, select);
	}

	@Override
	public int pointChargeCard(String userId, int select) {

		return pointDAO.pointChargeCard(userId, select);
	}
	
	@Override
	public List<ErrandsDTO> pointList(String userId) {
		
		return pointDAO.pointList(userId);
	}
	
	@Override
	public List<ErrandsDTO> searchPointSuccess(String userId) {
		
		return pointDAO.searchPointSuccess(userId);
	}

}
