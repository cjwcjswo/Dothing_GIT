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
		errandsDAO.deleteTimeErrands(); // �ɺθ��� �ҷ��� �� ���� ��û�ð��� ���� �ɺθ����� �����Ѵ�
		List<ErrandsDTO> list = errandsDAO.selectAll();
		for (ErrandsDTO dto : list) {
			dto.setHashes(errandsDAO.selectErrandsHashtag(dto.getErrandsNum())); // �ɺθ��� �ؽ��±� �ҷ�����
		}
		return list;
	}

	@Override
	public ErrandsDTO selectErrands(int errandsNum) {
		ErrandsDTO dto = errandsDAO.selectErrands(errandsNum);
		List<ErrandsReplyDTO> replyList = dto.getErrandsReply();
		// ��� �ܻ���� ���� ���� ����
		for (ErrandsReplyDTO reply : replyList) {
			MemberDTO replyUser = reply.getUser();
			// ��� �ܻ���� �����ɺθ����Ͽ� ������ 2�� ����
			if (memberDAO.isSafety(replyUser.getUserId())) {
				replyUser.setAuth(2);
			}
			replyUser.setHashList(memberDAO.selectHashtag(replyUser.getUserId()));
			replyUser.setGpaList(errandsDAO.selectGPAById(replyUser.getUserId()));
		}
		MemberDTO request = dto.getRequestUser();
		MemberDTO response = dto.getResponseUser();
		
		//�ɺθ����� ������� �ɺθ��ۿ� ���� ���� �߰�
		if (response != null) {
			response = memberDAO.selectMemberById(response.getUserId());
		}
		
		// ��û�ڿ� ���� ���� �߰�
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
		
		// �ؽ��±׸� ã�� �˰������� ã�Ƽ� ���� ��� �ؽ��±� ����
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
		errandsDAO.deleteTimeErrands(); // �ð��� ���� �ɺθ��� ����
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
		// �� ��û �ɺθ� ���� ���׵� ����
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
		// �� ���� �ɺθ� ���� ���׵� ����
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
		if (startTime != null) memberDAO.updatePoint(point, requestId); // �ɺθ��� ��Ī�� ��� ��û���� ����Ʈ�� ��´�
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
		
		// �ȵ���� ���
		if (isAndroid) {
			// �ؽ��±װ� �ִٸ�
			if(evalTag != null && !evalTag.trim().equals("")){
			int index = 0;
			// �ؽ��±׸� ã�� �˰����� ���� ã�� �� ����
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
		} else { // ���� ���
			if (evalTag != null && evalTag.length() > 0) {
				memberService.insertHashtag(gpaDTO.getErrandsNum(), id, evalTag, false);
			}
		}
		memberService.insertNotification(id, gpaDTO.getErrandsNum() + "���� �Խù� ������ �ŷ��ϷḦ �������ϴ�");
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
		// �� �Ǵ� �ɺθ� ���λ��� ����
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
		//����Ʈ�� �˻��� �ɺθ� ���λ��� ����
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
