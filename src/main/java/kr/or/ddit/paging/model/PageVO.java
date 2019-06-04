package kr.or.ddit.paging.model;

public class PageVO {

	
	private int page;
	private int pageSize;
	
	public PageVO() {
		// TODO Auto-generated constructor stub
	}

	public PageVO(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "PageVO [page=" + page + ", pageSize=" + pageSize + "]";
	}
	
	
}
