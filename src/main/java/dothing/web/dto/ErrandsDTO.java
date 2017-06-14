package dothing.web.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ErrandsDTO {
	private int errandsNum;
	private MemberDTO requestUser;
	private MemberDTO responseUser;
	private String startTime;
	private String endTime;
	private String arrivalTime;
	private String finishTime;
	private int productPrice;
	private int errandsPrice;
	private String errandsPhoto;
	private String title;
	private String content;
	private MultipartFile errandsPhotoFile;

	private List<ErrandsReplyDTO> errandsReply;
	
	private ErrandsPosDTO errandsPos;
	
	private GPADTO gpa;
	

	
	
	


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

	public GPADTO getGpa() {
		return gpa;
	}

	public void setGpa(GPADTO gpa) {
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
