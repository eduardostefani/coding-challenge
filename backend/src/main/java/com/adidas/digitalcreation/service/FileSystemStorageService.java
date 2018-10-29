package com.adidas.digitalcreation.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.adidas.digitalcreation.exception.StorageException;

@Service
public class FileSystemStorageService implements StorageService {

	@Value("${storage.dir}")
	private String storageDir;

	@Value("classpath:public/static/img")
	private Resource flickrResources;
	
	
	@Override
	public void storeFlickrImage(BufferedImage image, String filename, String format) throws StorageException {
		try {
			File outputfile = new File(Paths.get(flickrResources.getURI()) + "/" + filename);
			
			if (outputfile.exists()) {
				return;
			}
			outputfile.getParentFile().mkdirs();
			ImageIO.write(image, format, outputfile);
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + filename, e);
		}
	}
	
	@Override
	public boolean fileExists(String filename) throws StorageException {
		try {
			return Files.exists(Paths.get(flickrResources.getURI()).resolve(filename));
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + filename, e);
		}
	}

}