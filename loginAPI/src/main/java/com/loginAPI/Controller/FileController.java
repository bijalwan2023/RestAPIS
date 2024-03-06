package com.loginAPI.Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Optional;
import java.util.zip.Deflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.loginAPI.Entity.ImageData;
import com.loginAPI.Repository.StorageRepository;
import com.loginAPI.Service.FileService;
import com.loginAPI.Service.StorageService;

@RestController
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	FileService fileService;
	@Autowired
	private StorageService service;
	
	@Autowired
	StorageRepository storageRepository;
	 @PostMapping("/take_photo")
	    public String takePhoto(@RequestParam("photo") MultipartFile photo) throws IOException {
	        if (photo.isEmpty()) {
	            return "No photo provided";
	        }
	        // Convert the image to base64 string
	        byte[] photoBytes = photo.getBytes();
	        String imageBase64 = Base64.getEncoder().encodeToString(photoBytes);

	        return imageBase64;
	    }


	@PostMapping("/uploadImage")
	public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
		String uploadImage = service.uploadImage(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(uploadImage);
	}

	@GetMapping("download/{fileName}")
	public ResponseEntity<?> downloadImage(@PathVariable String fileName){
		byte[] imageData=service.downloadImage(fileName);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}
	@PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            // Save the file locally and get the file path
            String filePath = fileService.saveFileLocally(file);

            // Respond with the file path
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("File has been uploaded successfully. Path: " + filePath);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload the file: " + e.getMessage());
        }
    }
 


}
