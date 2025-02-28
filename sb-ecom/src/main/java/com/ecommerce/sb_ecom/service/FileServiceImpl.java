package com.ecommerce.sb_ecom.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.sb_ecom.Utility.IdGeneratorUtil;

@Service
public class FileServiceImpl implements IFileService {

	IdGeneratorUtil idGeneratorUtil;

	@Override
	public String uploadImage(String path, MultipartFile image) throws IOException {
		String originalFileName = image.getOriginalFilename();
		String randomId = idGeneratorUtil.generateId() + "_Image";
		String fileName = randomId.concat(originalFileName.substring(originalFileName.lastIndexOf(".")));
		String filePath = path + File.separator + fileName;

		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		Files.copy(image.getInputStream(), Paths.get(filePath));
		return fileName;
	}

}
