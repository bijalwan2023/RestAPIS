package com.loginAPI.Service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.loginAPI.Entity.ImageData;
import com.loginAPI.Repository.StorageRepository;
import com.loginAPI.util.ImageUtil;
@Service
public class StorageService {
	@Autowired
	StorageRepository storageRepository;

	public String uploadImage(MultipartFile file) throws IOException {
		ImageData imageData=	 storageRepository.save(ImageData.builder()
				.name(file.getOriginalFilename()).type(file.getContentType())
				.imageData(ImageUtil.compressImage(file.getBytes())).build());
		if (imageData != null) {
			return "file uploaded successfully : " + file.getOriginalFilename();
		}
		return null;
	}
	public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = storageRepository.findByName(fileName);
        byte[] images=ImageUtil.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
