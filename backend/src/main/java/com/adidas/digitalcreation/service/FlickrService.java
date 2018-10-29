package com.adidas.digitalcreation.service;

import com.adidas.digitalcreation.exception.DigitalCreationException;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;

public interface FlickrService {	
	
	PhotoList<Photo> search(String query, int perPage, int page, int sortBy) throws DigitalCreationException;
}
