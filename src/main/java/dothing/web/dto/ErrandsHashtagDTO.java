package dothing.web.dto;

public class ErrandsHashtagDTO {
	private String hashtag;
	private int count;
	
	public String getHashtag() {
		return hashtag;
	}
	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "ErrandsHashtagDTO [" + (hashtag != null ? "hashtag=" + hashtag + ", " : "") + "count=" + count + "]";
	}
	
}
