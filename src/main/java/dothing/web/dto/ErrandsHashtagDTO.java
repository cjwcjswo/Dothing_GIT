package dothing.web.dto;

/**
 * �ɺθ� �ؽ��±� DTO
 */
public class ErrandsHashtagDTO {
	private String hashtag; // �ؽ��±� ����
	private int count; // ��ϵ� ��
	
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
