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
	private String email;
	private String sex;
	private String addr;
	private String phone;
	private int auth;
	private String selfImg;
	private String ssnImg;
	private String joinDate;
	private String introduce;
	
	private String userType;
	
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
	
	public MemberDTO(String userId, String name, String password, String email, String sex, String addr, String phone,
			int auth, String selfImg, String ssnImg, MultipartFile selfImgFile, MultipartFile ssnImgFile,
			PointDTO point, List<GPADTO> gpaList) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.email = email;
		this.sex = sex;
		this.addr = addr;
		this.phone = phone;
		this.auth = auth;
		this.selfImg = selfImg;
		this.ssnImg = ssnImg;
		this.selfImgFile = selfImgFile;
		this.ssnImgFile = ssnImgFile;
		this.point = point;
		this.gpaList = gpaList;
	}
	
	

	public MemberDTO(String userId, String name, String password, String email, String sex, String addr, String phone,
			int auth, String selfImg, String ssnImg, String userType, MultipartFile selfImgFile,
			MultipartFile ssnImgFile, PointDTO point, List<GPADTO> gpaList) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.email = email;
		this.sex = sex;
		this.addr = addr;
		this.phone = phone;
		this.auth = auth;
		this.selfImg = selfImg;
		this.ssnImg = ssnImg;
		this.userType = userType;
		this.selfImgFile = selfImgFile;
		this.ssnImgFile = ssnImgFile;
		this.point = point;
		this.gpaList = gpaList;
	}
	
	

	public MemberDTO(String userId, String name, String password, String email, String sex, String addr, String phone,
			int auth, String selfImg, String ssnImg, String joinDate, String introduce, String userType,
			MultipartFile selfImgFile, MultipartFile ssnImgFile, PointDTO point, List<GPADTO> gpaList) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.email = email;
		this.sex = sex;
		this.addr = addr;
		this.phone = phone;
		this.auth = auth;
		this.selfImg = selfImg;
		this.ssnImg = ssnImg;
		this.joinDate = joinDate;
		this.introduce = introduce;
		this.userType = userType;
		this.selfImgFile = selfImgFile;
		this.ssnImgFile = ssnImgFile;
		this.point = point;
		this.gpaList = gpaList;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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

	@Override
	public String toString() {
		return "MemberDTO [userId=" + userId + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", sex=" + sex + ", addr=" + addr + ", phone=" + phone + ", auth=" + auth + ", selfImg=" + selfImg
				+ ", ssnImg=" + ssnImg + ", joinDate=" + joinDate + ", introduce=" + introduce + ", userType="
				+ userType + ", selfImgFile=" + selfImgFile + ", ssnImgFile=" + ssnImgFile + ", point=" + point
				+ ", gpaList=" + gpaList + "]";
	}

	

	
	
}