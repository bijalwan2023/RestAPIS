package com.loginAPI.Service;

import java.io.IOException;
import java.util.UUID;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    private static final String UPLOAD_DIR = "D:\\file"; // Replace with your desired directory path

    public String saveFileLocally(MultipartFile multipartFile) throws IOException {
        // Generate a unique filename for the file
        String fileName = "files_" + UUID.randomUUID() + "_" + multipartFile.getOriginalFilename();

        // Build the path where the file will be saved
        
        Path filePath = Path.of(UPLOAD_DIR, fileName);

        // Save the file locally
        Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Return the local file path
        return filePath.toString();
    }
}







