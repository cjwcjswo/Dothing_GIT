package dothing.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dothing.web.dao.ErrandsDAO;
import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsHashtagDTO;
import dothing.web.dto.ErrandsReplyDTO;

@Service
@Transactional
public class ErrandsServiceImpl implements ErrandsService {
	@Autowired
	ErrandsDAO errandsDAO;

	@Override
	public List<ErrandsDTO> selectAll(int page) {
		return errandsDAO.selectAll(page);
	}

	@Override
	public ErrandsDTO selectErrands(int errandsNum) {
		return errandsDAO.selectErrands(errandsNum);
	}

	@Override
	public int insertErrands(ErrandsDTO dto) {
		errandsDAO.insertErrands(dto);
		errandsDAO.insertErrandsPos(dto.getErrandsPos());
		if ((dto.getHashtag() != null) && (dto.getHashtag().size() != 0)) {
			for (int i = 0; i < 3; i++) {
				ErrandsHashtagDTO hashDTO = dto.getHashtag().get(i);
				if (hashDTO.getErrandsHashtag() != null && !hashDTO.getErrandsHashtag().trim().equals("")) {
					errandsDAO.insertErrandsHashtag(hashDTO);
				}
			}
		}
		return 1;
	}

	@Override
	public int selectNum() {
		return errandsDAO.selectNum();
	}

	@Override
	public int insertReply(ErrandsReplyDTO dto) {
		return errandsDAO.insertReply(dto);
	}

	@Override
	public int deleteErrands(int num) {
		return errandsDAO.deleteErrands(num);
	}

	@Override
	public int countErrands() {
		return errandsDAO.countErrands();
	}

}
