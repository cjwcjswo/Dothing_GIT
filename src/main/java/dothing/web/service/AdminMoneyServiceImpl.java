package dothing.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dothing.web.dao.AdminMoneyDAO;
import dothing.web.dto.BoardDTO;
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
	public int changePoint(String userId) throws Exception{
		int re = pointDAO.changePoint(userId);
		if(re == 0){
			throw new Exception("����Ʈ ��ȯ�ϴµ� ������ �߻��Ͽ����ϴ�.");
		}
		return re;
	}
	
	@Override
	public int pointCancel(String userId) throws Exception{
		int re = pointDAO.pointCancel(userId);
		if(re == 0){
			throw new Exception("��û ����Ʈ ����ϴµ� ������ �߻��Ͽ����ϴ�.");
		}
		return re;
	}
	
	@Override
	public int countPointList() {
		
		return pointDAO.countPointList();
	}

}