package com.adidas.digitalcreation.controllers;

import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.digitalcreation.dto.FlickrItemDTO;
import com.adidas.digitalcreation.dto.SearchResultDTO;
import com.adidas.digitalcreation.exception.DigitalCreationException;
import com.adidas.digitalcreation.exception.InvalidRequestException;
import com.adidas.digitalcreation.exception.StorageException;
import com.adidas.digitalcreation.service.SearchService;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;

@RestController
@RequestMapping("/api")
public class SearchController {

	public static final Logger logger = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	SearchService searchService;

	@RequestMapping("/search")
	@ResponseBody
	public SearchResultDTO search(@RequestParam("search") String search, @RequestParam("page") int page, @RequestParam("sort") int sort) {
		PhotoList<Photo> flickrItems = searchService.search(search, page, sort);

		List<FlickrItemDTO> flickrItemDTOs = flickrItems.stream().map(item -> convertToDto(item))
				.collect(Collectors.toList());

		SearchResultDTO result = new SearchResultDTO();
		result.setTotalPages(flickrItems.getPages());
		result.setTotalResults(flickrItems.getTotal());
		result.setPerPage(flickrItems.getPerPage());
		result.setItems(flickrItemDTOs);

		return result;
	}

	@ExceptionHandler({ DigitalCreationException.class, InvalidRequestException.class, StorageException.class })
    public ResponseEntity<String> handleException(RuntimeException e) {
		
		ResponseStatus annotation = findMergedAnnotation(e.getClass(), ResponseStatus.class);
        if (annotation != null) {
        	return new ResponseEntity<String>(e.getMessage(), annotation.value());
        }
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	private FlickrItemDTO convertToDto(Photo photo) {
		FlickrItemDTO itemDTO = new ModelMapper().map(photo, FlickrItemDTO.class);
		return itemDTO;
	}
}
