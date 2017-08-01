package dothing.web.dao;

import java.util.List;

import dothing.web.dto.ErrandsDTO;
import dothing.web.dto.ErrandsHashtagDTO;
import dothing.web.dto.ErrandsPosDTO;
import dothing.web.dto.ErrandsReplyDTO;
import dothing.web.dto.GPADTO;

public interface ErrandsDAO {
	/**
	 * 전체 심부름 리스트 가져오기
	 * @return List<ErrandsDTO>: 심부름 DTO 리스트
	 * */
	List<ErrandsDTO> selectAll();
	
	/**
	 * 심부름 검색하기(심부름 꾼이 매칭 안됬고 시간이 유효한 리스트만)
	 * @param String hash: 해쉬태그로 검색했을 경우 해쉬태그 값
	 * @param Integer minPrice: 최소 가격
	 * @param Integer maxPrice: 최대 가격
	 * @param Integer distance: 검색 거리 반경
	 * @param String latitude: 거리반경의 중심 위도
	 * @param String longitude: 검색반경의 중심 경도
	 * @return List<ErrandsDTO> 심부름 DTO 리스트
	 * */
	List<ErrandsDTO> searchErrands(String hash, Integer minPrice, Integer maxPrice,
			Integer distance, String latitude, String longitude);
	
	/**
	 * 심부름 검색하기(조건 없는 전체 심부름)
	 * @param String hash: 해쉬태그로 검색했을 경우 해쉬태그 값
	 * @param Integer minPrice: 최소 가격
	 * @param Integer maxPrice: 최대 가격
	 * @param Integer distance: 검색 거리 반경
	 * @param String latitude: 거리반경의 중심 위도
	 * @param String longitude: 검색반경의 중심 경도
	 * @return List<ErrandsDTO> 심부름 DTO 리스트
	 * */
	List<ErrandsDTO> searchErrandsAll(String hash, Integer minPrice, Integer maxPrice,
			Integer distance, String latitude, String longitude);
	
	/**
	 * 나의 요청 심부름 리스트 불러오기
	 * @param String userId: 해당하는 유저
	 * @param int page: 해당하는 페이지(0일경우 전체 리스트)
	 * @return List<ErrandsDTO>: 심부름 DTO 리스트
	 * */
	List<ErrandsDTO> myRequestErrands(String userId, int page);
	
	/**
	 * 나의 수행 심부름 리스트 불러오기
	 * @param String userId: 해당하는 유저
	 * @param int page: 해당하는 페이지(0일경우 전체 리스트)
	 * @return List<ErrandsDTO>: 심부름 DTO 리스트
	 * */
	List<ErrandsDTO> myResponseErrands(String userId, int page);
	
	/**
	 * 나의 전체 요청 심부름 리스트 개수 가져오기
	 * @param String userId: 해당하는 유저
	 * @return int: 심부름 리스트의 count 값
	 * */
	int countMyRequest(String id);
	
	/**
	 * 나의 전체 응답 심부름 리스트 개수 가져오기
	 * @param String userId: 해당하는 유저
	 * @return int: 심부름 리스트의 count 값
	 * */
	int countMyResponse(String id);
	
	/**
	 * 심부름 번호에 해당하는 심부름 정보 가져오기
	 * @param int errandsNum: 심부름 번호
	 * @return ErrandsDTO: 심부름 DTO
	 * */
	ErrandsDTO selectErrands(int errandsNum);
	
	/**
	 * 심부름 등록하기
	 * @param ErrandsDTO dto: 심부름 DTO
	 * @return int: 성공 여부
	 * */
	int insertErrands(ErrandsDTO dto);
	
	/**
	 * 심부름 위치정보 등록하기
	 * @param ErrandsPosDTO dto: 심부름 위치정보 DTO
	 * @return int: 성공 여부
	 * */
	int insertErrandsPos(ErrandsPosDTO dto);
	
	
	/**
	 * 해당하는 심부름 번호의 심부름 위치정보 가져오기
	 * @param int errandsNum: 심부름 번호
	 * @return ErrandsPosDTO: 심부름 위치 정보
	 * */
	ErrandsPosDTO selectErrandsPos(int errandsNum);
	
	/**
	 * 심부름 삭제하기
	 * @param num: 삭제하고자 하는 심부름 번호
	 * @return int: 성공 여부
	 */
	int deleteErrands(int num);
	
