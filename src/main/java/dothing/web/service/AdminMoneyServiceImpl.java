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
	public List<PointDTO> selectAll() {
		List<PointDTO> list = pointDAO.selectAll();
		return list;
	}

}
