package dothing.web.util;
/**
 * ������������ ����¡ ó���� �ϱ� ���� Ŭ����
 */
public class PageMaker {
	/**
	 * ��ü ������ ��
	 */
	private int totalPage;
	/**
	 * ���� ������ ��
	 */
	private int currentPage;
	/**
	 * �������� ������
	 */
	private int lastPage;
	/**
	 * �������� ����
	 */
	private int startPage;
	/**
	 * ������ư�� ����
	 */
	private boolean next;
	/**
	 * ������ư�� ����
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
	 * ���� ������(currentPage)�� �������� ����,������ �������� ����, 
	 * ���� �������� ������ �������
	 */
	public void start(){
		// ������������ ������ �������� ����ϴ� �˰���
		startPage = ((currentPage-1)/5) * 5 + 1; 
		lastPage = ((currentPage-1)/5) * 5 + 5;
		// ������ �������� ��ü �������� ũ�� ��ü ������ ���� �������������� ��
		if(lastPage > totalPage) lastPage = totalPage;
		if(lastPage < totalPage) next = true;
		if(lastPage <= 5) previous = false;
		System.out.println("startPage: " + startPage);
		System.out.println("lastPage: " + lastPage);
		System.out.println("totalPage: " + totalPage);
	}
}
