package com.adidas.digitalcreation.service;

import java.awt.image.BufferedImage;

import com.adidas.digitalcreation.exception.StorageException;

public interface StorageService {

	void storeFlickrImage(BufferedImage image, String filename, String format) throws StorageException;

	boolean fileExists(String filename) throws StorageException;
}