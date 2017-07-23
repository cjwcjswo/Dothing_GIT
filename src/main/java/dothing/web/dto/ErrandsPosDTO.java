package dothing.web.dto;

public class ErrandsPosDTO {
	private int errandsNum;
	private String addr;
	private String latitude;
	private String longitude;
	

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
