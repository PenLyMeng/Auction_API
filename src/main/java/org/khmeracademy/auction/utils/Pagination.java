package org.khmeracademy.auction.utils;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Pagination implements Serializable {

	private static final long serialVersionUID = 1L;

	//TODO: CURRENT PAGE
	@JsonProperty("PAGE")
	private int page;

	//TODO: LIMIT 15
	@JsonProperty("LIMIT")
	private int limit;

	//TODO: TOTAL COUNT OF RECORDS
	@JsonProperty("TOTAL_COUNT")
	private Long totalCount;

	//TODO: TOTAL PAGE OF PAGINATION
	@JsonProperty("TOTAL_PAGES")
	private int totalPages;
	
	@JsonIgnore
	private int offset;

	public Pagination() {
		this(1, 15, 0L, 0);
	}

	public Pagination(int page, int limit) {
		this.page = page;
		this.limit = limit;
		this.totalCount = 0L;
		this.totalPages = 0;
	}

	public Pagination(int page, int limit, Long totalCount, int totalPages) {
		this.page = page;
		this.limit = limit;
		this.totalCount = totalCount;
		this.totalPages = totalPages;
	}

	public int getPage() {
		return page;
	}

	public int totalPages() {
		return (int) Math.ceil((double) this.totalCount / limit);

	}

	public int offset() {
		return offset = (this.page - 1) * limit;
	}

	public void setPage(int currentPage) {
		this.page = currentPage;
		offset();
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
		this.totalPages = totalPages();
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	@Override
	public String toString() {
		return "Pagination [page=" + page + ", limit=" + limit + ", totalCount=" + totalCount + ", totalPages="
				+ totalPages + ", offset=" + offset + "]";
	}
	
}