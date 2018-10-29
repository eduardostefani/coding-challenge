package com.adidas.digitalcreation.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FlickrItemDTO {

	private String id;

	private String filename;

	private String title;

	private String description;

	private String originalFormat;

	private Date datePosted;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setOriginalFormat(String originalFormat) {
		this.originalFormat = originalFormat;
	}

	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}

	public String getDatePosted() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		String dateString = format.format(this.datePosted);
		return dateString;
	}

	public String getImage() {
		return "static/img/" + this.id + "." + this.originalFormat;
	}
}
