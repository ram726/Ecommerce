package com.ecommerce.sb_ecom.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IFileService {
	
	public String uploadImage(String path, MultipartFile image) throws IOException;

}
