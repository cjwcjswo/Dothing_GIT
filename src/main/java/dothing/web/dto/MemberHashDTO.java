package dothing.web.dto;

import java.io.Serializable;
/**
 * 유저에 달린 해시태그 DTO
 */
public class MemberHashDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int errandsNum; // 어떤 심부름을 통해 달렸는지 심부름 번호
	private String memberId; // 유저 아이디
	private String hashtag; // 해시태그 내용
	
	public MemberHashDTO(){}
	public MemberHashDTO(int errandsNum, String memberId, String hashtag) {
		super();
		this.errandsNum = errandsNum;
		this.memberId = memberId;
		this.hashtag = hashtag;
	}


	public int getErrandsNum() {
		return errandsNum;
	}


	public void setErrandsNum(int errandsNum) {
		this.errandsNum = errandsNum;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getHashtag() {
		return hashtag;
	}


	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	
	
	
	
}
