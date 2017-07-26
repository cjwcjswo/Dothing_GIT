package dothing.web.service;

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
import dothing.web.dto.ErrandsHashtagDTO;
import dothing.web.dto.ErrandsReplyDTO;
import dothing.web.dto.GPADTO;
import dothing.web.dto.MemberDTO;

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
		errandsDAO.deleteTimeErrands();
		List<ErrandsDTO> list = errandsDAO.selectAll();
		for (ErrandsDTO dto : list) {
			dto.setHashes(errandsDAO.selectErrandsHashtag(dto.getErrandsNum()));
		}
		return list;
	}

	@Override
	public ErrandsDTO selectErrands(int errandsNum) {
		ErrandsDTO dto = errandsDAO.selectErrands(errandsNum);
		List<ErrandsReplyDTO> replyList = dto.getErrandsReply();
		for (ErrandsReplyDTO reply : replyList) {
			MemberDTO replyUser = reply.getUser();
			if (memberDAO.isSafety(replyUser.getUserId())) {
				replyUser.setAuth(2);
			}
			replyUser.setHashList(memberDAO.selectHashtag(replyUser.getUserId()));
			replyUser.setGpaList(errandsDAO.selectGPAById(replyUser.getUserId()));
		}
		MemberDTO request = dto.getRequestUser();
		MemberDTO response = dto.getResponseUser();
		if (response != null) {
			response = memberDAO.selectMemberById(response.getUserId());
		}
		request.setGpaList(errandsDAO.selectGPAById(request.getUserId()));
		request.setHashList(memberDAO.selectHashtag(request.getUserId()));
		dto.setHashes(errandsDAO.selectErrandsHashtag(errandsNum));
		return dto;
	}

	/**
	 * 심부름 삽입
	 */
	@Override
	public int insertErrands(ErrandsDTO dto) {

		errandsDAO.insertErrands(dto);
		errandsDAO.insertErrandsPos(dto.getErrandsPos());
		int index = 0;
		String content = dto.getContent();
		while ((index = content.indexOf("#", index)) != -1) { // 해쉬태그 삽입
			int restIndex = content.indexOf(" ", index);
			if (restIndex == -1) {
				restIndex = content.length();
			}
			String result = content.substring(index + 1, restIndex);
			errandsDAO.insertErrandsHashtag(result);
			index = restIndex;
		}

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
		for (ErrandsDTO dto : list) {
			dto.setHashes(errandsDAO.selectErrandsHashtag(dto.getErrandsNum()));
		}
		return list;
	}

	@Override
	public List<ErrandsHashtagDTO> requestHash(String hash) {
		hash = "%" + hash + "%";
		return errandsDAO.serachErrandsHashtag(hash);
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
		for (ErrandsDTO dto : list) {
			int errandsNum = dto.getErrandsNum();
			dto.setErrandsPos(errandsDAO.selectErrandsPos(errandsNum));
			dto.setErrandsReply(errandsDAO.selectByErrands(errandsNum));
			dto.setGpa(errandsDAO.selectGPA(errandsNum));
			dto.setHashes(errandsDAO.selectErrandsHashtag(errandsNum));
		}
		return list;
	}

	/**
	 * 내 응답 심부름 조회
	 */
	@Override
	public List<ErrandsDTO> myErrandsResponse(String userId, int page) {
		List<ErrandsDTO> list = errandsDAO.myResponseErrands(userId, page);
		for (ErrandsDTO dto : list) {
			int errandsNum = dto.getErrandsNum();
			dto.setErrandsReply(errandsDAO.selectByErrands(errandsNum));
			dto.setGpa(errandsDAO.selectGPA(errandsNum));
			dto.setHashes(errandsDAO.selectErrandsHashtag(errandsNum));
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
		System.out.println(arrivalTime + " " + finishTime);
		return errandsDAO.updateErrands(errandsNum, responseId, startTime, arrivalTime, finishTime);
	}

	@Override
	public int insertGPA(GPADTO dto) {
		return errandsDAO.insertGPA(dto);
	}

	@Override
	public int okRequest(GPADTO gpaDTO, String id, String evalTag, boolean isAndroid) {
		insertGPA(gpaDTO);
		if (isAndroid) {
			if(evalTag != null && !evalTag.trim().equals("")){
			int index = 0;
			while ((index = evalTag.indexOf("#", index)) != -1) { // 해쉬태그 삽입
				int restIndex = evalTag.indexOf(" ", index);
				if (restIndex == -1) {
					restIndex = evalTag.length();
				}
				String result = evalTag.substring(index + 1, restIndex);
				memberService.insertHashtag(gpaDTO.getErrandsNum(), id, result, true);
				index = restIndex;
			}
			}
		} else {
			if (evalTag != null && evalTag.length() > 0) {
				memberService.insertHashtag(gpaDTO.getErrandsNum(), id, evalTag, false);
			}
		}
		memberService.insertNotification(id, gpaDTO.getErrandsNum() + "번의 게시물 상대방이 거래완료를 눌렀습니다");
		return 1;
	}

	@Override
	public int cancleErrands(int num, int point, String id) {
		memberDAO.updatePoint(point, id);
		errandsDAO.deleteErrands(num);
		return 1;
	}

	@Override
	public List<ErrandsDTO> moneyErrands() {
		List<ErrandsDTO> list = errandsDAO.moneyErrands();
		for (ErrandsDTO dto : list) {
			MemberDTO request = dto.getRequestUser();
			request.setGpaList(memberDAO.averageGPA(request.getUserId()));
			dto.setHashes(errandsDAO.selectErrandsHashtag(dto.getErrandsNum()));
		}
		return list;
	}

	@Override
	public List<GPADTO> selectGPAById(String id) {
		return errandsDAO.selectGPAById(id);
	}

	@Override
	public List<ErrandsDTO> selectList(Integer sort, String addr, String title, int page) {
		List<ErrandsDTO> list = errandsDAO.selectList(sort, addr, title, page);
		for (ErrandsDTO dto : list) {
			int errandsNum = dto.getErrandsNum();
			MemberDTO requestUser = dto.getRequestUser();
			requestUser.setGpaList(errandsDAO.selectGPAById(requestUser.getUserId()));
			dto.setHashes(errandsDAO.selectErrandsHashtag(dto.getErrandsNum()));
			dto.setErrandsPos(errandsDAO.selectErrandsPos(errandsNum));
			dto.setErrandsReply(errandsDAO.selectByErrands(errandsNum));
		}
		return list;
	}

	@Override
	public int countList(String addr) {
		return errandsDAO.countList(addr);
	}

}
