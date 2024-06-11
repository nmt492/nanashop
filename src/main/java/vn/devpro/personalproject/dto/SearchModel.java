package vn.devpro.personalproject.dto;

public class SearchModel {

	private int categoryId;
	private String keyword;
	private int status;
	private String beginDate;
	private String endDate;
	
	private int sizeOfPage;
	private int currentPage;
	private int totalItems;
	
	
	public SearchModel(int categoryId, String keyword, int status, String beginDate, String endDate, int sizeOfPage,
			int currentPage, int totalItems) {
		super();
		this.categoryId = categoryId;
		this.keyword = keyword;
		this.status = status;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.sizeOfPage = sizeOfPage;
		this.currentPage = currentPage;
		this.totalItems = totalItems;
	}
	
	
	public int getSizeOfPage() {
		return sizeOfPage;
	}


	public void setSizeOfPage(int sizeOfPage) {
		this.sizeOfPage = sizeOfPage;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public int getTotalItems() {
		return totalItems;
	}


	public void setTotalItems(int totalItems) {
		this.totalItems = totalItems;
	}


	public SearchModel() {
		super();
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
