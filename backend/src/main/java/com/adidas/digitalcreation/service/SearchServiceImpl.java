package com.adidas.digitalcreation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.adidas.digitalcreation.exception.InvalidRequestException;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;

@Service("searchService")
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	FlickrService flickrService;
	
	@Value( "${search.default.pageSize}" )
	private int pageSize;
	
	public PhotoList<Photo> search(String tag, int page, int sortBy) {
		if (tag == null || tag.trim().isEmpty()) {
			throw new InvalidRequestException("Tag can't be blank");
		}
		
		if (page <= 0) {
			throw new InvalidRequestException("Invalid page");
		}
		
		if (page <= 0) {
			throw new InvalidRequestException("Invalid sorting criteria");
		}
		
		return flickrService.search(tag, pageSize, page, sortBy);
	}
	
	
}
