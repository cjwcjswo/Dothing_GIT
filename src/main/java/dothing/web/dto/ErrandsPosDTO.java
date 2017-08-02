package dothing.web.dto;

/**
 * 심부름 위치 DTO
 */
public class ErrandsPosDTO {
	private int errandsNum; // 해당하는 심부름 번호
	private String addr; // 주소
	private String latitude; // 위도
	private String longitude; // 경도
	

	public int getErrandsNum() {
		return errandsNum;
	}
	public void setErrandsNum(int errandsNum) {
		this.errandsNum = errandsNum;
	}
	@Override
	public String toString() {
		return "ErrandsPosDTO [addr=" + addr + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
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
	
}
