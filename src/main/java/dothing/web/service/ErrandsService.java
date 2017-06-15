package dothing.web.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsReplyDTO;

public interface ErrandsService {
	List<ErrandsDTO> selectAll();

	List<ErrandsDTO> searchErrands(String hash, Integer minPrice, Integer maxPrice);

	ErrandsDTO selectErrands(int errandsNum);

	List<ErrandsDTO> myErrandsRequest(String userId);
	
	List<ErrandsDTO> myErrandsResponse(String userId);
	
	int insertErrands(ErrandsDTO dto, String path) throws FileNotFoundException, IOException;

	int deleteErrands(int num);

	int selectNum();

	int countErrands();

	int insertReply(ErrandsReplyDTO dto);
	
	Map<String, Integer> requestHash(String hash);
	
	void calHashes(List<ErrandsDTO> list);
}
