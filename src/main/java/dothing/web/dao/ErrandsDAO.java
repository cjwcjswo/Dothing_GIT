package dothing.web.dao;

import java.util.List;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsHashtagDTO;
import dothing.web.dto.ErrandsPosDTO;

public interface ErrandsDAO {
	List<ErrandsDTO> selectAll();
	ErrandsDTO selectErrands(int errandsNum);
	int insertErrands(ErrandsDTO dto);
	int insertErrandsPos(ErrandsDTO dto);
	int insertErrandsHashtag(ErrandsDTO dto);
}
