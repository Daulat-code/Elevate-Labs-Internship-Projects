package com.cb.daulat.service;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	
	public String store(MultipartFile file) throws IOException;
	 public Path load(String filename);
	 public boolean delete(String filename);

}
