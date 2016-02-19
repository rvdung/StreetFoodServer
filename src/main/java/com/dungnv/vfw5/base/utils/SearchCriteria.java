package com.sony.spe.mp.entity.ref.criteria;

import java.util.List;

abstract class SearchCriteria {
	
	/**
	 * If set, count query is executed.
	 */
	protected boolean runCountQuery;

	/**
	 * If set, paging features are turned off.
	 */
	protected boolean turnOffPaging;
	
	
	/**
	 * Page number to be returned in the list.
	 */
	private int pageNumber;
	/**
	 * Number of records per page.
	 */
	protected int pageSize;

	/**
	 * Total number of records returned by the query.
	 */
	protected long totalRecords;
	
	/**
	 * This method returns true if the current page is the last page returned 
	 * by the query.
	 * 	 
	 * @return true, if the current page is the last page returned by the query.
	 */
	public boolean isLastPage() {
		// totalPages -1 is done, because PageNumber starts from 0.
		return this.getTotalRecords() != 0 ? 
				this.getPageNumber() == this.getTotalPages()-1 : true;

	}

	/** 
	 * This method returns the total page count. 
	 * 
	 * @return long 
	 */
	public long getTotalPages() {
		return this.getTotalRecords()%this.getPageSize() == 0?
					this.getTotalRecords()/this.getPageSize() : 
						this.getTotalRecords()/this.getPageSize() +1;
	}


	/**
	 * This method returns the position of the first record returned by the 
	 * query in the entire un-paginated result.
	 * 
	 * For e.g. - Total up-paginated result count is 50 and 10 records are 
	 * displayed on each page, then for 3rd page, this method would return the 
	 * value 21. 
	 * 
	 * @return long
	 */
	public long getFirstRecordNumber() {
		return this.getPageNumber() * this.getPageSize() + 1;
	}

	/**
	 * This method returns the position of the first record returned by the 
	 * query in the entire un-paginated result.
	 * 
	 * For e.g. - Total up-paginated result count is 50 and 10 records are 
	 * displayed on each page, then for 3rd page, this method would return the 
	 * value 30. 
	 * 
	 * @return long
	 */	
	public long getLastRecordNumber() {
		long lastRecord = (this.getPageNumber() +1 ) * this.getPageSize();
		return lastRecord > this.getTotalRecords()?
				this.getTotalRecords(): lastRecord;
	}
	
	/**
	 *  Implement this method to prepare and execute search query.
	 *    
	 * @return List<Object>
	 */
	public abstract List<Object> getResultList() throws Exception; 
	

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public boolean isTurnOffPaging() {
		return turnOffPaging;
	}

	public void setTurnOffPaging(boolean turnOffPaging) {
		this.turnOffPaging = turnOffPaging;
	}

	public boolean isRunCountQuery() {
		return runCountQuery;
	}

	public void setRunCountQuery(boolean runCountQuery) {
		this.runCountQuery = runCountQuery;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

}
