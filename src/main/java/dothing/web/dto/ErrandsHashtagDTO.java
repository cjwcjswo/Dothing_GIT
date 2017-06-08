package dothing.web.dto;

public class ErrandsHashtagDTO {
	private ErrandsDTO errands;
	private String errandsHashtag;
	
	
	@Override
	public String toString() {
		return "ErrandsHashtagDTO [errands=" + errands + ", errandsHashtag=" + errandsHashtag + "]";
	}
	public ErrandsDTO getErrands() {
		return errands;
	}
	public void setErrands(ErrandsDTO errands) {
		this.errands = errands;
	}
	public String getErrandsHashtag() {
		return errandsHashtag;
	}
	public void setErrandsHashtag(String errandsHashtag) {
		this.errandsHashtag = errandsHashtag;
	}
	
	
}
