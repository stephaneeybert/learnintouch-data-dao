package com.thalasoft.learnintouch.data.dao.hibernate.pagination;

import java.util.List;

public class Page<T> {

	private int pageNumber;
	private int pageSize;
	private int totalSize;
	private final List<T> pageItems;

	public Page(int pageNumber, int pageSize, int totalSize, List<T> pageItems) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalSize = totalSize;
		this.pageItems = pageItems;
		if (pageNumber < 1) {
			this.pageNumber = 1;
		}
		if (pageNumber >= calculateNumberOfPages()) {
			this.pageNumber = calculateNumberOfPages();
		}
		if (pageSize < 1) {
			int defaultPageSize = 10;
			this.pageSize = defaultPageSize;
		}
	}

	 public int getPageNumber() {
		 return pageNumber;
	 }

	 public int getPageSize() {
		 return pageSize;
	 }

	public int getTotalSize() {
		return totalSize;
	}

	public List<T> getPageItems() {
		return pageItems;
	}

	public boolean isFirstPage() {
		return pageNumber == 1;
	}

	public boolean isLastPage() {
		return pageNumber >= getLastPageNumber();
	}

	public boolean hasNextPage() {
		return pageNumber < getLastPageNumber();
	}

	public boolean hasPreviousPage() {
		return pageNumber > 1;
	}

	public int getNextPageNumber() {
		if (pageNumber < getLastPageNumber()) {
			return pageNumber + 1;
		} else {
			return getLastPageNumber();
		}
	}

	public int getPreviousPageNumber() {
		if (pageNumber > 1) {
			return pageNumber - 1;
		} else {
			return 1;
		}
	}

	public int getLastPageNumber() {
		return getNumberOfPages();
	}

	public int getNumberOfPages() {
		return calculateNumberOfPages();
	}
	
	private int calculateNumberOfPages() {
		if (pageSize > 0) {
			// Most likely add a page for the remaining items
			int makeup = totalSize % pageSize != 0 ? 1 : 0;
			return totalSize / pageSize + makeup;
		} else {
			return 1;
		}
	}

	public static int getStartIndex(int pageNumber, int pageSize, int totalSize) {
		if (totalSize == 0) {
			return 0;
		}
		int startIndex;
		if (pageNumber < 1) {
			startIndex = 0;
		} else {
			startIndex = (pageNumber - 1) * pageSize;
		}
		if (startIndex > totalSize) {
			startIndex = totalSize - 1;
		}
		return startIndex;
	}

}
