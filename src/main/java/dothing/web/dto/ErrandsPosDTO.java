package dothing.web.dto;

public class ErrandsPosDTO {
	private ErrandsDTO errands;
	private String addr;
	private int latitude;
	private int longitude;
	
	public ErrandsDTO getErrands() {
		return errands;
	}
	public void setErrands(ErrandsDTO errands) {
		this.errands = errands;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getLatitude() {
		return latitude;
	}
	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	public int getLongitude() {
		return longitude;
	}
	public void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	
	
}
