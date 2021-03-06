package dothing.web.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
/**
 * 심부름 DTO
 */
public class ErrandsDTO {
	private int errandsNum; // 심부름 번호
	private MemberDTO requestUser; // 요청 유저 DTO
	private MemberDTO responseUser; // 응답 유저 DTO
	private String startTime; // 심부름 시작 시간
	private String endTime; // 요청 종료 시간
	private String arrivalTime; // 심부름꾼 완료 시간
	private String finishTime; // 요청자 완료 시간
	private int productPrice; // 물건 가격
	private int errandsPrice; // 심부름 가격
	private String errandsPhoto; // 심부름 이미지(DB에 저장되는 문자)
	private String title; // 심부름 제목
	private String content; // 심부름 내용
	private MultipartFile errandsPhotoFile; // 심부름 이미지(파일)

	private List<ErrandsReplyDTO> errandsReply; // 심부름에 달린 댓글들
	
	private ErrandsPosDTO errandsPos; // 심부름의 위치 DTO
	
	private List<GPADTO> gpa; // 심부름의 평점 DTO 리스트
	
	private List<String> hashes; // 심부름 해시태그 리스트

	
	
	


	@Override
	public String toString() {
		return "ErrandsDTO [errandsNum=" + errandsNum + ", requestUser=" + requestUser + ", responseUser="
				+ responseUser + ", startTime=" + startTime + ", endTime=" + endTime + ", arrivalTime=" + arrivalTime
				+ ", finishTime=" + finishTime + ", productPrice=" + productPrice + ", errandsPrice=" + errandsPrice
				+ ", errandsPhoto=" + errandsPhoto + ", title=" + title + ", content=" + content + ", errandsPhotoFile="
				+ errandsPhotoFile + ", errandsReply=" + errandsReply + ", errandsPos=" + errandsPos + ", gpa=" + gpa
				+ ", hashes=" + hashes + "]";
	}

	public List<String> getHashes() {
		return hashes;
	}

	public void setHashes(List<String> hashes) {
		this.hashes = hashes;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}



	public ErrandsPosDTO getErrandsPos() {
		return errandsPos;
	}

	public void setErrandsPos(ErrandsPosDTO errandsPos) {
		this.errandsPos = errandsPos;
	}

	public List<ErrandsReplyDTO> getErrandsReply() {
		return errandsReply;
	}

	public void setErrandsReply(List<ErrandsReplyDTO> errandsReply) {
		this.errandsReply = errandsReply;
	}



	public List<GPADTO> getGpa() {
		return gpa;
	}

	public void setGpa(List<GPADTO> gpa) {
		this.gpa = gpa;
	}

	public int getErrandsNum() {
		return errandsNum;
	}

	public void setErrandsNum(int errandsNum) {
		this.errandsNum = errandsNum;
	}

	public MemberDTO getRequestUser() {
		return requestUser;
	}

	public void setRequestUser(MemberDTO requestUser) {
		this.requestUser = requestUser;
	}

	public MemberDTO getResponseUser() {
		return responseUser;
	}

	public void setResponseUser(MemberDTO responseUser) {
		this.responseUser = responseUser;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getErrandsPrice() {
		return errandsPrice;
	}

	public void setErrandsPrice(int errandsPrice) {
		this.errandsPrice = errandsPrice;
	}

	public String getErrandsPhoto() {
		return errandsPhoto;
	}

	public void setErrandsPhoto(String errandsPhoto) {
		this.errandsPhoto = errandsPhoto;
	}

	public MultipartFile getErrandsPhotoFile() {
		return errandsPhotoFile;
	}

	public void setErrandsPhotoFile(MultipartFile errandsPhotoFile) {
		this.errandsPhotoFile = errandsPhotoFile;
	}


	
}
