package com.adidas.digitalcreation.service;

import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;

public interface SearchService {
	
	PhotoList<Photo> search(String query, int page, int sortBy);
}
