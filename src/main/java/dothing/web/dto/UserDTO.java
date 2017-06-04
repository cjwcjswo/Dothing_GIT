package dothing.web.dto;

import org.springframework.web.multipart.MultipartFile;

public class UserDTO {
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
	
	MultipartFile selfImgFile;
	MultipartFile ssnImgFile;
	
	private PointDTO point;
	
	private GPADTO gpa;
	
	
	public GPADTO getGpa() {
		return gpa;
	}
	public void setGpa(GPADTO gpa) {
		this.gpa = gpa;
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

	
}
