package dothing.web.dto;

import java.io.Serializable;
/**
 * ������ �޸� �ؽ��±� DTO
 */
public class MemberHashDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int errandsNum; // � �ɺθ��� ���� �޷ȴ��� �ɺθ� ��ȣ
	private String memberId; // ���� ���̵�
	private String hashtag; // �ؽ��±� ����
	
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
