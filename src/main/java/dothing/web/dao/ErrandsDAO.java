package dothing.web.dao;

import java.util.List;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsPosDTO;
import dothing.web.dto.ErrandsReplyDTO;

public interface ErrandsDAO {
	List<ErrandsDTO> selectAll();
	List<ErrandsDTO> searchErrands(String hash, Integer minPrice, Integer maxPrice);
	ErrandsDTO selectErrands(int errandsNum);
	int insertErrands(ErrandsDTO dto);
	int insertErrandsPos(ErrandsPosDTO dto);
	int deleteErrands(int num);
	int countErrands();
	int selectNum();
	
	
	int insertReply(ErrandsReplyDTO dto);
	
}
