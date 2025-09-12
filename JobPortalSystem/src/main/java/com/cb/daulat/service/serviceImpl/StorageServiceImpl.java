package com.cb.daulat.service.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cb.daulat.service.StorageService;

import java.io.*;
import java.nio.file.*;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path uploadDir;

    public StorageServiceImpl(@Value("${app.upload.dir}") String uploadDir) {
        this.uploadDir = Paths.get(uploadDir).toAbsolutePath().normalize();
        try { Files.createDirectories(this.uploadDir); } catch (IOException e) { throw new RuntimeException(e); }
    }

    @Override
    public String store(MultipartFile file) throws IOException {
        String filename = System.currentTimeMillis() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
        Path target = this.uploadDir.resolve(filename);
        try (InputStream in = file.getInputStream()) {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        }
        return filename; // return stored filename (join with uploadDir when needed)
    }

    @Override
    public Path load(String filename) {
        return uploadDir.resolve(filename).normalize();
    }

    @Override
    public boolean delete(String filename) {
        try { return Files.deleteIfExists(load(filename)); } catch (IOException e) { return false; }
    }
}
