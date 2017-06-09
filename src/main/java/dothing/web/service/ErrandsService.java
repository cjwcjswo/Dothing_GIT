package dothing.web.service;

import java.util.List;

import dothing.web.dto.ErrandsDTO;

public interface ErrandsService {
	List<ErrandsDTO> selectAll();
	ErrandsDTO selectErrands(int errandsNum);
	int insertErrands(ErrandsDTO dto);
	int selectNum();
}