	/**
	 * 등록된 심부름의 갯수 가져오기
	 * @return int: 전체 심부름의 count 값
	 */
	int countErrands();
	
	/**
	 * DB에서 currval에 해당하는 값(심부름 번호) 가져오기
	 * @return int: 심부름 번호
	 */
	int selectNum();
	
	/**
	 * 해당하는 심부름의 정보 수정하기
	 * @param int errandsNum: 해당하는 심부름 번호
	 * @param String responseId: 추가 할 심부름 배달꾼 id
	 * @param String startTime: 심부름 시작 시간 추가
	 * @param String arrivalTime: 심부름꾼 도착 시간 추가
	 * @param String finishTime: 요청자가 배달받은 시간 추가
	 * @return int: 성공 여부
	 * */
	int updateErrands(int errandsNum, String responseId, String startTime,
			String arrivalTime, String finishTime);
	
	
	/**
	 * 심부름 보상 금액이 가장 높은 상위 심부름 리스트 가져오기
	 * @return List<ErrandsDTO>: 심부름 리스트
	 */
	List<ErrandsDTO> moneyErrands();
	
	/**
	 * 심부름에 댓글 등록하기
	 * @param ErrandsReplyDTO dto: 등록하고자 하는 댓글 DTO
	 * @return int: 성공 여부
	 */
	int insertReply(ErrandsReplyDTO dto);
	
	/**
	 * 해당하는 댓글 삭제하기
	 * @param int num: 삭제하고자 하는 댓글 번호
	 * @return int: 성공 여부
	 */
	int deleteReply(int num);
	
	/**
	 * 해당하는 심부름의 댓글 리스트 가져오기
	 * @param int num: 해당하는 심부름 번호
	 * @return List<ErrandsReplyDTO>: 심부름 댓글 DTO 리스트
	 */
	List<ErrandsReplyDTO> selectByErrands(int num);
	
	/**
	 * 해당하는 심부름의 평점 정보 가져오기
	 * @param int num: 해당하는 심부름 번호
	 * @return List<GPADTO>: 심부름 평점 리스트
	 */
	List<GPADTO> selectGPA(int num);
	
	/**
	 * 해당하는 유저의의 평점 정보 가져오기
	 * @param String id: 해당하는 유저의 아이디
	 * @return List<GPADTO>: 유저 평점 리스트
	 */
	List<GPADTO> selectGPAById(String id);
	
	/**
	 * 평정 정보 추가하기
	 * @param GPADTO dto: 평점 정보 dto
	 * @return int: 성공 여부
	 */
	int insertGPA(GPADTO dto);
	
	/**
	 * 리스트로 보기에서 검색했을 경우 심부름 리스트 가져오기
	 * @param Integer sort: 정렬 순서(1= 최신순 2=가격 오름차순 3=가격 내림차순)
	 * @param String addr: 주소로 검색
	 * @param String title: 제목으로 검색
	 * @param int page: 해당하는 페이지
	 * @return List<ErrandsDTO>: 해당하는 심부름 리스트
	 */
	List<ErrandsDTO> selectList(Integer sort, String addr, String title, int page);
	
	/**
	 * 해당하는 주소의 심부름 갯수 구하기
	 * @param String addr: 검색 주소
	 * @return int: 심부름의 갯수
	 */
	int countList(String addr);
	

	/**
	 * 현재 시간 기준으로 요청 심부름 시간이 지난 심부름들 모두 삭제
	 * @return int 성공여부(삭제된 심부름의 갯수)
	 */
	int deleteTimeErrands();
	
	/**
	 * 심부름에 해쉬태그 삽입하기
	 * @param String hashtag: 해쉬태그 값
	 * @return int: 성공 여부
	 */
	int insertErrandsHashtag(String hashtag);
	
	/**
	 * 해당하는 심부름의 해쉬태그들 가져오기
	 * @param int errandsNum: 해당하는 심부름의 번호
	 * @return
	 */
	List<String> selectErrandsHashtag(int errandsNum);
	
	/**
	 * 해시태그로 검색했을 때 연관된 단어들 보여주기
	 * @param String keyword: 사용자가 입력한 값
	 * @return List<ErrandsHashtagDTO> 연관된 hashtagDTO 리스트
	 */
	List<ErrandsHashtagDTO> serachErrandsHashtag(String keyword);
}
