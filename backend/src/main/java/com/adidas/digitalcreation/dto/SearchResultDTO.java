package com.adidas.digitalcreation.dto;

import java.util.List;

public class SearchResultDTO {

	private int totalResults;

	private int totalPages;

	private int perPage;

	private List<FlickrItemDTO> items;

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPerPage() {
		return this.perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public List<FlickrItemDTO> getItems() {
		return items;
	}

	public void setItems(List<FlickrItemDTO> items) {
		this.items = items;
	}

}
