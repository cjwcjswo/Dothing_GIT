package dothing.web.service;

import java.util.List;
import java.util.Map;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsHashtagDTO;
import dothing.web.dto.ErrandsReplyDTO;
import dothing.web.dto.GPADTO;

public interface ErrandsService {
	/**
	 * 전체 심부름 리스트 가져오기
	 * @return 심부름 DTO 리스트
	 * */
	List<ErrandsDTO> selectAll();

	/**
	 * 심부름 검색하기(심부름 꾼이 매칭 안됬고 시간이 유효한 리스트만)
	 * @param hash 해쉬태그로 검색했을 경우 해쉬태그 값
	 * @param minPrice 최소 가격
	 * @param maxPrice 최대 가격
	 * @param distance 검색 거리 반경
	 * @param latitude 거리반경의 중심 위도
	 * @param longitude 검색반경의 중심 경도
	 * @return 심부름 DTO 리스트
	 * */
	List<ErrandsDTO> searchErrands(String hash, Integer minPrice, Integer maxPrice, Integer distance, String latitude, String longitude);

	/**
	 * 심부름 번호에 해당하는 심부름 정보 가져오기
	 * @param errandsNum 심부름 번호
	 * @return 심부름 DTO
	 * */
	ErrandsDTO selectErrands(int errandsNum);

	/**
	 * 나의 요청 심부름 리스트 불러오기
	 * @param userId 해당하는 유저
	 * @param page 해당하는 페이지(0일경우 전체 리스트)
	 * @return 심부름 DTO 리스트
	 * */
	List<ErrandsDTO> myErrandsRequest(String userId, int page);
	
	/**
	 * 나의 수행 심부름 리스트 불러오기
	 * @param userId 해당하는 유저
	 * @param page 해당하는 페이지(0일경우 전체 리스트)
	 * @return 심부름 DTO 리스트
	 * */
	List<ErrandsDTO> myErrandsResponse(String userId, int page);
	
	/**
	 * 나의 전체 요청 심부름 리스트 개수 가져오기
	 * @param id 해당하는 유저
	 * @return 심부름 리스트의 count 값
	 * */
	int countMyRequest(String id);
	/**
	 * 나의 전체 응답 심부름 리스트 개수 가져오기
	 * @param id 해당하는 유저
	 * @return 심부름 리스트의 count 값
	 * */
	int countMyResponse(String id);
	
	/**
	 * 심부름 등록하기
	 * @param dto 심부름 DTO
	 * @return 성공 여부
	 * */
	int insertErrands(ErrandsDTO dto);

	/**
	 * 심부름 삭제하기
	 * @param num 삭제하고자 하는 심부름 번호
	 * @return 성공 여부
	 */
	int deleteErrands(int num);
	
	/**
	 * 심부름 취소하기
	 * @param num 취소하고자 하는 심부름 번호
	 * @param point 해당하는 심부름의 point
	 * @param id 다시 포인트를 돌려받을 유저의 id
	 * @return 성공 여부
	 */
	int cancelErrands(int num, int point,String id);
	
	/**
	 * 심부름 수정
	 * @param errandsNum 수정할 심부름 번호
	 * @param responseId 심부름이 시작됬을때 심부름꾼의 아이디
	 * @param requestId 심부름 요청자의 아이디(포인트감소)
	 * @param startTime 심부름 시작시간 여부
	 * @param arrivalTime 심부름꾼이 확인했을때의 시간
	 * @param finishTime 요청자가 확인했을때의 시간
	 * @param point 가격을 감소
	 * @return 성공 여부
	 */
	int updateErrands(int errandsNum, String responseId, String requestId, String startTime,
			String arrivalTime, String finishTime, int point);
	
	
	/**
	 * DB에서 currval에 해당하는 값(심부름 번호) 가져오기
	 * @return 심부름 번호
	 */
	int selectNum();

	/**
	 * 등록된 심부름의 갯수 가져오기
	 * @return 전체 심부름의 count 값
	 */
	int countErrands();
	
	/**
	 * 심부름 보상 금액이 가장 높은 상위 심부름 리스트 가져오기
	 * @return 심부름 리스트
	 */
	List<ErrandsDTO> moneyErrands();

	/**
	 * 심부름에 댓글 등록하기
	 * @param dto 등록하고자 하는 댓글 DTO
	 * @return 성공 여부
	 */
	int insertReply(ErrandsReplyDTO dto);
	
	/**
	 * 해당하는 댓글 삭제하기
	 * @param num 삭제하고자 하는 댓글 번호
	 * @return 성공 여부
	 */
	int deleteReply(int num);
	
	/**
	 * 해시태그로 검색했을 때 연관된 단어들 보여주기
	 * @param hash 사용자가 입력한 값
	 * @return 연관된 hashtagDTO 리스트
	 */
	List<ErrandsHashtagDTO> requestHash(String hash);
	
	/**
	 * 평정 정보 추가하기
	 * @param dto 평점 정보 dto
	 * @return 성공 여부
	 */
	int insertGPA(GPADTO dto);
	
	/**
	 * 사용자가 심부름 완료를 눌렀을때 프로세스
	 * @param gpaDTO 사용자가 상대방에게 준 평점 DTO
	 * @param id 상대방의 아이디
	 * @param evalTag 평가 해쉬태그
	 * @param isAndroid 안드로이드에서 실행됬는지 여부 true = 안드로이드, false = 웹
	 * @return 성공 여부
	 */
	int okRequest(GPADTO gpaDTO, String id, String evalTag, boolean isAndroid);
	
	/**
	 * 해당하는 유저의의 평점 정보 가져오기
	 * @param id 해당하는 유저의 아이디
	 * @return 유저 평점 리스트
	 */
	List<GPADTO> selectGPAById(String id);
	
	/**
	 * 리스트로 보기에서 검색했을 경우 심부름 리스트 가져오기
	 * @param sort 정렬 순서(1= 최신순 2=가격 오름차순 3=가격 내림차순)
	 * @param addr 주소로 검색
	 * @param title 제목으로 검색
	 * @param page 해당하는 페이지
	 * @return 해당하는 심부름 리스트
	 */
	List<ErrandsDTO> selectList(Integer sort, String addr, String title, int page);
	
	/**
	 * 해당하는 주소의 심부름 갯수 구하기
	 * @param addr 검색 주소
	 * @return 심부름의 갯수
	 */
	int countList(String addr);
}
