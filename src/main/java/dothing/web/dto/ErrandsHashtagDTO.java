package dothing.web.dto;

/**
 * 심부름 해시태그 DTO
 */
public class ErrandsHashtagDTO {
	private String hashtag; // 해시태그 내용
	private int count; // 등록된 수
	
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
