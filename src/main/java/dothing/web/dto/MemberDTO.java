package dothing.web.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
/**
 * 유저 DTO
 */
public class MemberDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userId; // 유저 아이디
	private String name; // 이름
	private String password; // 비밀번호
	private String sex; // 성별
	private String preAddr; // 주소
	private String detailAddr; // 상세주소
	private int auth; // 권한
	private String selfImg; // 프로필 사진
	private String ssnImg; // 안전 심부름꾼 사진
	private String joinDate; // 가입 날짜
	private String introduce; // 자기소개
	private String latitude; // 위도
	private String longitude; // 경도
	private String userType; // 유저 타입
	private Integer state; // 인증 번호
	MultipartFile selfImgFile; // 프로필사진 파일
	MultipartFile ssnImgFile; // 안전심부름꾼 사진 파일
	
	private PointDTO point; // 포인트 dto
	private List<GPADTO> gpaList; // 평점 리스트
	private List<MemberHashDTO> hashList; // 해시태그 리스트
	

	public List<MemberHashDTO> getHashList() {
		return hashList;
	}

	public void setHashList(List<MemberHashDTO> hashList) {
		this.hashList = hashList;
	}

	public MemberDTO() {
		super();
	}
	


	public List<GPADTO> getGpaList() {
		return gpaList;
	}
	public void setGpaList(List<GPADTO> gpaList) {
		this.gpaList = gpaList;
	}
	public PointDTO getPoint() {
		return point;
	}
	public void setPoint(PointDTO point) {
		this.point = point;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public String getSelfImg() {
		return selfImg;
	}
	public void setSelfImg(String selfImg) {
		this.selfImg = selfImg;
	}
	public String getSsnImg() {
		return ssnImg;
	}
	public void setSsnImg(String ssnImg) {
		this.ssnImg = ssnImg;
	}
	public MultipartFile getSelfImgFile() {
		return selfImgFile;
	}
	public void setSelfImgFile(MultipartFile selfImgFile) {
		this.selfImgFile = selfImgFile;
	}
	public MultipartFile getSsnImgFile() {
		return ssnImgFile;
	}
	public void setSsnImgFile(MultipartFile ssnImgFile) {
		this.ssnImgFile = ssnImgFile;
	}
	
	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPreAddr() {
		return preAddr;
	}

	public void setPreAddr(String preAddr) {
		this.preAddr = preAddr;
	}

	public String getDetailAddr() {
		return detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}


	public MemberDTO(String userId, String name, String password, String sex, String preAddr, String detailAddr,
			int auth, String selfImg, String ssnImg, String joinDate, String introduce, String latitude,
			String longitude, String userType, Integer state, MultipartFile selfImgFile, MultipartFile ssnImgFile,
			PointDTO point, List<GPADTO> gpaList, List<MemberHashDTO> hashList) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.sex = sex;
		this.preAddr = preAddr;
		this.detailAddr = detailAddr;
		this.auth = auth;
		this.selfImg = selfImg;
		this.ssnImg = ssnImg;
		this.joinDate = joinDate;
		this.introduce = introduce;
		this.latitude = latitude;
		this.longitude = longitude;
		this.userType = userType;
		this.state = state;
		this.selfImgFile = selfImgFile;
		this.ssnImgFile = ssnImgFile;
		this.point = point;
		this.gpaList = gpaList;
		this.hashList = hashList;
	}

	@Override
	public String toString() {
		return "MemberDTO [userId=" + userId + ", name=" + name + ", password=" + password + ", sex=" + sex
				+ ", preAddr=" + preAddr + ", detailAddr=" + detailAddr + ", auth=" + auth + ", selfImg=" + selfImg
				+ ", ssnImg=" + ssnImg + ", joinDate=" + joinDate + ", introduce=" + introduce + ", latitude="
				+ latitude + ", longitude=" + longitude + ", userType=" + userType + ", state=" + state
				+ ", selfImgFile=" + selfImgFile + ", ssnImgFile=" + ssnImgFile + ", point=" + point + ", gpaList="
				+ gpaList + ", hashList=" + hashList + "]";
	}






	
	
}