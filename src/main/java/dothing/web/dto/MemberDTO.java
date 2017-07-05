package dothing.web.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MemberDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String name;
	private String password;
	private String sex;
	private String preAddr;
	private String detailAddr;
	private int auth;
	private String selfImg;
	private String ssnImg;
	private String joinDate;
	private String introduce;
	private String latitude;
	private String longitude;
	private String userType;
	private Integer state;
	MultipartFile selfImgFile;
	MultipartFile ssnImgFile;
	
	private PointDTO point;
	private List<GPADTO> gpaList;
	private List<MemberHashDTO> hashList;
	
	
	
	

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