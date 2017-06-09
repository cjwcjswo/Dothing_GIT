package dothing.web.service;

import java.util.List;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsReplyDTO;

public interface ErrandsService {
	List<ErrandsDTO> selectAll(int page);
	ErrandsDTO selectErrands(int errandsNum);
	int insertErrands(ErrandsDTO dto);
	int deleteErrands(int num);
	int selectNum();
	int countErrands();
	
	int insertReply(ErrandsReplyDTO dto);
}
