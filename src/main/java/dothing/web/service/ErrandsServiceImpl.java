package dothing.web.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dothing.web.dao.ErrandsDAO;
import dothing.web.dao.MemberDAO;
import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsReplyDTO;
import dothing.web.dto.GPADTO;
import dothing.web.dto.MemberDTO;
import dothing.web.properties.ErrandsHashProperties;

@Service
@Transactional
public class ErrandsServiceImpl implements ErrandsService {
	@Autowired
	ErrandsDAO errandsDAO;
	@Autowired
	MemberDAO memberDAO;
	@Autowired
	MemberService memberService;
	@Override
	public List<ErrandsDTO> selectAll() {
		List<ErrandsDTO> list = errandsDAO.selectAll();
		calHashes(list);
		return errandsDAO.selectAll();
	}

	
	@Override
	public ErrandsDTO selectErrands(int errandsNum) {
		ErrandsDTO dto = errandsDAO.selectErrands(errandsNum);
		MemberDTO request = dto.getRequestUser();
		MemberDTO response = dto.getResponseUser();
		if(response != null){
			response = memberDAO.selectMemberById(response.getUserId());
		}
		request.setGpaList(errandsDAO.selectGPAById(request.getUserId()));
		request.setHashList(memberDAO.selectHashtag(request.getUserId()));
		dto.setHashes(new ArrayList<>());
		List<String> hashes = dto.getHashes();
		int index = 0;
		String content = dto.getContent();
		while ((index = content.indexOf("#", index)) != -1) {
			int restIndex = content.indexOf(" ", index);
			if (restIndex == -1) {
				restIndex = content.length();
			}
			String result = content.substring(index + 1, restIndex);
			hashes.add(result);
			index = restIndex;
		}
		return dto;
	}

	/**
	 * 심부름 삽입
	 */
	@Override
	public int insertErrands(ErrandsDTO dto, String path) throws FileNotFoundException, IOException {
		ErrandsHashProperties hp = new ErrandsHashProperties();
		errandsDAO.insertErrands(dto);
		errandsDAO.insertErrandsPos(dto.getErrandsPos());

		return 1;
	}

	/**
	 * 해당 심부름의 심부름 번호 셀렉트
	 */
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

	@Override
	public List<ErrandsDTO> searchErrands(String hash, Integer minPrice, Integer maxPrice, Integer distance,
			String latitude, String longitude) {
		List<ErrandsDTO> list = errandsDAO.searchErrands(hash, minPrice, maxPrice, distance, latitude, longitude);
		calHashes(list);
		return list;
	}

	@Override
	public Map<String, Integer> requestHash(String hash) {
		List<ErrandsDTO> list = errandsDAO.searchErrands(hash, null, null, null, null, null);
		Map<String, Integer> info = new HashMap<String, Integer>();
		System.out.println("************************************");
		for (ErrandsDTO dto : list) {
			int index = 0;
			String content = dto.getContent();
			while ((index = content.indexOf(hash, index)) != -1) {
				int restIndex = content.indexOf(" ", index);
				if (restIndex == -1) {
					restIndex = content.length();
				}
				String result = content.substring(index + 1, restIndex);
				if (info.containsKey(result)) {
					info.put(result, info.get(result) + 1);
				} else {
					info.put(result, 1);
				}
				index = restIndex;
			}
		}
		System.out.println(hash + "로 검색한 맵");
		System.out.println(info);
		System.out.println("************************************");
		info.remove("");

		return info;
	}

	public static List<String> sortByValue(Map<String, Integer> map) {
		List<String> list = new ArrayList<>();
		list.addAll(map.keySet());

		Collections.sort(list, new Comparator<Object>() {

			public int compare(Object o1, Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);

				return ((Comparable) v1).compareTo(v2);
			}

		});
		Collections.reverse(list); // 주석시 오름차순
		return list;
	}

	/**
	 * 내 요청 심부름 조회
	 */
	@Override
	public List<ErrandsDTO> myErrandsRequest(String userId, int page) {
		List<ErrandsDTO> list = errandsDAO.myRequestErrands(userId, page);
		calHashes(list);
		for(ErrandsDTO dto : list){
			dto.setErrandsReply(errandsDAO.selectByErrands(dto.getErrandsNum()));
			dto.setGpa(errandsDAO.selectGPA(dto.getErrandsNum()));
		}
		return list;
	}

	/**
	 * 내 응답 심부름 조회
	 */
	@Override
	public List<ErrandsDTO> myErrandsResponse(String userId, int page) {
		List<ErrandsDTO> list = errandsDAO.myResponseErrands(userId, page);
		calHashes(list);
		for(ErrandsDTO dto : list){
			dto.setErrandsReply(errandsDAO.selectByErrands(dto.getErrandsNum()));
			dto.setGpa(errandsDAO.selectGPA(dto.getErrandsNum()));
		}
		return list;
	}

	@Override
	public int countMyRequest(String id) {
		return errandsDAO.countMyRequest(id);
	}

	@Override
	public int countMyResponse(String id) {
		return errandsDAO.countMyResponse(id);
	}

	/**
	 * 해쉬를 뽑는 메서드
	 */
	@Override
	public void calHashes(List<ErrandsDTO> list) {
		for (ErrandsDTO dto : list) {
			dto.setHashes(new ArrayList<>());
			List<String> hashes = dto.getHashes();
			int index = 0;
			String content = dto.getContent();
			while ((index = content.indexOf("#", index)) != -1) {
				int restIndex = content.indexOf(" ", index);
				if (restIndex == -1) {
					restIndex = content.length();
				}
				String result = content.substring(index + 1, restIndex);
				hashes.add(result);
				index = restIndex;
			}
		}
	}

	@Override
	public int deleteReply(int num) {
		return errandsDAO.deleteReply(num);
	}

	@Override
	public int updateErrands(int errandsNum, String responseId, String requestId, String startTime, String arrivalTime,
			String finishTime, int point) {
		if (startTime != null) {
			memberDAO.updatePoint(point, requestId);
		}
		return errandsDAO.updateErrands(errandsNum, responseId, startTime, arrivalTime, finishTime);
	}

	@Override
	public int insertGPA(GPADTO dto) {
		return errandsDAO.insertGPA(dto);
	}

	@Override
	public int okRequest(GPADTO gpaDTO, String id, String evalTag) {
		updateErrands(gpaDTO.getErrandsNum(), null, null, null, null, "finish", 0);
		insertGPA(gpaDTO);
		memberService.insertHashtag(gpaDTO.getErrandsNum(), id, evalTag);
		return 1;
	}

}
