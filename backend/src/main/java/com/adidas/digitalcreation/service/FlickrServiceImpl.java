package com.adidas.digitalcreation.service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.adidas.digitalcreation.exception.DigitalCreationException;
import com.adidas.digitalcreation.exception.InvalidRequestException;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.photos.Extras;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photos.SearchParameters;
import com.flickr4java.flickr.photos.Size;

@Service("flickrService")
public class FlickrServiceImpl implements FlickrService {

	@Autowired
	FileSystemStorageService fileSystemStorageService;

	private PhotosInterface photosInterface;

	@Value("${flickr.key}")
	private String key;

	@Value("${flickr.secret}")
	private String secret;

	@PostConstruct
	public void init() {
		this.photosInterface = new Flickr(key, secret, new REST()).getPhotosInterface();

		Flickr.debugRequest = false;
		Flickr.debugStream = false;
	}

	@Override
	public PhotoList<Photo> search(String tag, int perPage, int page, int sortBy) {
		try {
			PhotoList<Photo> results = this.photosInterface.search(this._getSearchParameters(tag, sortBy), perPage,
					page);

			for (Photo p : results) {
				String filename = p.getId() + "." + p.getOriginalFormat();

				if (!fileSystemStorageService.fileExists(filename)) {
					fileSystemStorageService.storeFlickrImage(this.photosInterface.getImage(p, Size.SMALL), filename,
							p.getOriginalFormat());
				}
			}

			return results;

		} catch (FlickrException e) {
			throw new DigitalCreationException(e.getMessage());
		} 
	}

	private SearchParameters _getSearchParameters(String tag, int sortBy) {

		if (!Stream.of(SearchParameters.DATE_POSTED_ASC, SearchParameters.DATE_POSTED_DESC, SearchParameters.RELEVANCE)
				.collect(Collectors.toList()).contains(sortBy)) {
			throw new InvalidRequestException("Invalid sort by criteria");
		}

		SearchParameters params = new SearchParameters();
		params.setPrivacyFilter(Flickr.PRIVACY_LEVEL_PUBLIC);
		params.setTags(new String[] { tag });
		params.setExtras(Stream.of(Extras.DATE_UPLOAD, "description").collect(Collectors.toSet()));
		params.setSort(sortBy);

		return params;
	}
}
