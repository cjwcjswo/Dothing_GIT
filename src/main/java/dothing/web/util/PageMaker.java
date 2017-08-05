package dothing.web.util;
/**
 * 웹페이지에서 페이징 처리를 하기 위한 클래스
 */
public class PageMaker {
	/**
	 * 전체 페이지 수
	 */
	private int totalPage;
	/**
	 * 현재 페이지 수
	 */
	private int currentPage;
	/**
	 * 페이지의 마지막
	 */
	private int lastPage;
	/**
	 * 페이지의 시작
	 */
	private int startPage;
	/**
	 * 다음버튼의 유무
	 */
	private boolean next;
	/**
	 * 이전버튼의 유무
	 */
	private boolean previous;
	
	
	public PageMaker(int currentPage, int totalPage){
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.next = false;
		this.previous = true;
	}
	
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public boolean isPrevious() {
		return previous;
	}

	public void setPrevious(boolean previous) {
		this.previous = previous;
	}
	
	/**
	 * 현재 페이지(currentPage)를 기준으로 시작,마지막 페이지와 다음, 
	 * 이전 페이지의 유무를 계산해줌
	 */
	public void start(){
		// 시작페이지와 마지막 페이지를 계산하는 알고리즘
		startPage = ((currentPage-1)/5) * 5 + 1; 
		lastPage = ((currentPage-1)/5) * 5 + 5;
		// 마지막 페이지가 전체 페이지가 크면 전체 페이지 수가 마지막페이지가 됨
		if(lastPage > totalPage) lastPage = totalPage;
		if(lastPage < totalPage) next = true;
		if(lastPage <= 5) previous = false;
		System.out.println("startPage: " + startPage);
		System.out.println("lastPage: " + lastPage);
		System.out.println("totalPage: " + totalPage);
	}
}
