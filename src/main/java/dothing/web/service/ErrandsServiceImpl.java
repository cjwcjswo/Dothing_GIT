package dothing.web.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dothing.web.dao.ErrandsDAO;
import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsReplyDTO;
import dothing.web.properties.ErrandsHashProperties;

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
	public int insertErrands(ErrandsDTO dto, String path) throws FileNotFoundException, IOException {
		ErrandsHashProperties hp = new ErrandsHashProperties();
		errandsDAO.insertErrands(dto);
		errandsDAO.insertErrandsPos(dto.getErrandsPos());
		String content = dto.getContent();
		List<String> hashes = new ArrayList<>();
		int index = 0;

		while((index = content.indexOf('#', index)) != -1){
			int restIndex = content.indexOf(" ", index);
			if(restIndex == -1){
				restIndex = content.length();
			}
			hashes.add(content.substring(index+1, restIndex));
			index = restIndex;
		}
		hp.saveHashtag(hashes, path);
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
