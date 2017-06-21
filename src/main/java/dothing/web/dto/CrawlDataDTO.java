package dothing.web.dto;

public class CrawlDataDTO {
	private String name;
	private String img;
	private String link;
	
	public CrawlDataDTO(){}
	public CrawlDataDTO(String name, String img, String link) {
		super();
		this.name = name;
		this.img = img;
		this.link = link;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
}
