package dothing.web.dao;

import java.util.List;

import dothing.web.dto.ErrandsDTO;

public interface ErrandsDAO {
	List<ErrandsDTO> selectAll();
}
