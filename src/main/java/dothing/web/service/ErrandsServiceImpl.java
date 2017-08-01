package dothing.web.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		errandsDAO.deleteTimeErrands(); // 심부름을 불러올 때 마다 요청시간이 지난 심부름들을 삭제한다
		List<ErrandsDTO> list = errandsDAO.selectAll();
		for (ErrandsDTO dto : list) {
			dto.setHashes(errandsDAO.selectErrandsHashtag(dto.getErrandsNum())); // 심부름의 해쉬태그 불러오기
		}
		return list;
	}

	@Override
	public ErrandsDTO selectErrands(int errandsNum) {
		ErrandsDTO dto = errandsDAO.selectErrands(errandsNum);
		List<ErrandsReplyDTO> replyList = dto.getErrandsReply();
		// 댓글 단사람에 대한 정보 세팅
		for (ErrandsReplyDTO reply : replyList) {
			MemberDTO replyUser = reply.getUser();
			// 댓글 단사람이 안전심부름꾼일우 권한을 2로 세팅
			if (memberDAO.isSafety(replyUser.getUserId())) {
				replyUser.setAuth(2);
			}
			replyUser.setHashList(memberDAO.selectHashtag(replyUser.getUserId()));
			replyUser.setGpaList(errandsDAO.selectGPAById(replyUser.getUserId()));
		}
		MemberDTO request = dto.getRequestUser();
		MemberDTO response = dto.getResponseUser();
		
		//심부름꾼이 있을경우 심부름꾼에 대한 정보 추가
		if (response != null) {
			response = memberDAO.selectMemberById(response.getUserId());
		}
		
		// 요청자에 대한 정보 추가
		request.setGpaList(errandsDAO.selectGPAById(request.getUserId()));
		request.setHashList(memberDAO.selectHashtag(request.getUserId()));
		dto.setHashes(errandsDAO.selectErrandsHashtag(errandsNum));
		return dto;
	}

	
	@Override
	public int insertErrands(ErrandsDTO dto) {

		errandsDAO.insertErrands(dto);
		errandsDAO.insertErrandsPos(dto.getErrandsPos());
		int index = 0;
		String content = dto.getContent();
		
		// 해쉬태그를 찾는 알고리즘으로 찾아서 있을 경우 해쉬태그 삽입
		while ((index = content.indexOf("#", index)) != -1) {
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
		errandsDAO.deleteTimeErrands(); // 시간이 지난 심부름들 삭제
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

	
	@Override
	public List<ErrandsDTO> myErrandsRequest(String userId, int page) {
		List<ErrandsDTO> list = errandsDAO.myRequestErrands(userId, page);
		// 내 요청 심부름 세부 사항들 세팅
		for (ErrandsDTO dto : list) {
			int errandsNum = dto.getErrandsNum();
			dto.setErrandsPos(errandsDAO.selectErrandsPos(errandsNum));
			dto.setErrandsReply(errandsDAO.selectByErrands(errandsNum));
			dto.setGpa(errandsDAO.selectGPA(errandsNum));
			dto.setHashes(errandsDAO.selectErrandsHashtag(errandsNum));
		}
		return list;
	}


	@Override
	public List<ErrandsDTO> myErrandsResponse(String userId, int page) {
		List<ErrandsDTO> list = errandsDAO.myResponseErrands(userId, page);
		// 내 수행 심부름 세부 사항들 세팅
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
		if (startTime != null) memberDAO.updatePoint(point, requestId); // 심부름이 매칭된 경우 요청자의 포인트를 깎는다
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
		
		// 안드로일 경우
		if (isAndroid) {
			// 해쉬태그가 있다면
			if(evalTag != null && !evalTag.trim().equals("")){
			int index = 0;
			// 해쉬태그를 찾는 알고리즘을 통해 찾은 후 삽입
			while ((index = evalTag.indexOf("#", index)) != -1) { 
				int restIndex = evalTag.indexOf(" ", index);
				if (restIndex == -1) {
					restIndex = evalTag.length();
				}
				String result = evalTag.substring(index + 1, restIndex);
				memberService.insertHashtag(gpaDTO.getErrandsNum(), id, result, true);
				index = restIndex;
			}
			}
		} else { // 웹일 경우
			if (evalTag != null && evalTag.length() > 0) {
				memberService.insertHashtag(gpaDTO.getErrandsNum(), id, evalTag, false);
			}
		}
		memberService.insertNotification(id, gpaDTO.getErrandsNum() + "번의 게시물 상대방이 거래완료를 눌렀습니다");
		return 1;
	}

	@Override
	public int cancelErrands(int num, int point, String id) {
		memberDAO.updatePoint(point, id);
		errandsDAO.deleteErrands(num);
		return 1;
	}

	@Override
	public List<ErrandsDTO> moneyErrands() {
		List<ErrandsDTO> list = errandsDAO.moneyErrands();
		// 돈 되는 심부름 세부사항 세팅
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
		//리스트로 검색한 심부름 세부사항 세팅
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
