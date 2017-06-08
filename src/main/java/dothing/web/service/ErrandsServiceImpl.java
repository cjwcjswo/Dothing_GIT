package dothing.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dothing.web.dao.ErrandsDAO;
import dothing.web.dto.ErrandsDTO;

@Service
@Transactional
public class ErrandsServiceImpl implements ErrandsService {
	@Autowired
	ErrandsDAO errandsDAO;

	@Override
	public List<ErrandsDTO> selectAll() {
		return errandsDAO.selectAll();
	}

	@Override
	public ErrandsDTO selectErrands(int errandsNum) {
		return errandsDAO.selectErrands(errandsNum);
	}

	@Override
	public int insertErrands(ErrandsDTO dto) {
		errandsDAO.insertErrands(dto);
		errandsDAO.insertErrandsPos(dto);
		if ((dto.getHashtag() != null) && (dto.getHashtag().size() != 0)) {
			errandsDAO.insertErrandsHashtag(dto);
		}
		return 1;
	}

}
